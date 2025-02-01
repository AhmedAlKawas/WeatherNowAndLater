package com.vodafone.city_input.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vodafone.core.models.GetSearchCitiesResponse
import kotlinx.coroutines.delay

@Composable
fun SearchCityScreen(navController: NavController, viewModel: CitiesViewModel = hiltViewModel()) {

    var query by remember { mutableStateOf("") }
    val cities by viewModel.resultCities.collectAsState(initial = emptyList())
    val citySaved by viewModel.currentCity.collectAsState(initial = null)

    LaunchedEffect(query) {
        if (query.length >= 2) {
            delay(300)
            viewModel.getCities(query)
        }
    }

    LaunchedEffect(citySaved) {
        if (citySaved != null) {
            navController.navigate("home_screen") {
                popUpTo("search_city_screen") { inclusive = true }  // Remove from backstack
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Search TextField
        TextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Enter city name") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    viewModel.getCities(query)
                }
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (query.isEmpty()) {
            Text("Start typing to search for cities.")
        } else if (cities.isEmpty()) {
            Text("No cities found. Try another search.")
        } else {
            LazyColumn {
                items(cities) { city ->
                    CityItem(city = city) {
                        viewModel.setCurrentCity(city)
                    }
                }
            }
        }
    }

}

@Composable
fun CityItem(city: GetSearchCitiesResponse, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        city.name?.let { name -> Text(text = name, fontWeight = FontWeight.Bold) }
        Row {
            city.state?.let { state -> Text(text = state) }
            city.country?.let { country -> Text(text = " ,$country") }
        }
    }
}

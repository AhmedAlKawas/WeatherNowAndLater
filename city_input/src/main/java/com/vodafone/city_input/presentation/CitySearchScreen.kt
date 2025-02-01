package com.vodafone.city_input.presentation

import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vodafone.core.models.GetSearchCitiesResponse

@Composable
fun SearchCityScreen(viewModel: CitiesViewModel = hiltViewModel()) {

    var query = ""
    val cities by viewModel.resultCities.collectAsState(initial = emptyList())

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
                    viewModel.getCities(query) // Call ViewModel to fetch cities
                }
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Display the list of cities
        if (cities.isEmpty()) {
            Text("No cities found. Try another search.")
        } else {
            LazyColumn {
                items(cities) { city ->
                    CityItem(city = city)
                }
            }
        }
    }

}

@Composable
fun CityItem(city: GetSearchCitiesResponse) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        city.name?.let { Text(text = it, fontWeight = FontWeight.Bold) }
        city.country?.let { Text(text = it) }
    }
}

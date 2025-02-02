package com.vodafone.current_city.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vodafone.current_city.presentation.CityWeatherViewModel
import com.vodafone.current_city.states.GetCityWeatherDataState

@Composable
fun HomeScreen(navController: NavController, viewModel: CityWeatherViewModel = hiltViewModel()) {

    val state by viewModel.cityWeatherData.collectAsState()

    when (val currentState = state) {
        is GetCityWeatherDataState.LoadingState -> {
            LoadingScreen()
        }

        is GetCityWeatherDataState.SuccessState -> {
            WeatherDataScreen(weatherData = currentState.weatherData) {
                navController.navigate("fore_cast_screen")
            }
        }

        is GetCityWeatherDataState.ErrorState -> {
            ErrorScreen()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getCurrentCityData()
    }

}
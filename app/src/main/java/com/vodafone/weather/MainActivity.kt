package com.vodafone.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vodafone.city_input.presentation.SearchCityScreen
import com.vodafone.data.models.entities.CurrentCityEntity
import com.vodafone.current_city.presentation.screens.HomeScreen
import com.vodafone.forecast.presentation.screens.ForeCastScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            WeatherNowAndLaterApp(mainViewModel)
        }

    }
}

@Composable
fun WeatherNowAndLaterApp(mainViewModel: MainViewModel) {
    val navController = rememberNavController()

    val currentCity by produceState<CurrentCityEntity?>(initialValue = null) {
        value = mainViewModel.getCurrentCity()
    }

    LaunchedEffect(currentCity) {
        if (currentCity != null) {
            navController.navigate("home_screen") {
                popUpTo("search_city_screen") { inclusive = true }  // Remove from backstack
            }
        }
    }

    NavHost(
        navController,
        startDestination = if (currentCity == null) "search_city_screen" else "home_screen"
    ) {
        composable("search_city_screen") { SearchCityScreen(navController) }
        composable("home_screen") { HomeScreen(navController) }
        composable("fore_cast_screen") { ForeCastScreen() }
    }
}
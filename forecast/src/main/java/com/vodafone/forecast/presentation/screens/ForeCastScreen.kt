package com.vodafone.forecast.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.vodafone.forecast.presentation.ForeCastViewModel
import com.vodafone.forecast.states.ForecastState

@Composable
fun ForeCastScreen(viewModel: ForeCastViewModel = hiltViewModel()) {

    val state by viewModel.foreCastFlow.collectAsState()

    when (val currentState = state) {
        is ForecastState.LoadingState -> {
            LoadingScreen()
        }

        is ForecastState.SuccessState -> {
            ForeCastDataScreen(currentState.days)
        }

        is ForecastState.ErrorState -> {
            ErrorScreen()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.screenLaunched()
    }

}
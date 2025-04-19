package com.vodafone.forecast.states

import com.vodafone.domain.models.CityWeatherData

sealed interface ForecastState {

    data object LoadingState : ForecastState

    data class SuccessState(val days: List<CityWeatherData>) : ForecastState

    data object ErrorState : ForecastState

}
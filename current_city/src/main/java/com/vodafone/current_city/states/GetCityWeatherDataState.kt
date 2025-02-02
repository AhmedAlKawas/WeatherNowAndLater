package com.vodafone.current_city.states

import com.vodafone.core.models.CityWeatherData

sealed class GetCityWeatherDataState {

    data object LoadingState : GetCityWeatherDataState()

    data class SuccessState(
        val weatherData: CityWeatherData
    ) : GetCityWeatherDataState()

    data object ErrorState : GetCityWeatherDataState()

}
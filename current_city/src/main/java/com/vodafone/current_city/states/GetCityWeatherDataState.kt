package com.vodafone.current_city.states

import com.vodafone.domain.models.CityWeatherData

sealed class GetCityWeatherDataState {

    data object LoadingState : GetCityWeatherDataState()

    data class SuccessState(
        val weatherData: CityWeatherData
    ) : GetCityWeatherDataState()

    data object ErrorState : GetCityWeatherDataState()

}
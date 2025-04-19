package com.vodafone.domain.repos

import com.vodafone.domain.models.CityWeatherData
import com.vodafone.domain.models.City

interface CurrentCityWeatherRepo {

    suspend fun getCurrentCityWeather(
        latitude: Double,
        longitude: Double
    ): CityWeatherData?

    suspend fun getCurrentCity(): City?

}
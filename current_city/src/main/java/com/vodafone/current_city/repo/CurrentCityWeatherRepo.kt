package com.vodafone.current_city.repo

import com.vodafone.core.models.CurrentCity
import com.vodafone.core.models.getCurrentCityWeatherResponse.GetCurrentCityWeatherResponse
import retrofit2.Response

interface CurrentCityWeatherRepo {

    suspend fun getCurrentCityWeather(
        latitude: Double,
        longitude: Double
    ): Response<GetCurrentCityWeatherResponse>

    suspend fun getCurrentCity(): CurrentCity?

}
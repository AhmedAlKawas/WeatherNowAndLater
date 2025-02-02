package com.vodafone.data.data_sources.weather

import com.vodafone.core.models.getCurrentCityWeatherResponse.GetCurrentCityWeatherResponse
import retrofit2.Response
import retrofit2.http.Query

interface WeatherDataSource {

    suspend fun getCurrentCityWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double
    ): Response<GetCurrentCityWeatherResponse>

}
package com.vodafone.data.services

import com.vodafone.core.models.getCurrentCityWeatherResponse.GetCurrentCityWeatherResponse
import com.vodafone.core.models.getDailyForecastResponse.GetDailyForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherServices {

    @GET("data/2.5/weather")
    suspend fun getCurrentCityWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String,
        @Query("units") unit: String
    ): Response<GetCurrentCityWeatherResponse>

    @GET("data/2.5/forecast/daily")
    suspend fun getDailyForeCast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String,
        @Query("units") unit: String,
        @Query("cnt") numberOfDays: Int
    ): Response<GetDailyForecastResponse>

}
package com.vodafone.data.data_sources.weather

import com.vodafone.core.models.getCurrentCityWeatherResponse.GetCurrentCityWeatherResponse
import com.vodafone.data.models.dto.getDailyForecastResponse.GetDailyForecastResponse
import com.vodafone.data.services.WeatherServices
import com.vodafone.data.utils.Constants.API_KEY
import com.vodafone.data.utils.Constants.METRIC
import com.vodafone.data.utils.Constants.NUMBER_OF_FORECAST_DAYS
import retrofit2.Response
import javax.inject.Inject

class WeatherDataSourceImpl @Inject constructor(private val weatherServices: WeatherServices) :
    WeatherDataSource {

    override suspend fun getCurrentCityWeather(
        latitude: Double,
        longitude: Double
    ): Response<GetCurrentCityWeatherResponse> =
        weatherServices.getCurrentCityWeather(latitude, longitude, API_KEY, METRIC)

    override suspend fun getDailyForeCast(
        latitude: Double,
        longitude: Double
    ): Response<GetDailyForecastResponse> = weatherServices.getDailyForeCast(
        latitude,
        longitude,
        API_KEY,
        METRIC,
        NUMBER_OF_FORECAST_DAYS
    )

}
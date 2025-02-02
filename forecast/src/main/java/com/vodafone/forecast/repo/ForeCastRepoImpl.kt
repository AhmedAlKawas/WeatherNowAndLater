package com.vodafone.forecast.repo

import com.vodafone.core.models.CurrentCity
import com.vodafone.core.models.getDailyForecastResponse.GetDailyForecastResponse
import com.vodafone.data.data_sources.cities.CitiesDataSource
import com.vodafone.data.data_sources.weather.WeatherDataSource
import retrofit2.Response
import javax.inject.Inject

class ForeCastRepoImpl @Inject constructor(
    private val citiesDataSource: CitiesDataSource,
    private val weatherDataSource: WeatherDataSource
) : ForeCastRepo {

    override suspend fun getCurrentCity(): CurrentCity? = citiesDataSource.getCurrentCity()

    override suspend fun getDailyForeCast(
        latitude: Double,
        longitude: Double
    ): Response<GetDailyForecastResponse> = weatherDataSource.getDailyForeCast(latitude, longitude)

}
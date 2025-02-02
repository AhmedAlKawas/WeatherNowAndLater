package com.vodafone.current_city.repo

import com.vodafone.core.models.CurrentCity
import com.vodafone.core.models.getCurrentCityWeatherResponse.GetCurrentCityWeatherResponse
import com.vodafone.data.data_sources.cities.CitiesDataSource
import com.vodafone.data.data_sources.weather.WeatherDataSource
import retrofit2.Response
import javax.inject.Inject

class CurrentCityWeatherRepoImpl @Inject constructor(
    private val weatherDataSource: WeatherDataSource,
    private val citiesDataSource: CitiesDataSource
) :
    CurrentCityWeatherRepo {

    override suspend fun getCurrentCityWeather(
        latitude: Double,
        longitude: Double
    ): Response<GetCurrentCityWeatherResponse> =
        weatherDataSource.getCurrentCityWeather(latitude, longitude)

    override suspend fun getCurrentCity(): CurrentCity? = citiesDataSource.getCurrentCity()

}
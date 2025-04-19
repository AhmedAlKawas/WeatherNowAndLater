package com.vodafone.data.repos

import com.vodafone.data.data_sources.cities.CitiesDataSource
import com.vodafone.data.data_sources.weather.WeatherDataSource
import com.vodafone.data.utils.WeatherUtils.getWeatherFromIcon
import com.vodafone.domain.models.City
import com.vodafone.domain.models.CityWeatherData
import com.vodafone.domain.repos.CurrentCityWeatherRepo
import javax.inject.Inject

class CurrentCityWeatherRepoImpl @Inject constructor(
    private val weatherDataSource: WeatherDataSource,
    private val citiesDataSource: CitiesDataSource
) :
    CurrentCityWeatherRepo {

    override suspend fun getCurrentCityWeather(latitude: Double, longitude: Double):
            CityWeatherData? {

        val currentCityWeather = weatherDataSource.getCurrentCityWeather(latitude, longitude)

        if (currentCityWeather.isSuccessful) {

            val cityWeatherData = CityWeatherData(
                name = null,
                state = null,
                country = null,
                temperature = currentCityWeather.body()?.main?.temp,
                condition = currentCityWeather.body()?.weather?.get(0)?.main,
                weather = getWeatherFromIcon(currentCityWeather.body()?.weather?.get(0)?.icon)
            )
            return cityWeatherData

        } else
            return null
    }

    override suspend fun getCurrentCity(): City? {

        val currentCityEntity = citiesDataSource.getCurrentCity()

        return if (currentCityEntity != null) {

            City(
                name = currentCityEntity.name,
                country = currentCityEntity.country,
                state = currentCityEntity.state,
                longitude = currentCityEntity.longitude,
                latitude = currentCityEntity.latitude
            )

        } else
            null

    }

}
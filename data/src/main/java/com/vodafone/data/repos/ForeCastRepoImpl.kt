package com.vodafone.data.repos

import com.vodafone.data.data_sources.cities.CitiesDataSource
import com.vodafone.data.data_sources.weather.WeatherDataSource
import com.vodafone.data.utils.WeatherUtils.getWeatherFromIcon
import com.vodafone.domain.models.City
import com.vodafone.domain.models.CityWeatherData
import com.vodafone.domain.repos.ForeCastRepo
import javax.inject.Inject

class ForeCastRepoImpl @Inject constructor(
    private val citiesDataSource: CitiesDataSource,
    private val weatherDataSource: WeatherDataSource
) : ForeCastRepo {

    override suspend fun getCurrentCity(): City? {

        val currentCityEntity = citiesDataSource.getCurrentCity()

        return if (currentCityEntity != null) {

            City(
                name = currentCityEntity.name,
                state = currentCityEntity.state,
                country = currentCityEntity.country,
                latitude = currentCityEntity.latitude,
                longitude = currentCityEntity.longitude
            )

        } else
            null

    }

    override suspend fun getDailyForeCast(city: City): List<CityWeatherData>? {

        val response = weatherDataSource.getDailyForeCast(city.latitude!!, city.longitude!!)

        return if (response.isSuccessful && response.body()?.list?.isNotEmpty() == true) {

            val foreCastDataList: ArrayList<CityWeatherData> = ArrayList()

            for (listItem in response.body()!!.list) {

                foreCastDataList.add(
                    CityWeatherData(
                        name = city.name,
                        state = city.state,
                        country = city.country,
                        temperature = listItem.temp?.day,
                        condition = listItem.weather[0].main,
                        weather = getWeatherFromIcon(listItem.weather[0].icon)
                    )
                )

            }

            return foreCastDataList

        } else
            null

    }

}
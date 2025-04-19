package com.vodafone.data.repos

import com.vodafone.data.data_sources.cities.CitiesDataSource
import com.vodafone.data.models.entities.CurrentCityEntity
import com.vodafone.domain.models.City
import com.vodafone.domain.repos.CitiesRepo
import javax.inject.Inject

class CitiesRepoImpl @Inject constructor(private val citiesDataSource: CitiesDataSource) :
    CitiesRepo {

    override suspend fun getCitiesByText(query: String): List<City>? {
        val response = citiesDataSource.getCitiesByText(query)

        return if (response.isSuccessful) {
            response.body()?.map { cityDto ->
                City(
                    name = cityDto.name ?: "",
                    state = cityDto.state ?: "",
                    country = cityDto.country,
                    latitude = cityDto.lat,
                    longitude = cityDto.lon
                )
            }
        } else {
            null
        }
    }

    override suspend fun setCurrentCity(currentCity: City) {

        citiesDataSource.setCurrentCity(
            CurrentCityEntity(
                id = null,
                name = currentCity.name,
                state = currentCity.state,
                country = currentCity.country,
                latitude = currentCity.latitude,
                longitude = currentCity.longitude
            )
        )

    }
}
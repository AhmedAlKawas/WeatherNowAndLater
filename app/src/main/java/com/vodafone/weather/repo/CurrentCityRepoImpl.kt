package com.vodafone.weather.repo

import com.vodafone.data.models.entities.CurrentCityEntity
import com.vodafone.data.data_sources.cities.CitiesDataSource
import javax.inject.Inject

class CurrentCityRepoImpl @Inject constructor(private val citiesDataSource: CitiesDataSource) :
    CurrentCityRepo {

    override suspend fun getCurrentCity(): CurrentCityEntity? = citiesDataSource.getCurrentCity()

}
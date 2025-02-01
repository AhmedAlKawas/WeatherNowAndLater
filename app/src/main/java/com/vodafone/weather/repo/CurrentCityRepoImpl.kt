package com.vodafone.weather.repo

import com.vodafone.core.models.CurrentCity
import com.vodafone.data.data_sources.CitiesDataSource
import javax.inject.Inject

class CurrentCityRepoImpl @Inject constructor(private val citiesDataSource: CitiesDataSource) :
    CurrentCityRepo {

    override suspend fun getCurrentCity(): CurrentCity? = citiesDataSource.getCurrentCity()

}
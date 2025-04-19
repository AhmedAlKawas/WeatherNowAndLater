package com.vodafone.data.data_sources.cities

import com.vodafone.data.models.entities.CurrentCityEntity
import com.vodafone.data.models.dto.GetSearchCitiesResponse
import retrofit2.Response

interface CitiesDataSource {

    suspend fun getCitiesByText(
        query: String
    ): Response<List<GetSearchCitiesResponse>>

    suspend fun getCurrentCity(): CurrentCityEntity?

    suspend fun setCurrentCity(city: CurrentCityEntity)

}
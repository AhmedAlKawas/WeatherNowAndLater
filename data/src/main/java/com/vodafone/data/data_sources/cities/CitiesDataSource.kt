package com.vodafone.data.data_sources.cities

import com.vodafone.core.models.CurrentCity
import com.vodafone.core.models.GetSearchCitiesResponse
import retrofit2.Response

interface CitiesDataSource {

    suspend fun getCitiesByText(
        query: String
    ): Response<List<GetSearchCitiesResponse>>

    suspend fun getCurrentCity(): CurrentCity?

    suspend fun setCurrentCity(city: CurrentCity)

}
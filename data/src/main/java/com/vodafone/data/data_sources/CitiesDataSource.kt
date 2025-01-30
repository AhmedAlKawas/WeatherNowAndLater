package com.vodafone.data.data_sources

import com.vodafone.core.models.GetSearchCitiesResponse
import retrofit2.Response

interface CitiesDataSource {

    suspend fun getCitiesByText(
        query: String
    ): Response<List<GetSearchCitiesResponse>>

}
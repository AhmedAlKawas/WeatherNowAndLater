package com.vodafone.data.repos.citiies

import com.vodafone.core.models.GetSearchCitiesResponse
import retrofit2.Response

interface CitiesRepo {

    suspend fun getCitiesByText(query: String): Response<List<GetSearchCitiesResponse>>

}
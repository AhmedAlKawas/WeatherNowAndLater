package com.vodafone.data.repos.citiies

import com.vodafone.core.models.GetSearchCitiesResponse
import com.vodafone.data.data_sources.CitiesDataSource
import retrofit2.Response

class CitiesRepoImpl(private val citiesDataSource: CitiesDataSource) : CitiesRepo {

    override suspend fun getCitiesByText(query: String): Response<List<GetSearchCitiesResponse>> =
        citiesDataSource.getCitiesByText(query)

}
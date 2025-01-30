package com.vodafone.framework.network.data_sources

import com.vodafone.core.models.GetSearchCitiesResponse
import com.vodafone.data.data_sources.CitiesDataSource
import com.vodafone.data.utils.Constants.API_KEY
import com.vodafone.framework.network.services.CitiesServices
import retrofit2.Response

class CitiesDataSourceImpl(private val citiesServices: CitiesServices) : CitiesDataSource {

    override suspend fun getCitiesByText(query: String): Response<List<GetSearchCitiesResponse>> =
        citiesServices.getCitiesByText(query, API_KEY)

}
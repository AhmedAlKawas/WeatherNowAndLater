package com.vodafone.data.data_sources

import com.vodafone.core.models.GetSearchCitiesResponse
import com.vodafone.data.services.CitiesServices
import com.vodafone.data.utils.Constants.API_KEY
import com.vodafone.data.utils.Constants.CITIES_LIMIT
import retrofit2.Response
import javax.inject.Inject

class CitiesDataSourceImpl @Inject constructor(private val citiesServices: CitiesServices) :
    CitiesDataSource {

    override suspend fun getCitiesByText(query: String): Response<List<GetSearchCitiesResponse>> =
        citiesServices.getCitiesByText(query, API_KEY, CITIES_LIMIT)

}
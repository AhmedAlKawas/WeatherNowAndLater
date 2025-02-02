package com.vodafone.data.data_sources.cities

import com.vodafone.core.models.CurrentCity
import com.vodafone.core.models.GetSearchCitiesResponse
import com.vodafone.data.room_db.daos.CitiesDao
import com.vodafone.data.services.CitiesServices
import com.vodafone.data.utils.Constants.API_KEY
import com.vodafone.data.utils.Constants.CITIES_LIMIT
import retrofit2.Response
import javax.inject.Inject

class CitiesDataSourceImpl @Inject constructor(
    private val citiesServices: CitiesServices,
    private val dao: CitiesDao
) :
    CitiesDataSource {

    override suspend fun getCitiesByText(query: String): Response<List<GetSearchCitiesResponse>> =
        citiesServices.getCitiesByText(query, API_KEY, CITIES_LIMIT)

    override suspend fun getCurrentCity(): CurrentCity? = dao.getCurrentCity()

    override suspend fun setCurrentCity(city: CurrentCity) {
        dao.setCurrentCity(city)
    }

}
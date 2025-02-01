package com.vodafone.city_input.repo

import com.vodafone.core.models.CurrentCity
import com.vodafone.core.models.GetSearchCitiesResponse
import retrofit2.Response

interface CitiesRepo {

    suspend fun getCitiesByText(query: String): Response<List<GetSearchCitiesResponse>>

    suspend fun setCurrentCity(currentCity: CurrentCity)

}
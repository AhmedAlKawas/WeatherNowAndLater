package com.vodafone.city_input.use_cases

import com.vodafone.core.models.GetSearchCitiesResponse
import com.vodafone.data.repos.citiies.CitiesRepo
import retrofit2.Response
import javax.inject.Inject

class GetCitiesByText @Inject constructor(private val repo: CitiesRepo) {

    suspend operator fun invoke(query: String): Response<List<GetSearchCitiesResponse>> =
        repo.getCitiesByText(query)

}
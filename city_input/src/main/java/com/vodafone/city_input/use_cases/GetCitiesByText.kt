package com.vodafone.city_input.use_cases

import com.vodafone.domain.models.City
import com.vodafone.domain.repos.CitiesRepo
import javax.inject.Inject

class GetCitiesByText @Inject constructor(private val repo: CitiesRepo) {

    suspend operator fun invoke(query: String): List<City>? =
        repo.getCitiesByText(query)

}
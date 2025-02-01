package com.vodafone.city_input.use_cases

import com.vodafone.city_input.repo.CitiesRepo
import com.vodafone.core.models.CurrentCity
import com.vodafone.core.models.GetSearchCitiesResponse
import javax.inject.Inject

class SetCurrentCity @Inject constructor(private val repo: CitiesRepo) {

    suspend operator fun invoke(responseCity: GetSearchCitiesResponse) : CurrentCity{

        val currentCity = CurrentCity(
            id = 1,
            name = responseCity.name ?: "",
            state = responseCity.state ?: "",
            country = responseCity.country ?: "",
            longitude = responseCity.lon ?: 0.0,
            latitude = responseCity.lat ?: 0.0
        )

        repo.setCurrentCity(currentCity)

        return currentCity

    }

}
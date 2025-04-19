package com.vodafone.domain.repos

import com.vodafone.domain.models.City

interface CitiesRepo {

    suspend fun getCitiesByText(query: String): List<City>?

    suspend fun setCurrentCity(currentCity: City)

}
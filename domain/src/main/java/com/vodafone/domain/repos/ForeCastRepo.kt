package com.vodafone.domain.repos

import com.vodafone.domain.models.City
import com.vodafone.domain.models.CityWeatherData

interface ForeCastRepo {

    suspend fun getCurrentCity(): City?

    suspend fun getDailyForeCast(city: City): List<CityWeatherData>?

}
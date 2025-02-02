package com.vodafone.forecast.repo

import com.vodafone.core.models.CurrentCity
import com.vodafone.core.models.getDailyForecastResponse.GetDailyForecastResponse
import retrofit2.Response

interface ForeCastRepo {

    suspend fun getCurrentCity(): CurrentCity?

    suspend fun getDailyForeCast(
        latitude: Double,
        longitude: Double
    ): Response<GetDailyForecastResponse>

}
package com.vodafone.data.services

import com.vodafone.data.models.dto.GetSearchCitiesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CitiesServices {

    @GET("geo/1.0/direct")
    suspend fun getCitiesByText(
        @Query("q") query: String,
        @Query("appid") apiKey: String,
        @Query("limit") limit: Int
    ): Response<List<GetSearchCitiesResponse>>

}
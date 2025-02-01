package com.vodafone.weather.repo

import com.vodafone.core.models.CurrentCity

interface CurrentCityRepo {

    suspend fun getCurrentCity(): CurrentCity?

}
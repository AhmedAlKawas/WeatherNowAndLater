package com.vodafone.weather.repo

import com.vodafone.data.models.entities.CurrentCityEntity

interface CurrentCityRepo {

    suspend fun getCurrentCity(): CurrentCityEntity?

}
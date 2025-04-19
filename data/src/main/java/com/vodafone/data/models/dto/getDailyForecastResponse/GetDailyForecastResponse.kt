package com.vodafone.data.models.dto.getDailyForecastResponse

import com.google.gson.annotations.SerializedName
import com.vodafone.core.models.getDailyForecastResponse.List

data class GetDailyForecastResponse(

    @SerializedName("cod") var cod: String? = null,
    @SerializedName("message") var message: Double? = null,
    @SerializedName("cnt") var cnt: Double? = null,
    @SerializedName("list") var list: ArrayList<List> = arrayListOf()

)

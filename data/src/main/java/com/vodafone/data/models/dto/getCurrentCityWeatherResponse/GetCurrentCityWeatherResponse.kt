package com.vodafone.core.models.getCurrentCityWeatherResponse

import com.google.gson.annotations.SerializedName

data class GetCurrentCityWeatherResponse(
    @SerializedName("weather") var weather: ArrayList<Weather> = arrayListOf(),
    @SerializedName("base") var base: String? = null,
    @SerializedName("main") var main: Main? = Main(),
    @SerializedName("visibility") var visibility: Double? = null,
    @SerializedName("dt") var dt: Double? = null,
    @SerializedName("timezone") var timezone: Int? = null,
    @SerializedName("id") var id: Double? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("cod") var cod: Double? = null
)

package com.vodafone.core.models

data class CityWeatherData(
    val name: String?,
    val state: String?,
    val country: String?,
    val temperature: Double?,
    val condition: String?,
    val weather: Weather?
)

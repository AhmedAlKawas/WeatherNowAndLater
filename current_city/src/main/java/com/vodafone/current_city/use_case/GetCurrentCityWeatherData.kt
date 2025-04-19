package com.vodafone.current_city.use_case

import com.vodafone.domain.models.CityWeatherData
import com.vodafone.domain.repos.CurrentCityWeatherRepo
import javax.inject.Inject

class GetCurrentCityWeatherData @Inject constructor(
    private val currentCityRepo: CurrentCityWeatherRepo
) {

    suspend operator fun invoke(): CityWeatherData? {

        val currentCity = currentCityRepo.getCurrentCity()

        if (currentCity?.latitude != null && currentCity.longitude != null) {

            val currentCityWeather =
                currentCityRepo.getCurrentCityWeather(
                    currentCity.latitude!!,
                    currentCity.longitude!!
                )


            if (currentCityWeather != null) {

                val cityWeatherData = currentCityWeather.copy(
                    name = currentCity.name,
                    state = currentCity.state,
                    country = currentCity.country
                )

                return cityWeatherData

            } else
                return null

        } else
            return null

    }

}
package com.vodafone.current_city.use_case

import com.vodafone.core.models.CityWeatherData
import com.vodafone.current_city.repo.CurrentCityWeatherRepo
import com.vodafone.data.utils.WeatherUtils.getWeatherFromIcon
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


            if (currentCityWeather.isSuccessful) {

                val cityWeatherData = CityWeatherData(
                    name = currentCity.name,
                    state = currentCity.state,
                    country = currentCity.country,
                    temperature = currentCityWeather.body()?.main?.temp,
                    condition = currentCityWeather.body()?.weather?.get(0)?.main,
                    weather = getWeatherFromIcon(currentCityWeather.body()?.weather?.get(0)?.icon)
                )
                return cityWeatherData

            } else
                return null

        } else
            return null

    }

}
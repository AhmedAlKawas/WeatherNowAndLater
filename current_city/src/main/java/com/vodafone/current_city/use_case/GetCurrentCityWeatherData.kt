package com.vodafone.current_city.use_case

import com.vodafone.core.models.CityWeatherData
import com.vodafone.core.models.Weather
import com.vodafone.current_city.repo.CurrentCityWeatherRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import javax.inject.Inject

class GetCurrentCityWeatherData @Inject constructor(private val currentCityRepo: CurrentCityWeatherRepo) {

    suspend operator fun invoke(scope: CoroutineScope): CityWeatherData? {

        val currentCityDeffer = scope.async {

            currentCityRepo.getCurrentCity()

        }

        val currentCity = currentCityDeffer.await()

        if (currentCity?.latitude != null) {

            val currentCityWeatherJob = scope.async {
                currentCityRepo.getCurrentCityWeather(currentCity.latitude, currentCity.longitude)
            }

            val currentCityWeather = currentCityWeatherJob.await()

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

    private fun getWeatherFromIcon(icon: String?): Weather {

        if (icon != null) {

            if (icon == "01d" || icon == "01n") {
                return Weather.ClearSky
            }
            if (icon == "02d" || icon == "02n") {
                return Weather.FewClouds
            }
            if (icon == "03d" || icon == "03n") {
                return Weather.ScatteredClouds
            }
            if (icon == "04d" || icon == "04n") {
                return Weather.BrokenClouds
            }
            if (icon == "09d" || icon == "09n") {
                return Weather.ShowerRain
            }
            if (icon == "10d" || icon == "10n") {
                return Weather.Rain
            }
            if (icon == "11d" || icon == "11n") {
                return Weather.Thunderstorm
            }
            if (icon == "13d" || icon == "13n") {
                return Weather.Snow
            }
            if (icon == "50d" || icon == "50n") {
                return Weather.Mist
            }
            return Weather.Unknown

        } else
            return Weather.Unknown

    }

}
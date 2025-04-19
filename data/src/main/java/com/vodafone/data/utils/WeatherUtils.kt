package com.vodafone.data.utils

import com.vodafone.domain.models.Weather

object WeatherUtils {

    fun getWeatherFromIcon(icon: String?): Weather {

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
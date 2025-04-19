package com.vodafone.forecast.use_cases

import com.vodafone.domain.models.CityWeatherData
import com.vodafone.domain.repos.ForeCastRepo
import javax.inject.Inject

class GetForeCastData @Inject constructor(private val foreCastRepo: ForeCastRepo) {

    suspend operator fun invoke(): List<CityWeatherData>? {

        val currentCity = foreCastRepo.getCurrentCity()

        if (currentCity?.latitude != null && currentCity.longitude != null) {

            val dailyForeCast = foreCastRepo.getDailyForeCast(currentCity)

            return dailyForeCast

        } else
            return null

    }

}
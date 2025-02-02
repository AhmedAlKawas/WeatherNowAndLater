package com.vodafone.forecast.use_cases

import com.vodafone.core.models.CityWeatherData
import com.vodafone.data.utils.WeatherUtils.getWeatherFromIcon
import com.vodafone.forecast.repo.ForeCastRepo
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class GetForeCastData @Inject constructor(private val foreCastRepo: ForeCastRepo) {

    suspend operator fun invoke(): ArrayList<CityWeatherData>? {

        val currentCity = foreCastRepo.getCurrentCity()

        if (currentCity?.latitude != null) {

            val dailyForeCast =
                foreCastRepo.getDailyForeCast(currentCity.latitude, currentCity.longitude)

            if (dailyForeCast.isSuccessful && dailyForeCast.body()?.list?.isNotEmpty() == true) {

                val foreCastDataList: ArrayList<CityWeatherData> = ArrayList()

                for (listItem in dailyForeCast.body()!!.list) {

                    foreCastDataList.add(
                        CityWeatherData(
                            name = currentCity.name,
                            state = currentCity.state,
                            country = currentCity.country,
                            temperature = listItem.temp?.day,
                            condition = listItem.weather[0].main,
                            weather = getWeatherFromIcon(listItem.weather[0].icon)
                        )
                    )

                }

                return foreCastDataList

            } else
                return null

        } else
            return null

    }

}
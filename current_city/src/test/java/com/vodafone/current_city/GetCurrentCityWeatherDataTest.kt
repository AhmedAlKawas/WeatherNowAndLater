package com.vodafone.current_city

import com.vodafone.core.models.CityWeatherData
import com.vodafone.core.models.CurrentCity
import com.vodafone.core.models.getCurrentCityWeatherResponse.GetCurrentCityWeatherResponse
import com.vodafone.core.models.getCurrentCityWeatherResponse.Main
import com.vodafone.core.models.getCurrentCityWeatherResponse.Weather
import com.vodafone.current_city.repo.CurrentCityWeatherRepo
import com.vodafone.current_city.use_case.GetCurrentCityWeatherData
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import retrofit2.Response

class GetCurrentCityWeatherDataTest {

    private lateinit var getCurrentCityWeatherData: GetCurrentCityWeatherData
    private lateinit var repo: CurrentCityWeatherRepo

    @Before
    fun setup() {

        repo = mock() // Mock the repository
        getCurrentCityWeatherData = GetCurrentCityWeatherData(repo) // Inject the mock repository

    }

    @Test
    fun `invoke should return CityWeatherData when current city and weather data are available`() =
        runTest {
            // Arrange
            val currentCityFromDataBase = CurrentCity(
                1, "New York", "NY", "USA", 40.7128, -74.0060
            )
            val weatherResponse = GetCurrentCityWeatherResponse(
                main = Main(temp = 72.5),
                weather = arrayListOf(Weather(main = "Clear", icon = "01d"))
            )
            val expectedCityWeatherData =
                CityWeatherData(
                    "New York",
                    "NY",
                    "USA",
                    72.5,
                    "Clear",
                    com.vodafone.core.models.Weather.ClearSky
                )

            whenever(repo.getCurrentCity()).thenReturn(currentCityFromDataBase)
            whenever(
                repo.getCurrentCityWeather(
                    any(),
                    any()
                )
            ).thenReturn(Response.success(weatherResponse))

            // Act
            val result = getCurrentCityWeatherData()

            // Assert
            assertEquals(expectedCityWeatherData, result)
        }

    @Test
    fun `invoke should return null when current city is null`() = runTest {
        // Arrange
        whenever(repo.getCurrentCity()).thenReturn(null)

        // Act
        val result = getCurrentCityWeatherData()

        // Assert
        assertNull(result)
    }

    @Test
    fun `invoke should return null when weather data fetch fails`() = runTest {
        // Arrange
        val currentCityFromDataBase = CurrentCity(
            1, "New York", "NY", "USA", 40.7128, -74.0060
        )

        whenever(repo.getCurrentCity()).thenReturn(currentCityFromDataBase)

        val responseBody: ResponseBody = ResponseBody.create(null, "Not found")

        whenever(repo.getCurrentCityWeather(any(), any())).thenReturn(
            Response.error(404, responseBody)
        )

        // Act
        val result = getCurrentCityWeatherData()

        // Assert
        assertNull(result)
    }

    @Test
    fun `invoke should return null when latitude or longitude is null`() = runTest {
        // Arrange
        val currentCityFromDataBase = CurrentCity(
            1, "New York", "NY", "USA", null, null
        )

        whenever(repo.getCurrentCity()).thenReturn(currentCityFromDataBase)

        // Act
        val result = getCurrentCityWeatherData()

        // Assert
        assertNull(result)
    }

}
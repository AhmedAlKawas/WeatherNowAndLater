package com.vodafone.current_city

import com.vodafone.current_city.use_case.GetCurrentCityWeatherData
import com.vodafone.domain.models.City
import com.vodafone.domain.models.CityWeatherData
import com.vodafone.domain.models.Weather
import com.vodafone.domain.repos.CurrentCityWeatherRepo
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

class GetCityEntityWeatherDataTest {

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
            val currentCityFromDataBase = City(
                "New York", "NY", "USA", 40.7128, -74.0060
            )
            val expectedCityWeatherData =
                CityWeatherData(
                    "New York",
                    "NY",
                    "USA",
                    72.5,
                    "Clear",
                    Weather.ClearSky
                )

            whenever(repo.getCurrentCity()).thenReturn(currentCityFromDataBase)
            whenever(
                repo.getCurrentCityWeather(
                    any(),
                    any()
                )
            ).thenReturn(expectedCityWeatherData)

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
        val currentCityFromDataBase = City(
            "New York", "NY", "USA", 40.7128, -74.0060
        )

        whenever(repo.getCurrentCity()).thenReturn(currentCityFromDataBase)

        whenever(repo.getCurrentCityWeather(any(), any())).thenReturn(null)

        // Act
        val result = getCurrentCityWeatherData()

        // Assert
        assertNull(result)
    }

    @Test
    fun `invoke should return null when latitude or longitude is null`() = runTest {
        // Arrange
        val currentCityFromDataBase = City(
            "New York", "NY", "USA", null, null
        )

        whenever(repo.getCurrentCity()).thenReturn(currentCityFromDataBase)

        // Act
        val result = getCurrentCityWeatherData()

        // Assert
        assertNull(result)
    }

}
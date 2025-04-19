package com.vodafone.city_input

import app.cash.turbine.test
import com.vodafone.city_input.presentation.CitiesViewModel
import com.vodafone.city_input.use_cases.GetCitiesByText
import com.vodafone.city_input.use_cases.SetCurrentCity
import com.vodafone.domain.models.City
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any

@ExperimentalCoroutinesApi
class CitiesViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var mockGetCitiesByText: GetCitiesByText
    private lateinit var mockSetCurrentCityUseCase: SetCurrentCity
    private lateinit var viewModel: CitiesViewModel

    @Before
    fun setUp() {

        Dispatchers.setMain(testDispatcher) // Set test dispatcher for Main
        mockGetCitiesByText = mock()
        mockSetCurrentCityUseCase = mock()
        viewModel = CitiesViewModel(mockGetCitiesByText, mockSetCurrentCityUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset Main dispatcher after tests
    }

    @Test
    fun `getCities should emit empty list initially and then emit cities when successful`() =
        runTest {
            // Arrange
            val query = "Cairo"
            val expectedCities = listOf(
                City(
                    "Cairo", "State",
                    country = null,
                    latitude = null,
                    longitude = null
                )
            )
            `when`(mockGetCitiesByText.invoke(any())).thenReturn(expectedCities)

            // Act
            val job = launch {
                viewModel.getCities(query)
            }

            //Assert
            viewModel.resultCities.test {

                // Initial emission (empty list)
                assertEquals(emptyList<City>(), awaitItem())

                // Verify the cities are emitted
                assertEquals(expectedCities, awaitItem())
            }

            job.cancel()

        }

    @Test
    fun `setCurrentCity should call use case and emit the result`() = runTest {
        // Arrange
        val responseCity = City(
            "Cairo",
            "Cairo",
            country = "Eg",
            latitude = 1.0,
            longitude = 1.0
        )
        val expectedCity = City(
            name = "Cairo",
            state = "Cairo",
            country = "Eg",
            latitude = 1.0,
            longitude = 1.0
        )

        // Act
        val job = launch {
            viewModel.setCurrentCity(responseCity)
        }

        // Assert
        viewModel.currentCity.test {
            assertEquals(expectedCity, awaitItem()) // Verify emitted value
        }

        verify(mockSetCurrentCityUseCase).invoke(responseCity) // Ensure the use case was called

        job.cancel()
    }

}
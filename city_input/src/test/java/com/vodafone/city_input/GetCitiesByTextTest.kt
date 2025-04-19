package com.vodafone.city_input

import com.vodafone.city_input.use_cases.GetCitiesByText
import com.vodafone.domain.models.City
import com.vodafone.domain.repos.CitiesRepo
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any

class GetCitiesByTextTest {

    private lateinit var getCitiesByText: GetCitiesByText
    private lateinit var mockRepo: CitiesRepo

    @Before
    fun setup() {

        mockRepo = mock() // Mock the repository
        getCitiesByText = GetCitiesByText(mockRepo) // Inject the mock repository

    }

    @Test
    fun `invoke should return cities from repo`() = runBlocking {
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
        `when`(mockRepo.getCitiesByText(any())).thenReturn(expectedCities)

        // Act
        val result = getCitiesByText(query)

        // Assert
        assertEquals(expectedCities, result)
    }

    @Test
    fun `invoke should return error response when repo fails`() = runBlocking {
        // Arrange
        val query = "Unknown"
        `when`(mockRepo.getCitiesByText(any())).thenReturn(null)

        // Act
        val result = getCitiesByText(query)

        // Assert
        assertEquals(null, result)
    }

}
package com.vodafone.city_input

import com.vodafone.city_input.repo.CitiesRepo
import com.vodafone.city_input.use_cases.GetCitiesByText
import com.vodafone.core.models.GetSearchCitiesResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import retrofit2.Response

class GetCitiesByTextTest {

    private lateinit var getCitiesByText: GetCitiesByText
    private lateinit var mockRepo: CitiesRepo

    @Before
    fun setup(){

        mockRepo = mock() // Mock the repository
        getCitiesByText = GetCitiesByText(mockRepo) // Inject the mock repository

    }

    @Test
    fun `invoke should return cities from repo`() = runBlocking {
        // Arrange
        val query = "Cairo"
        val expectedCities = listOf(GetSearchCitiesResponse("Cairo", 1.0))
        `when`(mockRepo.getCitiesByText(any())).thenReturn(Response.success(expectedCities))

        // Act
        val result = getCitiesByText(query)

        // Assert
        assertEquals(expectedCities, result.body())
    }

    @Test
    fun `invoke should return error response when repo fails`() = runBlocking {
        // Arrange
        val query = "Unknown"
        `when`(mockRepo.getCitiesByText(any())).thenReturn(Response.error(404, null))

        // Act
        val result = getCitiesByText(query)

        // Assert
        assertEquals(404, result.code())
        assertEquals(null, result.body())
    }

}
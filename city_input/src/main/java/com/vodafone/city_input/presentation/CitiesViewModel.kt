package com.vodafone.city_input.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vodafone.city_input.use_cases.GetCitiesByText
import com.vodafone.city_input.use_cases.SetCurrentCity
import com.vodafone.core.models.CurrentCity
import com.vodafone.core.models.GetSearchCitiesResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val getCitiesByText: GetCitiesByText,
    private val setCurrentCityUseCase: SetCurrentCity
) :
    ViewModel() {

    private val _resultCities: MutableSharedFlow<List<GetSearchCitiesResponse>> =
        MutableSharedFlow()
    val resultCities: SharedFlow<List<GetSearchCitiesResponse>> = _resultCities

    private val _currentCity: MutableSharedFlow<CurrentCity> = MutableSharedFlow()
    val currentCity: SharedFlow<CurrentCity> = _currentCity

    fun getCities(query: String) {

        viewModelScope.launch {

            _resultCities.emit(emptyList())

            val cities = getCitiesByText(query)

            if (cities.isSuccessful && cities.body() != null && cities.body()!!.isNotEmpty()) {

                _resultCities.emit(cities.body()!!)

            }

        }

    }

    fun setCurrentCity(responseCity: GetSearchCitiesResponse) {

        viewModelScope.launch {

            val currentCity = setCurrentCityUseCase(responseCity)
            _currentCity.emit(currentCity)

        }

    }

}
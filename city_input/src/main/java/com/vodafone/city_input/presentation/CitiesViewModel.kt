package com.vodafone.city_input.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vodafone.city_input.use_cases.GetCitiesByText
import com.vodafone.city_input.use_cases.SetCurrentCity
import com.vodafone.domain.models.City
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

    private val _resultCities: MutableSharedFlow<List<City>> =
        MutableSharedFlow()
    val resultCities: SharedFlow<List<City>> = _resultCities

    private val _currentCity: MutableSharedFlow<City> = MutableSharedFlow()
    val currentCity: SharedFlow<City> = _currentCity

    fun getCities(query: String) {

        viewModelScope.launch {

            _resultCities.emit(emptyList())

            val cities = getCitiesByText(query)

            if (!cities.isNullOrEmpty()) {

                _resultCities.emit(cities)

            }

        }

    }

    fun setCurrentCity(city: City) {

        viewModelScope.launch {

            setCurrentCityUseCase(city)
            _currentCity.emit(city)

        }

    }

}
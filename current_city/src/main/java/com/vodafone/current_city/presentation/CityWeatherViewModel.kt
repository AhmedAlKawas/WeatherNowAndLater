package com.vodafone.current_city.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vodafone.current_city.states.GetCityWeatherDataState
import com.vodafone.current_city.use_case.GetCurrentCityWeatherData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CityWeatherViewModel @Inject constructor(
    private val useCase: GetCurrentCityWeatherData,
) :
    ViewModel() {

    private val _cityWeatherData: MutableStateFlow<GetCityWeatherDataState> =
        MutableStateFlow(GetCityWeatherDataState.LoadingState)
    val cityWeatherData: StateFlow<GetCityWeatherDataState> = _cityWeatherData

    suspend fun getCurrentCityData() {

        _cityWeatherData.emit(GetCityWeatherDataState.LoadingState)

        val currentCityWeatherData = useCase(viewModelScope)

        if (currentCityWeatherData != null) {
            _cityWeatherData.emit(
                GetCityWeatherDataState.SuccessState(
                    currentCityWeatherData
                )
            )
        } else {
            _cityWeatherData.emit(
                GetCityWeatherDataState.ErrorState
            )
        }

    }

}
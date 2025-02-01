package com.vodafone.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vodafone.core.models.CurrentCity
import com.vodafone.weather.repo.CurrentCityRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val currentCityRepo: CurrentCityRepo) :
    ViewModel() {

    suspend fun getCurrentCity(): CurrentCity? {

        val currentCity = viewModelScope.async {

            currentCityRepo.getCurrentCity()

        }

        return currentCity.await()

    }
}
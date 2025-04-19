package com.vodafone.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vodafone.data.models.entities.CurrentCityEntity
import com.vodafone.weather.repo.CurrentCityRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val currentCityRepo: CurrentCityRepo) :
    ViewModel() {

    suspend fun getCurrentCity(): CurrentCityEntity? {

        val currentCity = viewModelScope.async {

            currentCityRepo.getCurrentCity()

        }

        return currentCity.await()

    }
}
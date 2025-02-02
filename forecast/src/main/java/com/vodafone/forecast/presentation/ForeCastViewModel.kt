package com.vodafone.forecast.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vodafone.forecast.intent.ForeCastIntent
import com.vodafone.forecast.states.ForecastState
import com.vodafone.forecast.use_cases.GetForeCastData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForeCastViewModel @Inject constructor(private val getForeCastDataUseCase: GetForeCastData) :
    ViewModel() {

    private val foreCastChannel = Channel<ForeCastIntent>(Channel.UNLIMITED)

    private val _foreCastFlow = MutableStateFlow<ForecastState>(ForecastState.LoadingState)
    val foreCastFlow: StateFlow<ForecastState> get() = _foreCastFlow

    init {
        identifyIntent()
    }

    private fun identifyIntent() {
        viewModelScope.launch {

            foreCastChannel.consumeAsFlow().collect {

                when (it) {

                    is ForeCastIntent.GetForeCastData -> getForeCastData()

                }

            }

        }
    }

    private suspend fun getForeCastData() {

        _foreCastFlow.emit(ForecastState.LoadingState)

        try {
            val foreCastData = getForeCastDataUseCase()

            if (!foreCastData.isNullOrEmpty()) {
                _foreCastFlow.emit(ForecastState.SuccessState(foreCastData))
            } else {
                _foreCastFlow.emit(ForecastState.ErrorState)
            }
        } catch (e: Exception) {
            _foreCastFlow.emit(ForecastState.ErrorState)
        }

    }

    fun screenLaunched() {
        viewModelScope.launch {
            foreCastChannel.send(ForeCastIntent.GetForeCastData)
        }
    }

}
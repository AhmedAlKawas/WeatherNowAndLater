package com.vodafone.forecast.intent

sealed interface ForeCastIntent {

    data object GetForeCastData : ForeCastIntent

}
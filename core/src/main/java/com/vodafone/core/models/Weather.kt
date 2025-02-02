package com.vodafone.core.models

import com.vodafone.core.R

enum class Weather(val iconRes: Int) {

    ClearSky(R.drawable.clear_sky),
    FewClouds(R.drawable.few_cloud),
    ScatteredClouds(R.drawable.scattered_cloud),
    BrokenClouds(R.drawable.broken_cloud),
    ShowerRain(R.drawable.shower_rain),
    Rain(R.drawable.rain),
    Thunderstorm(R.drawable.thunderstorms),
    Snow(R.drawable.snowflake),
    Mist(R.drawable.mist),
    Unknown(R.drawable.unknown)

}
package com.vodafone.current_city.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vodafone.core.models.CityWeatherData

@Composable
fun WeatherDataScreen(
    weatherData: CityWeatherData,
    onShow7DayWeather: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = weatherData.name ?: "",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "${weatherData.state}, ${weatherData.country}",
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        weatherData.weather?.iconRes?.let { painterResource(id = it) }?.let {
            Image(
                painter = it,
                contentDescription = "Weather Icon",
                modifier = Modifier.size(64.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "${weatherData.temperature?.toInt()}°C",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = weatherData.condition ?: "",
            fontSize = 18.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onShow7DayWeather,
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Show 7-Day Weather")
        }
    }
}
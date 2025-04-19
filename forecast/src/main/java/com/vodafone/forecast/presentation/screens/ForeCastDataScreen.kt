package com.vodafone.forecast.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vodafone.domain.models.CityWeatherData

@Composable
fun ForeCastDataScreen(foreCastDataList: List<CityWeatherData>) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Daily Forecast",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(foreCastDataList) { weather ->
                WeatherRow(weather)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

    }

}

@Composable
fun WeatherRow(weather: CityWeatherData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        weather.weather?.let { painterResource(id = it.iconRes) }?.let {
            Image(
                painter = it,
                contentDescription = "Weather Icon",
                modifier = Modifier.size(48.dp)
            )
        }

        Text(
            text = "${weather.temperature?.toInt()}°C",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        weather.condition?.let {
            Text(
                text = it,
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
    }
}
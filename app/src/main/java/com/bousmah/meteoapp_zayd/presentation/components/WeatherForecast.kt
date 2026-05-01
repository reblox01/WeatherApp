package com.bousmah.meteoapp_zayd.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bousmah.meteoapp_zayd.domain.model.Forecast

@Composable
fun WeatherForecast(
    forecasts: List<Forecast>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "7-Day Forecast",
            fontSize = 20.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(forecasts) { forecast ->
                ForecastItem(forecast = forecast)
            }
        }
    }
}

@Composable
fun ForecastItem(
    forecast: Forecast,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = forecast.date,
            color = Color.White.copy(alpha = 0.7f),
            fontSize = 12.sp
        )
        AsyncImage(
            model = "https://openweathermap.org/img/wn/${forecast.icon}.png",
            contentDescription = forecast.description,
            modifier = Modifier.size(40.dp)
        )
        Text(
            text = "${forecast.maxTemp.toInt()}°",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Text(
            text = "${forecast.minTemp.toInt()}°",
            color = Color.White.copy(alpha = 0.5f),
            fontSize = 14.sp
        )
    }
}

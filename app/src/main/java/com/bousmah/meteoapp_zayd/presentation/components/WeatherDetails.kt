package com.bousmah.meteoapp_zayd.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bousmah.meteoapp_zayd.domain.model.Weather

@Composable
fun WeatherDetails(
    weather: Weather,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            WeatherDetailItem(
                value = "${weather.humidity}%",
                unit = "Humidity",
                icon = Icons.Default.WaterDrop
            )
            WeatherDetailItem(
                value = "${weather.windSpeed} km/h",
                unit = "Wind",
                icon = Icons.Default.Air
            )
            WeatherDetailItem(
                value = "${weather.feelsLike.toInt()}°C",
                unit = "Feels Like",
                icon = Icons.Default.Thermostat
            )
            WeatherDetailItem(
                value = "${weather.uvIndex}",
                unit = "UV Index",
                icon = Icons.Default.WbSunny
            )
        }
    }
}

@Composable
fun WeatherDetailItem(
    value: String,
    unit: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            color = Color.White,
            fontSize = 14.sp
        )
        Text(
            text = unit,
            color = Color.White.copy(alpha = 0.6f),
            fontSize = 12.sp
        )
    }
}

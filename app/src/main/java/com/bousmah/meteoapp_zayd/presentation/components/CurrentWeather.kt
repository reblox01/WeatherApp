package com.bousmah.meteoapp_zayd.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bousmah.meteoapp_zayd.domain.model.Weather

@Composable
fun CurrentWeather(
    weather: Weather,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = weather.cityName,
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        
        // Animated Weather Icon
        WeatherAnimation(
            iconCode = weather.icon,
            modifier = Modifier.size(160.dp)
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "${weather.temperature.toInt()}°C",
            style = MaterialTheme.typography.displayLarge,
            color = Color.White,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = weather.description.replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.titleLarge,
            color = Color.White.copy(alpha = 0.7f)
        )
    }
}

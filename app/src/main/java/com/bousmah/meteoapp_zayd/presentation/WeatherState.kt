package com.bousmah.meteoapp_zayd.presentation

import com.bousmah.meteoapp_zayd.domain.model.Forecast
import com.bousmah.meteoapp_zayd.domain.model.Weather

data class WeatherState(
    val weather: Weather? = null,
    val forecast: List<Forecast> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

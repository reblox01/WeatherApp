package com.bousmah.meteoapp_zayd.domain.repository

import com.bousmah.meteoapp_zayd.domain.model.Forecast
import com.bousmah.meteoapp_zayd.domain.model.Weather

interface WeatherRepository {
    suspend fun getWeatherByCity(city: String): Result<Weather>
    suspend fun getWeatherByLocation(lat: Double, lon: Double): Result<Weather>
    suspend fun get7DayForecast(lat: Double, lon: Double): Result<List<Forecast>>
}

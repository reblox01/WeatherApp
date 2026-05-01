package com.bousmah.meteoapp_zayd.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bousmah.meteoapp_zayd.domain.location.LocationTracker
import com.bousmah.meteoapp_zayd.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
) : ViewModel() {

    private val _state = MutableStateFlow(WeatherState())
    val state = _state.asStateFlow()

    private var searchJob: Job? = null

    fun loadWeatherInfo(city: String? = null) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            
            if (city != null) {
                if (!validateCityName(city)) {
                    _state.update { it.copy(isLoading = false, error = "Invalid city name. Use only letters and spaces.") }
                    return@launch
                }
                fetchWeatherByCity(city)
            } else {
                fetchWeatherByLocation()
            }
        }
    }

    private fun validateCityName(city: String): Boolean {
        val regex = "^[a-zA-Z\\s\\-]{1,100}$".toRegex()
        return regex.matches(city)
    }

    fun onSearchQueryChange(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            if (query.isNotBlank()) {
                loadWeatherInfo(query)
            }
        }
    }

    private suspend fun fetchWeatherByLocation() {
        locationTracker.getCurrentLocation()?.let { location ->
            val weatherResult = repository.getWeatherByLocation(location.latitude, location.longitude)
            val forecastResult = repository.get7DayForecast(location.latitude, location.longitude)
            
            _state.update { it.copy(
                weather = weatherResult.getOrNull(),
                forecast = forecastResult.getOrDefault(emptyList()),
                isLoading = false,
                error = if (weatherResult.isFailure) "Could not fetch weather data." else null
            ) }
        } ?: run {
            _state.update { it.copy(isLoading = false, error = "Couldn't retrieve location. Make sure to grant permission and enable GPS.") }
        }
    }

    private suspend fun fetchWeatherByCity(city: String) {
        val weatherResult = repository.getWeatherByCity(city)
        weatherResult.onSuccess { weather ->
            // For simplicity, we just show current weather for city search in this version
            _state.update { it.copy(weather = weather, forecast = emptyList(), isLoading = false, error = null) }
        }.onFailure {
            _state.update { it.copy(isLoading = false, error = "City not found or network error.") }
        }
    }
}

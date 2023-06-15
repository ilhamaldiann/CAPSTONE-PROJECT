package com.example.weatherapp.ui.screen.detail

import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.remote.response.CurrentWeatherResponse
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DetailViewModel(
    private val repository: WeatherRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<CurrentWeatherResponse>>> = MutableStateFlow(
        UiState.Loading)
    val uiState: StateFlow<UiState<List<CurrentWeatherResponse>>> = _uiState
}
package com.example.weatherapp.ui.screen.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.data.remote.response.CurrentWeatherResponse
import com.example.weatherapp.data.local.entity.BookmarkEntity
import com.example.weatherapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _bookmarkCity : MutableState<List<BookmarkEntity>> = mutableStateOf(listOf())
    val bookmarkCity: MutableState<List<BookmarkEntity>> get() = _bookmarkCity

    private val _uiState: MutableStateFlow<UiState<List<CurrentWeatherResponse>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<CurrentWeatherResponse>>> = _uiState

    fun clearWeatherData() {
        repository.clearWeatherData()
    }

    fun getWeatherData(cityName: String, airQuality: String = "no") {
        viewModelScope.launch {
            repository.getWeatherData(cityName, airQuality)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { data ->
                    _uiState.value = UiState.Success(data)
                    Log.i("HomeViewModel", "getWeatherData: ${data.size}")
                }
        }
    }

    fun getBookmarkCity() {
        repository.getBookmarkCity().observeForever {
            _bookmarkCity.value = it
        }
    }
}
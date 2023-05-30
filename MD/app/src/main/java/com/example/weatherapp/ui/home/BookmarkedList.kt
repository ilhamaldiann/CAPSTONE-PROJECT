package com.example.weatherapp.ui.home

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.weatherapp.components.WeatherCard
import com.example.weatherapp.data.WeatherData
import com.example.weatherapp.data.local.BookmarkEntity
import com.example.weatherapp.ui.common.UiState
import com.example.weatherapp.ui.viewmodel.HomeViewModel

@Composable
fun BookmarkedList(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    list: ArrayList<WeatherData>
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                list.map {
                    Log.d("Andre", it.city)
                    viewModel.getWeatherData(it.city)
                }
            }
            is UiState.Success -> {
                LazyColumn{
                    items(uiState.data){
                        WeatherCard(
                            cityName = it.location.name,
                            status = it.current.condition.text,
                            tempC = it.current.tempC,
                            modifier = modifier
                        )
                    }
                }
            }
            is UiState.Error -> {}
        }
    }
}
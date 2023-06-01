package com.example.weatherapp.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherapp.components.WeatherCard
import com.example.weatherapp.data.WeatherData
import com.example.weatherapp.ui.common.UiState

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
                    viewModel.getWeatherData(it.city)
                }
            }
            is UiState.Success -> {
                LazyColumn(
                    contentPadding = PaddingValues(56.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ){
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
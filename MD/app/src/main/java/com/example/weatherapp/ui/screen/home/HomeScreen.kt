package com.example.weatherapp.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherapp.ui.components.WeatherCard
import com.example.weatherapp.ui.common.UiState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    navigateToDetail: (String) -> Unit
) {
    val bookmarkCity by viewModel.bookmarkCity.observeAsState()
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                bookmarkCity?.map {
                    viewModel.getWeatherData(it.cityName)
                }
            }
            is UiState.Success -> {
                LazyColumn(
                    contentPadding = PaddingValues(56.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(uiState.data) {
                        WeatherCard(
                            cityName = it.location.name,
                            status = it.current.condition.text,
                            tempC = it.current.tempC,
                            modifier = modifier.clickable {
                                navigateToDetail(it.location.name)
                            }
                        )
                    }
                }
            }
            is UiState.Error -> {}
        }
    }
}
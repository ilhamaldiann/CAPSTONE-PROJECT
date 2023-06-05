package com.example.weatherapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.utils.doubleToInt
import com.example.weatherapp.utils.gradientBackground

@Composable
fun WeatherCard(
    modifier: Modifier = Modifier,
    cityName: String,
    status: String,
    tempC: Double,
) {
    val icon: Painter
    val background: List<Color>
    when (status) {
        "Sunny" -> {
            icon = painterResource(R.drawable.ic_sunny)
            background = listOf(Color(0xFFFFE4B5), Color(0xFFFFFFCC))
        }
        "Rainy" -> {
            icon = painterResource(R.drawable.ic_rainy)
            background = listOf(Color(0xFFADD8E6), Color(0xFF87CEFA))
        }
        "Cloudy" -> {
            icon = painterResource(R.drawable.cloudy2)
            background = listOf(Color(0xFFD3D3D3), Color(0xFFFFE4B5))
        }
        "Drizzly" -> {
            icon = painterResource(R.drawable.ic_drizzly)
            background = listOf(Color(0xFF87CEEB), Color(0xFFECECEC))
        }
        "SunShower" -> {
            icon = painterResource(R.drawable.ic_sun_shower)
            background = listOf(Color(0xFF87CEEB), Color(0xFFFFFFCC))
        }
        "Storm" -> {
            icon = painterResource(R.drawable.ic_storm)
            background = listOf(Color(0xFF4B0082), Color(0xFF000080))
        }
        "Foggy" -> {
            icon = painterResource(R.drawable.ic_foggy)
            background = listOf(Color(0xFFF5F5F5), Color(0xFFF0F0F0))
        }
        else -> {
            icon = painterResource(R.drawable.ic_partly_cloudy)
            background = listOf(Color(0xFF87CEEB), Color(0xFFFFE4B5))
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(110.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(100.dp),
            border = BorderStroke(1.dp, Color(0x80C5C5C5)),
            shape = RoundedCornerShape(20.dp)
        ) {
            Box(
                modifier = modifier.gradientBackground(background, 90f)
            )

            Column(
                modifier = modifier
                    .padding(horizontal = 20.dp, vertical = 16.dp),
            ) {
                Text(
                    text = cityName,
                    style = MaterialTheme.typography.h5
                )
                Text(
                    text = status,
                    style = MaterialTheme.typography.h6
                )
            }
        }
        Image(
            modifier = modifier
                .size(120.dp)
                .align(Alignment.CenterEnd)
                .offset(x = (-5).dp, y = (-5).dp),
            painter = icon,
            contentDescription = null,
        )
        Card(
            modifier = modifier
                .width(70.dp)
                .height(30.dp)
                .align(Alignment.BottomCenter),
            elevation = 0.dp,
            backgroundColor = Color(0x66FFFFFF),
            border = BorderStroke(0.3.dp,Color(0x80A3A3A3)),
            shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp, bottomStart = 8.dp, bottomEnd = 8.dp)
        ) {
            Text(
                modifier = modifier
                    .padding(top = 5.dp),
                text = "${doubleToInt(tempC)}℃",
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun WeatherCardPreview() {
    WeatherAppTheme {
        WeatherCard(cityName = "Banda Aceh", status = "Sunny", tempC = 28.2)
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun WeatherCardPreview2() {
    WeatherAppTheme {
        WeatherCard(cityName = "Banda Aceh", status = "Rainy", tempC = 28.2)
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun WeatherCardPreview3() {
    WeatherAppTheme {
        WeatherCard(cityName = "Banda Aceh", status = "Cloudy", tempC = 28.2)
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun WeatherCardPreview4() {
    WeatherAppTheme {
        WeatherCard(cityName = "Banda Aceh", status = "Storm", tempC = 28.2)
    }
}
@Preview(showBackground = true, widthDp = 320)
@Composable
fun WeatherCardPreview5() {
    WeatherAppTheme {
        WeatherCard(cityName = "Banda Aceh", status = "Drizzly", tempC = 28.2)
    }
}
@Preview(showBackground = true, widthDp = 320)
@Composable
fun WeatherCardPreview6() {
    WeatherAppTheme {
        WeatherCard(cityName = "Banda Aceh", status = "Partly Sunny", tempC = 28.2)
    }
}
@Preview(showBackground = true, widthDp = 320)
@Composable
fun WeatherCardPreview7() {
    WeatherAppTheme {
        WeatherCard(cityName = "Banda Aceh", status = "SunShower", tempC = 28.2)
    }
}
@Preview(showBackground = true, widthDp = 320)
@Composable
fun WeatherCardPreview8() {
    WeatherAppTheme {
        WeatherCard(cityName = "Banda Aceh", status = "Foggy", tempC = 28.2)
    }
}
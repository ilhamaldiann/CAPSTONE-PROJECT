package com.example.weatherapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.WbCloudy
import androidx.compose.material.icons.rounded.WbSunny
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.utils.doubleToInt

@Composable
fun WeatherCard(
    modifier: Modifier = Modifier,
    cityName: String,
    status: String,
    tempC: Double
) {
    val icon: ImageVector
    val tint: Color
    val background: List<Color>
    when (status) {
        "Sunny" -> {
            icon = Icons.Rounded.WbSunny
            tint = Color(0xFFFFE37E)
            background = listOf(Color(0xFFF37100), Color(0xFFFFC800))
        }
        else -> {
            icon = Icons.Rounded.WbCloudy
            tint = Color(0xFFA8C5F8)
            background = listOf(Color(0xFF007BFF), Color(0xFF9EE9FF))
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
            shape = RoundedCornerShape(20.dp)
        ) {
            Box(
                modifier = modifier.background(Brush.verticalGradient(background))
            )

            Column(
                modifier = modifier
                    .padding(horizontal = 20.dp, vertical = 16.dp),
            ) {
                Text(
                    text = cityName,
                    fontSize = 28.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = status,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Medium
                )
            }
        }
        Icon(
            modifier = modifier
                .size(120.dp)
                .align(Alignment.CenterEnd)
                .offset(x = (-5).dp,y = (-5).dp),
            imageVector = icon,
            contentDescription = null,
            tint = tint
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
                fontFamily = FontFamily.SansSerif,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherCardPreview() {
    WeatherAppTheme {
        WeatherCard(cityName = "Semarang", status = "Sunny", tempC = 28.0)
    }
}
@Preview(showBackground = true)
@Composable
fun WeatherCardPreview2() {
    WeatherAppTheme {
        WeatherCard(cityName = "Jakarta", status = "Cloudy", tempC = 25.1)
    }
}
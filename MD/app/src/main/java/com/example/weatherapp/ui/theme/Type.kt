package com.example.weatherapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R

val Ubuntu = FontFamily(
    Font(R.font.ubuntu_regular),
    Font(R.font.ubuntu_medium, Medium),
    Font(R.font.ubuntu_bold, Bold),
    Font(R.font.ubuntu_italic, style = Italic),
)

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = Ubuntu,
        fontWeight = Bold,
        fontSize = 60.sp
    ),
    h2 = TextStyle(
        fontFamily = Ubuntu,
        fontWeight = Bold,
        fontSize = 52.sp
    ),
    h3 = TextStyle(
        fontFamily = Ubuntu,
        fontWeight = Bold,
        fontSize = 44.sp
    ),
    h4 = TextStyle(
        fontFamily = Ubuntu,
        fontWeight = Bold,
        fontSize = 36.sp
    ),
    h5 = TextStyle(
        fontFamily = Ubuntu,
        fontWeight = Bold,
        fontSize = 28.sp
    ),
    h6 = TextStyle(
        fontFamily = Ubuntu,
        fontWeight = Bold,
        fontSize = 20.sp
    ),
    body1 = TextStyle(
        fontFamily = Ubuntu,
        fontWeight = Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = Ubuntu,
        fontStyle = Italic,
        fontSize = 12.sp
    ),
)

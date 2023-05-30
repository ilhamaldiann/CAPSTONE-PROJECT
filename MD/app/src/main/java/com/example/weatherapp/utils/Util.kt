package com.example.weatherapp.utils

fun DoubleToInt(number: Double): String{
    val resultInteger: Int
    val a = number.toString().split(".")
    return if (a[1] == "0"){
        resultInteger = a[0].toInt()
        resultInteger.toString()
    }else{
        number.toString()
    }
}
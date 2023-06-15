package com.example.weatherapp.utils

import org.junit.Assert.*
import org.junit.Test

class DateFormatterTest{

    @Test
    fun `input format yyyy-LL-dd`(){
        val a = dateFormatter("2023-06-14")
        assertEquals("Rabu, 14 Juni 2023", a)
    }
    @Test
    fun `input format yyyy-L-d`(){
        val a = dateFormatter("2023-6-7")
        assertEquals("Rabu, 07 Juni 2023", a)
    }
}
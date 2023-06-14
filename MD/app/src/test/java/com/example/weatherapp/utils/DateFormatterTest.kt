package com.example.weatherapp.utils

import org.junit.Assert.*
import org.junit.Test
import java.time.LocalDate

class DateFormatterTest{

    @Test
    fun `input format yyyy-LL-dd`(){
        val a = dateFormatter(LocalDate.now().toString())
        assertEquals("Rabu, 14 Juni 2023", a)
    }
}
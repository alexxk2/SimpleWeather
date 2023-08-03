package com.example.simpleweather.data.weather.dto

data class WeatherInfoDto(
    val current: Current,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int
)
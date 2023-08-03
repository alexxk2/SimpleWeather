package com.example.simpleweather.domain.models

data class WeatherInfo(
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val wind_speed: Double,
    val weatherImage: String
)

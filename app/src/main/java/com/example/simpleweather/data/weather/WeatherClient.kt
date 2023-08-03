package com.example.simpleweather.data.weather

import com.example.simpleweather.data.weather.dto.WeatherDto

interface WeatherClient {

    suspend fun getWeatherInfo(lat: Double, lon: Double): WeatherDto
}
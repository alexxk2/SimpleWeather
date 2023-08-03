package com.example.simpleweather.data.weather

import com.example.simpleweather.data.weather.dto.WeatherInfoDto

interface WeatherClient {

    suspend fun getWeatherInfo(lat: Double, lon: Double): WeatherInfoDto
}
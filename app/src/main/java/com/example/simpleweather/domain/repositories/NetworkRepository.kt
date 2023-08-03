package com.example.simpleweather.domain.repositories

import com.example.simpleweather.domain.models.CityInfo
import com.example.simpleweather.domain.models.WeatherInfo

interface NetworkRepository {

    suspend fun getCityLocation(name: String): List<CityInfo>
    suspend fun getWeatherInfo(lat: Double, lon: Double): WeatherInfo
}
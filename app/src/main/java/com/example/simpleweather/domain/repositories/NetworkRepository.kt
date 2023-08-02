package com.example.simpleweather.domain.repositories

import com.example.simpleweather.domain.models.CityInfo

interface NetworkRepository {

    suspend fun getCityLocation(name: String): List<CityInfo>
    suspend fun getWeatherInfo()
}
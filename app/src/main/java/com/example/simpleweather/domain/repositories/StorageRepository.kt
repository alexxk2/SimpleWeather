package com.example.simpleweather.domain.repositories

import com.example.simpleweather.domain.models.CityInfo
import com.example.simpleweather.domain.models.WeatherInfo

interface StorageRepository {
    suspend fun addNewHistoryItem(cityInfo: CityInfo, weatherInfo: WeatherInfo)
    suspend fun deleteAllHistoryItems()
    suspend fun getAllHistoryItems(): List<CityInfo>
}
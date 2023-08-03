package com.example.simpleweather.domain.use_cases.storage

import com.example.simpleweather.domain.models.CityInfo
import com.example.simpleweather.domain.models.WeatherInfo
import com.example.simpleweather.domain.repositories.StorageRepository

class AddNewHistoryItemUseCase(private val storageRepository: StorageRepository) {

    suspend fun execute(cityInfo: CityInfo, weatherInfo: WeatherInfo) =
        storageRepository.addNewHistoryItem(cityInfo = cityInfo, weatherInfo = weatherInfo)
}
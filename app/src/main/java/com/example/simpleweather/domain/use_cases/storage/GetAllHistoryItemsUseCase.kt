package com.example.simpleweather.domain.use_cases.storage

import com.example.simpleweather.domain.models.CityInfo
import com.example.simpleweather.domain.repositories.StorageRepository

class GetAllHistoryItemsUseCase(private val storageRepository: StorageRepository) {

    suspend fun execute(): List<CityInfo> = storageRepository.getAllHistoryItems()
}
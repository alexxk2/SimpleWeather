package com.example.simpleweather.domain.use_cases.storage

import com.example.simpleweather.domain.repositories.StorageRepository

class DeleteAllHistoryItemsUseCase(private val storageRepository: StorageRepository) {

     suspend fun execute() = storageRepository.deleteAllHistoryItems()
}
package com.example.simpleweather.data.db

import com.example.simpleweather.data.db.dto.HistoryItemDto

interface RoomStorage {

    suspend fun addNewHistoryItem(historyItemDto: HistoryItemDto)
    suspend fun deleteAllHistoryItems()
    suspend fun getAllHistoryItems(): List<HistoryItemDto>
}
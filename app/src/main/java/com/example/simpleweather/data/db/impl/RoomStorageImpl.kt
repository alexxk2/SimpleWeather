package com.example.simpleweather.data.db.impl

import android.content.Context
import com.example.simpleweather.app.App
import com.example.simpleweather.data.db.HistoryDatabase
import com.example.simpleweather.data.db.RoomStorage
import com.example.simpleweather.data.db.dto.HistoryItemDto

class RoomStorageImpl(context: Context, historyDatabase: HistoryDatabase) : RoomStorage {

    private val historyDao = historyDatabase.historyDao()

    override suspend fun addNewHistoryItem(historyItemDto: HistoryItemDto) {
        historyDao.insert(historyItemDto)
    }

    override suspend fun deleteAllHistoryItems() {
        historyDao.deleteAll()
    }

    override suspend fun getAllHistoryItems(): List<HistoryItemDto> = historyDao.getAll()
}
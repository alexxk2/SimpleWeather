package com.example.simpleweather.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.simpleweather.data.db.dto.HistoryItemDto


@Dao
interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(historyItemDto: HistoryItemDto)

    @Update
    suspend fun update(historyItemDto: HistoryItemDto)

    @Delete
    suspend fun delete(historyItemDto: HistoryItemDto)

    @Query("SELECT * FROM history ORDER BY name ASC")
    suspend fun getAll(): List<HistoryItemDto>

    @Query("DELETE FROM history")
    suspend fun deleteAll()

}
package com.example.simpleweather.data.db.dto

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "history")
data class HistoryItemDto(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val country: String,
    val lat: Double,
    val lon: Double,
    val name: String,
    val state: String,
    @ColumnInfo(name = "flag_image_src")val flagImageSrc: String,
    val dt: Int?
): Parcelable

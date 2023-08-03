package com.example.simpleweather.data.repositories

import androidx.room.RoomDatabase
import com.example.simpleweather.data.db.RoomStorage
import com.example.simpleweather.data.db.dto.HistoryItemDto
import com.example.simpleweather.domain.models.CityInfo
import com.example.simpleweather.domain.models.WeatherInfo
import com.example.simpleweather.domain.repositories.StorageRepository

class StorageRepositoryImpl(private val roomStorage: RoomStorage) : StorageRepository {


    override suspend fun addNewHistoryItem(cityInfo: CityInfo, weatherInfo: WeatherInfo) {
        roomStorage.addNewHistoryItem(mapToData(cityInfo, weatherInfo))
    }

    override suspend fun deleteAllHistoryItems() {
        roomStorage.deleteAllHistoryItems()
    }

    override suspend fun getAllHistoryItems(): List<CityInfo> {
        val result = roomStorage.getAllHistoryItems()
        return result.map {
            mapToDomain(it)
        }
    }

    private fun mapToData(cityInfo: CityInfo, weatherInfo: WeatherInfo): HistoryItemDto {
        return HistoryItemDto(
            id = 0,
            country = cityInfo.country,
            lat = cityInfo.lat,
            lon = cityInfo.lon,
            name = cityInfo.name,
            state = cityInfo.state,
            flagImageSrc = cityInfo.flagImageSrc,
            dt = weatherInfo.dt
        )
    }

    private fun mapToDomain(historyItemDto: HistoryItemDto): CityInfo {
        with(historyItemDto) {
            return CityInfo(
                country = country,
                lat = lat,
                lon = lon,
                name = name,
                state = state,
                flagImageSrc = flagImageSrc,
                dt = dt
            )
        }
    }
}

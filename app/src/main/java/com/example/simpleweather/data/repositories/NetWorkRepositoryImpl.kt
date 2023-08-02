package com.example.simpleweather.data.repositories

import com.example.simpleweather.data.location.LocationClient
import com.example.simpleweather.data.location.dto.CityInfoDto
import com.example.simpleweather.domain.models.CityInfo
import com.example.simpleweather.domain.repositories.NetworkRepository

class NetWorkRepositoryImpl(private val locationClient: LocationClient) : NetworkRepository {

    override suspend fun getCityLocation(name: String): List<CityInfo> {

        val result = locationClient.getCityLocation(name = name)
        return result.map {
            mapToDomain(it)
        }
    }

    override suspend fun getWeatherInfo() {
        TODO("Not yet implemented")
    }

    private fun mapToDomain(cityInfoDto: CityInfoDto): CityInfo {

        with(cityInfoDto) {
            return CityInfo(
                country = country,
                lat = lat,
                lon = lon,
                name = name,
                state = state
            )
        }
    }
}
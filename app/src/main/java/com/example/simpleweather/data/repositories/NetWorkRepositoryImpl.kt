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
                state = state,
                flagImageSrc = getFlagSrc(countryCode = country)
            )
        }
    }

    fun getFlagSrc(countryCode: String): String =
       "https://raw.githubusercontent.com/alexxk2/SimpleWeather/ae6217f45da789156459a81b4c00583f04d8972c/app/src/main/res/drawable/${countryCode.lowercase()}.png"

}
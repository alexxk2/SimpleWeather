package com.example.simpleweather.data.location

import com.example.simpleweather.data.location.dto.CityInfoDto

interface LocationClient {

    suspend fun getCityLocation(name: String): List<CityInfoDto>
}
package com.example.simpleweather.domain.use_cases

import com.example.simpleweather.domain.models.CityInfo
import com.example.simpleweather.domain.repositories.NetworkRepository

class GetCityLocationUseCase(private val networkRepository: NetworkRepository) {

    suspend fun execute(name: String): List<CityInfo> = networkRepository.getCityLocation(name = name)
}
package com.example.simpleweather.domain.use_cases.network

import com.example.simpleweather.domain.models.WeatherInfo
import com.example.simpleweather.domain.repositories.NetworkRepository

class GetWeatherInfoUseCase(private val networkRepository: NetworkRepository) {

    suspend fun execute(lat: Double, lon: Double): WeatherInfo =
        networkRepository.getWeatherInfo(lat = lat, lon = lon)
}
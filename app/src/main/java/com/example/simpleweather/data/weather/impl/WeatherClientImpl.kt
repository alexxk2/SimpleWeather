package com.example.simpleweather.data.weather.impl

import com.example.simpleweather.data.location.LocationApiService
import com.example.simpleweather.data.weather.WeatherApiService
import com.example.simpleweather.data.weather.WeatherClient
import com.example.simpleweather.data.weather.dto.WeatherInfoDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherClientImpl : WeatherClient {

    private val baseUrl = "http://api.openweathermap.org"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }


    override suspend fun getWeatherInfo(lat: Double, lon: Double): WeatherInfoDto {
        return retrofitService.getWeatherInfo(
            lat = lat,
            lon = lon
        ).body()!!
    }
}
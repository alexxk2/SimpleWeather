package com.example.simpleweather.data.weather

import com.example.simpleweather.data.weather.dto.WeatherDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {



    @GET("/data/2.5/weather")
    suspend fun getWeatherInfo(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String = API_KEY
    ): Response<WeatherDto>

    companion object {
        const val API_KEY = "1c172339abd1869d6b8d7a09c54a4be7"
    }
}
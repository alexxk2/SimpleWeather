package com.example.simpleweather.data.weather

import com.example.simpleweather.data.weather.dto.WeatherInfoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {



    @GET("/data/3.0/onecall")
    suspend fun getWeatherInfo(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String = API_KEY
    ): Response<WeatherInfoDto>

    companion object {
        const val API_KEY = "1c172339abd1869d6b8d7a09c54a4be7"
    }
}
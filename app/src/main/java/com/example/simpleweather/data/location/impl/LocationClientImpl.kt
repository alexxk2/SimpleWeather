package com.example.simpleweather.data.location.impl

import com.example.simpleweather.data.location.LocationApiService
import com.example.simpleweather.data.location.LocationClient
import com.example.simpleweather.data.location.dto.CityInfoDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LocationClientImpl: LocationClient {

    private val baseUrl = "http://api.openweathermap.org"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: LocationApiService by lazy {
        retrofit.create(LocationApiService::class.java)
    }


    override suspend fun getCityLocation(name: String): List<CityInfoDto> = retrofitService.getCityLocation(name = name).body()?: emptyList()

}
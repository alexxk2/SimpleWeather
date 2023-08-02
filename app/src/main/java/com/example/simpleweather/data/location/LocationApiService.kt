package com.example.simpleweather.data.location

import com.example.simpleweather.data.location.dto.CityInfoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApiService {


    @GET("/geo/1.0/direct")
    suspend fun getCityLocation(
        @Query("q") name: String,
        @Query("limit") limit: Int = 5,
        @Query("appid") apiKey: String = API_KEY
    ): Response<List<CityInfoDto>>


    companion object {
        const val API_KEY = "1c172339abd1869d6b8d7a09c54a4be7"
    }
}
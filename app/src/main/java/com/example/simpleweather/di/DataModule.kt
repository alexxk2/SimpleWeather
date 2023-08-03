package com.example.simpleweather.di

import com.example.simpleweather.data.location.LocationClient
import com.example.simpleweather.data.location.impl.LocationClientImpl
import com.example.simpleweather.data.repositories.NetWorkRepositoryImpl
import com.example.simpleweather.data.weather.WeatherClient
import com.example.simpleweather.data.weather.impl.WeatherClientImpl
import com.example.simpleweather.domain.repositories.NetworkRepository
import org.koin.dsl.module

val dataModule = module {

    single<LocationClient> { LocationClientImpl() }

    single<WeatherClient> { WeatherClientImpl() }

    single<NetworkRepository> {
        NetWorkRepositoryImpl(
            locationClient = get(),
            weatherClient = get()
        )
    }
}
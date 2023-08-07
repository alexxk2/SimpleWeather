package com.example.simpleweather.di

import com.example.simpleweather.data.db.HistoryDatabase
import com.example.simpleweather.data.db.RoomStorage
import com.example.simpleweather.data.db.impl.RoomStorageImpl
import com.example.simpleweather.data.location.LocationClient
import com.example.simpleweather.data.location.impl.LocationClientImpl
import com.example.simpleweather.data.repositories.NetWorkRepositoryImpl
import com.example.simpleweather.data.repositories.StorageRepositoryImpl
import com.example.simpleweather.data.weather.WeatherClient
import com.example.simpleweather.data.weather.impl.WeatherClientImpl
import com.example.simpleweather.domain.repositories.NetworkRepository
import com.example.simpleweather.domain.repositories.StorageRepository
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

    single<RoomStorage> { RoomStorageImpl(context = get(), historyDatabase = get()) }

    single<StorageRepository> {StorageRepositoryImpl(roomStorage = get())  }

    single<HistoryDatabase> { HistoryDatabase.getDataBase(get())  }
}
package com.example.simpleweather.di

import com.example.simpleweather.domain.use_cases.network.GetCityLocationUseCase
import com.example.simpleweather.domain.use_cases.network.GetWeatherInfoUseCase
import com.example.simpleweather.domain.use_cases.storage.AddNewHistoryItemUseCase
import com.example.simpleweather.domain.use_cases.storage.DeleteAllHistoryItemsUseCase
import com.example.simpleweather.domain.use_cases.storage.GetAllHistoryItemsUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetCityLocationUseCase> { GetCityLocationUseCase(networkRepository = get()) }

    factory<GetWeatherInfoUseCase> { GetWeatherInfoUseCase(networkRepository = get()) }

    factory<AddNewHistoryItemUseCase> { AddNewHistoryItemUseCase(storageRepository = get())  }

    factory<DeleteAllHistoryItemsUseCase> {DeleteAllHistoryItemsUseCase(storageRepository = get())  }

    factory<GetAllHistoryItemsUseCase> {GetAllHistoryItemsUseCase(storageRepository = get())  }
}
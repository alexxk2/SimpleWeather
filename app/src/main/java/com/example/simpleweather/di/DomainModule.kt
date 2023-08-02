package com.example.simpleweather.di

import com.example.simpleweather.domain.use_cases.GetCityLocationUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetCityLocationUseCase> { GetCityLocationUseCase(networkRepository = get())}
}
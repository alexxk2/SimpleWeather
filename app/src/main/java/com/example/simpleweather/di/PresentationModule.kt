package com.example.simpleweather.di

import com.example.simpleweather.presentation.search.view_model.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel<SearchViewModel> {SearchViewModel(getCityLocationUseCase = get())  }
}
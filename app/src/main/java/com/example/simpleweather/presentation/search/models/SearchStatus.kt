package com.example.simpleweather.presentation.search.models

sealed interface SearchStatus {
    object Loading : SearchStatus
    object Done : SearchStatus
    object Error : SearchStatus
}
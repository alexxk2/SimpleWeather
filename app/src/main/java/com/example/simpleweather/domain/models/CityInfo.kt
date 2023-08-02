package com.example.simpleweather.domain.models

data class CityInfo(
    val country: String,
    val lat: Double,
    val lon: Double,
    val name: String,
    val state: String,
    val flagImageSrc: String
)

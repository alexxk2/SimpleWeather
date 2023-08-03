package com.example.simpleweather.data.repositories

import com.example.simpleweather.data.location.LocationClient
import com.example.simpleweather.data.location.dto.CityInfoDto
import com.example.simpleweather.data.weather.WeatherClient
import com.example.simpleweather.data.weather.dto.WeatherDto
import com.example.simpleweather.domain.models.CityInfo
import com.example.simpleweather.domain.models.WeatherInfo
import com.example.simpleweather.domain.repositories.NetworkRepository

class NetWorkRepositoryImpl(
    private val locationClient: LocationClient,
    private val weatherClient: WeatherClient
) : NetworkRepository {

    override suspend fun getCityLocation(name: String): List<CityInfo> {

        val result = locationClient.getCityLocation(name = name)
        return result.map {
            mapCityInfoToDomain(it)
        }
    }

    override suspend fun getWeatherInfo(lat: Double, lon: Double): WeatherInfo {
        val result = weatherClient.getWeatherInfo(lat = lat, lon = lon)
        return mapWeatherInfoToDomain(result)
    }

    private fun mapCityInfoToDomain(cityInfoDto: CityInfoDto): CityInfo {

        with(cityInfoDto) {
            return CityInfo(
                country = country,
                lat = lat,
                lon = lon,
                name = name,
                state = state,
                flagImageSrc = getFlagSrc(countryCode = country),
                dt = null
            )
        }
    }

    private fun mapWeatherInfoToDomain(weatherDto: WeatherDto): WeatherInfo {
        with(weatherDto) {
            return WeatherInfo(
                feels_like = main.feels_like,
                humidity = main.humidity,
                pressure = main.pressure,
                temp = main.temp,
                wind_speed = wind.speed,
                weatherImage = getWeatherIconSrc(weather.first().icon),
                dt = dt
            )
        }

    }

    private fun getFlagSrc(countryCode: String): String =
        "https://raw.githubusercontent.com/alexxk2/SimpleWeather/ae6217f45da789156459a81b4c00583f04d8972c/app/src/main/res/drawable/${countryCode.lowercase()}.png"


    private fun getWeatherIconSrc(iconCode: String): String =
        "https://openweathermap.org/img/wn/$iconCode@2x.png"
}
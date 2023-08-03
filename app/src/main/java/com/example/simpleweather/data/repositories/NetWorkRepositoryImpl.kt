package com.example.simpleweather.data.repositories

import com.example.simpleweather.data.location.LocationClient
import com.example.simpleweather.data.location.dto.CityInfoDto
import com.example.simpleweather.data.weather.WeatherClient
import com.example.simpleweather.data.weather.dto.WeatherInfoDto
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
                flagImageSrc = getFlagSrc(countryCode = country)
            )
        }
    }

    private fun mapWeatherInfoToDomain(weatherInfoDto: WeatherInfoDto): WeatherInfo {
        with(weatherInfoDto) {
            return WeatherInfo(
                feels_like = current.feels_like,
                humidity = current.humidity,
                pressure = current.pressure,
                temp = current.temp,
                wind_speed = current.wind_speed,
                weatherImage = getWeatherIconSrc(current.weather.first().icon),
            )
        }

    }

    private fun getFlagSrc(countryCode: String): String =
        "https://raw.githubusercontent.com/alexxk2/SimpleWeather/ae6217f45da789156459a81b4c00583f04d8972c/app/src/main/res/drawable/${countryCode.lowercase()}.png"


    private fun getWeatherIconSrc(iconCode: String): String =
        "https://openweathermap.org/img/wn/$iconCode@2x.png"
}
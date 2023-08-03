package com.example.simpleweather.presentation.info.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleweather.domain.models.WeatherInfo
import com.example.simpleweather.domain.use_cases.GetWeatherInfoUseCase
import com.example.simpleweather.presentation.search.models.SearchStatus
import kotlinx.coroutines.launch

class InfoViewModel(
    private val getWeatherInfoUseCase: GetWeatherInfoUseCase
): ViewModel() {

    private val _weatherInfo = MutableLiveData<WeatherInfo>()
    val weatherInfo: LiveData<WeatherInfo> = _weatherInfo

    private val _searchState = MutableLiveData<SearchStatus>()
    val searchState: LiveData<SearchStatus> = _searchState


    fun getWeatherInfo(lat: Double, lon: Double){
        _searchState.value = SearchStatus.Loading
        viewModelScope.launch {
            try {
                _weatherInfo.value = getWeatherInfoUseCase.execute(lat = lat, lon = lon)
                _searchState.value = SearchStatus.Done
            }
            catch (e:Exception){
                _searchState.value = SearchStatus.Error
            }
        }

    }
}
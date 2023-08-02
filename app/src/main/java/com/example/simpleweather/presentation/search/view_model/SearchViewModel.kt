package com.example.simpleweather.presentation.search.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleweather.domain.models.CityInfo
import com.example.simpleweather.domain.use_cases.GetCityLocationUseCase
import kotlinx.coroutines.launch
import java.lang.Exception

class SearchViewModel(
    private val getCityLocationUseCase: GetCityLocationUseCase
) : ViewModel() {

    private val _cityInfo = MutableLiveData<List<CityInfo>>()
    val cityInfo: LiveData<List<CityInfo>> = _cityInfo


    fun getCityLocation(name: String) {
        viewModelScope.launch {

            try {
                _cityInfo.value = getCityLocationUseCase.execute(name = name)
            } catch (e: Exception) {
                _cityInfo.value = listOf(
                    CityInfo(
                        country = "empty",
                        lat = 0.0,
                        lon = 0.0,
                        name = "empty",
                        state = "empty"
                    )
                )
            }
        }
    }

}
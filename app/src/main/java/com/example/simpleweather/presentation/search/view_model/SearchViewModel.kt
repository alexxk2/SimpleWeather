package com.example.simpleweather.presentation.search.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleweather.domain.models.CityInfo
import com.example.simpleweather.domain.use_cases.network.GetCityLocationUseCase
import com.example.simpleweather.domain.use_cases.storage.DeleteAllHistoryItemsUseCase
import com.example.simpleweather.domain.use_cases.storage.GetAllHistoryItemsUseCase
import com.example.simpleweather.presentation.search.models.SearchStatus
import kotlinx.coroutines.launch
import java.lang.Exception

class SearchViewModel(
    private val getCityLocationUseCase: GetCityLocationUseCase,
    private val getAllHistoryItemsUseCase: GetAllHistoryItemsUseCase,
    private val deleteAllHistoryItemsUseCase: DeleteAllHistoryItemsUseCase
) : ViewModel() {

    private val _cityInfo = MutableLiveData<List<CityInfo>>()
    val cityInfo: LiveData<List<CityInfo>> = _cityInfo

    private val _searchState = MutableLiveData<SearchStatus>()
    val searchState: LiveData<SearchStatus> = _searchState

    fun getCityLocation(name: String) {
        _searchState.value = SearchStatus.Loading
        viewModelScope.launch {

            try {
                _cityInfo.value = getCityLocationUseCase.execute(name = name)

                if (cityInfo.value?.isEmpty() == true) {
                    _searchState.value = SearchStatus.Error
                } else _searchState.value = SearchStatus.Done

            } catch (e: Exception) {
                _searchState.value = SearchStatus.Error
            }
        }
    }
}
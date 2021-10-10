package com.mbobiosio.weatherappandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbobiosio.weatherappandroid.model.Result
import com.mbobiosio.weatherappandroid.model.WeatherResponse
import com.mbobiosio.weatherappandroid.repository.WeatherRepository
import kotlinx.coroutines.launch

/*
* Created by Mbuodile Obiosio on Oct 10, 2021.
* Twitter: @cazewonder
* Nigeria
*/
class WeatherViewModel : ViewModel() {

    private val weatherRepository = WeatherRepository()

    //API Error response livedata
    private val _errorMessage = MutableLiveData<Result.Error>()
    val errorMessage: LiveData<Result.Error> get() = _errorMessage
    //Network error response livedata
    private val _networkError = MutableLiveData<Result.NetworkError>()
    val networkError: LiveData<Result.NetworkError> get() = _networkError

    /*
    * St
    * */
    private val _weatherInfo = MutableLiveData<WeatherResponse>()
    val weatherInfo: LiveData<WeatherResponse> get() = _weatherInfo

    fun getWeatherInfo(query: String?) {
        viewModelScope.launch {
            when (val result = weatherRepo(query = query)) {
                is Result.Success -> _weatherInfo.postValue(result.value)
                is Result.Error -> _errorMessage.postValue(result)
                is Result.NetworkError -> _networkError.postValue(result)
            }
        }
    }

    private suspend fun weatherRepo(query: String?) = weatherRepository.getWeatherData(query)
}
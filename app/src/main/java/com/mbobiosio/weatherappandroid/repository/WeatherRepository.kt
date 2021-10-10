package com.mbobiosio.weatherappandroid.repository

import com.mbobiosio.weatherappandroid.api.RetrofitClient
import com.mbobiosio.weatherappandroid.model.Result
import com.mbobiosio.weatherappandroid.model.WeatherResponse

/*
* Created by Mbuodile Obiosio on Oct 10, 2021.
* Twitter: @cazewonder
* Nigeria
*/
class WeatherRepository : BaseRepository() {

    private val weatherService = RetrofitClient.weatherService

    suspend fun getWeatherData(q: String?
    ): Result<WeatherResponse> {
        return coroutineHandler(dispatcher) {
            weatherService.fetchWeatherInfo(q)
        }
    }
}
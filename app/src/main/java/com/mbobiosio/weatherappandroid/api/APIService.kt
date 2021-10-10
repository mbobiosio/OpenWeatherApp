package com.mbobiosio.weatherappandroid.api

import com.mbobiosio.weatherappandroid.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


/*
* Created by Mbuodile Obiosio on Oct 10, 2021.
* Twitter: @cazewonder
* Nigeria
*/
interface APIService {

    @GET("weather")
    suspend fun fetchWeatherInfo(
        @Query("q") city: String?
    ): WeatherResponse
}
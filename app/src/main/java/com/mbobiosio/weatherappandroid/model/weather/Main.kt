package com.mbobiosio.weatherappandroid.model.weather

import com.squareup.moshi.Json

/*
* Created by Mbuodile Obiosio on Oct 10, 2021.
* Twitter: @cazewonder
* Nigeria
*/
data class Main(
    @Json(name = "temp")
    val temperature: Double?,

    @Json(name = "feels_like")
    val feelsLike: Double?,

    @Json(name = "temp_min")
    val tempMin: Double?,

    @Json(name = "temp_max")
    val tempMax: Double?,

    @Json(name = "pressure")
    val pressure: Long?,

    @Json(name = "humidity")
    val humidity: Long?
)

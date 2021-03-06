package com.mbobiosio.weatherappandroid.model.weather

import com.squareup.moshi.Json

/*
* Created by Mbuodile Obiosio on Oct 10, 2021.
* Twitter: @cazewonder
* Nigeria
*/
data class Sys(
    @Json(name = "id")
    val id: Long?,

    @Json(name = "type")
    val type: Long?,

    @Json(name = "country")
    val country: String?,

    @Json(name = "sunrise")
    val sunrise: Int?,

    @Json(name = "sunset")
    val sunset: Int?
)

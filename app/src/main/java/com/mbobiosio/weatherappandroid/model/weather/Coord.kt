package com.mbobiosio.weatherappandroid.model.weather

import com.squareup.moshi.Json

/*
* Created by Mbuodile Obiosio on Oct 10, 2021.
* Twitter: @cazewonder
* Nigeria
*/
data class Coord(
    @Json(name = "lon")
    val lon: Double?,

    @Json(name = "lat")
    val lat: Double?
)

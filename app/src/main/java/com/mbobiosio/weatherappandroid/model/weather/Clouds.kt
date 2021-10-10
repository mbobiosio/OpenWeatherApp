package com.mbobiosio.weatherappandroid.model.weather

import com.squareup.moshi.Json

/*
* Created by Mbuodile Obiosio on Oct 10, 2021.
* Twitter: @cazewonder
* Nigeria
*/
data class Clouds(
    @Json(name = "all")
    val all: Long?
)

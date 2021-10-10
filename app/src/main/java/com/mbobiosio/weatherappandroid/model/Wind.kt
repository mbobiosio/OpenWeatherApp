package com.mbobiosio.weatherappandroid.model

import com.squareup.moshi.Json

/*
* Created by Mbuodile Obiosio on Oct 10, 2021.
* Twitter: @cazewonder
* Nigeria
*/
data class Wind(
    @Json(name = "speed")
    val speed: Double?,

    @Json(name = "deg")
    val degree: Long?
)

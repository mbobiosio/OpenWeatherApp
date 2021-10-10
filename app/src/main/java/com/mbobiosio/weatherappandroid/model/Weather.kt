package com.mbobiosio.weatherappandroid.model

import com.squareup.moshi.Json

/*
* Created by Mbuodile Obiosio on Oct 10, 2021.
* Twitter: @cazewonder
* Nigeria
*/
data class Weather(
    @Json(name = "id")
    val id: Long?,

    @Json(name = "main")
    val main: String?,

    @Json(name = "description")
    val description: String?,

    @Json(name = "icon")
    val icon: String?
)

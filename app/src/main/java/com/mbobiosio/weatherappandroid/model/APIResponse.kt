package com.mbobiosio.weatherappandroid.model

import com.squareup.moshi.Json

/*
* Created by Mbuodile Obiosio on Oct 10, 2021.
* Twitter: @cazewonder
* Nigeria
*/
data class APIResponse(
    @Json(name = "cod")
    val code: Int?,

    @Json(name = "message")
    val message: String?
)

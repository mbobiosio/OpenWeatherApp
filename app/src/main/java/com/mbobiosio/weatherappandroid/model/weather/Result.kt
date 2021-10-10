package com.mbobiosio.weatherappandroid.model.weather

import com.mbobiosio.weatherappandroid.model.APIResponse

/*
* Created by Mbuodile Obiosio on Oct 10, 2021.
* Twitter: @cazewonder
* Nigeria
*/
sealed class Result<out T> {
    data class Success<out T>(val value: T): Result<T>()
    data class Error(val error: Int? = null, val message: APIResponse? = null): Result<Nothing>()
    data class NetworkError(val error: String? = null): Result<Nothing>()
}
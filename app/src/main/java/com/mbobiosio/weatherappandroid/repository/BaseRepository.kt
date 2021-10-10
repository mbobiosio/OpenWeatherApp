package com.mbobiosio.weatherappandroid.repository

import com.mbobiosio.weatherappandroid.model.APIResponse
import com.mbobiosio.weatherappandroid.model.Result
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

/*
* Created by Mbuodile Obiosio on Oct 10, 2021.
* Twitter: @cazewonder
* Nigeria
*/
open class BaseRepository {

    suspend fun <T> coroutineHandler(
        dispatcher: CoroutineDispatcher,
        apiRequest: suspend () -> T
    ): Result<T> {
        return withContext(dispatcher) {
            try {
                Result.Success(apiRequest.invoke())
            } catch (t: Throwable) {
                when (t) {
                    is IOException -> {
                        Result.NetworkError(t.message)
                    }
                    is HttpException -> {
                        val code = t.code()
                        val message = throwableResponse(t)
                        Timber.d("API Error Message $code : ${message?.code} : ${message?.message}")
                        Result.Error(code, message)
                    }
                    else -> {
                        Result.Error(null, null)
                    }
                }
            }
        }
    }

    open val dispatcher = Dispatchers.IO

    private fun throwableResponse(e: HttpException): APIResponse? {
        return try {
            e.response()?.errorBody()?.source()?.let {
                val moshiAdapter = Moshi.Builder()
                    .addLast(KotlinJsonAdapterFactory())
                    .build()
                    .adapter(APIResponse::class.java)
                moshiAdapter.fromJson(it)
            }
        } catch (t: Throwable) {
            Timber.d("Error $t")
            null
        }
    }
}
package com.mbobiosio.weatherappandroid.repository

import com.mbobiosio.weatherappandroid.model.APIResponse
import com.mbobiosio.weatherappandroid.model.weather.Result
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
/*
* In Kotlin, a class by default is 'final' which means it can't be inherited.
* The open annotation on a class is the opposite of Java's final : it allows others to inherit from this class.
* */
open class BaseRepository {

    /*
    * Suspend functions can be started, paused and resumed later.
    * This function executes CoroutineDispatcher operation and waits for the response without blocking.
    * Cooperation & Routines - Coroutines introduce a new style of concurrency that can be used to simplify async code
    * */
    suspend fun <T> coroutineHandler(
        /*
        * In Kotlin, all coroutines must run in a dispatcher, even when they're running on the main thread.
        * Kotlin coroutines use dispatchers to determine which threads are used for coroutine execution.
        * */
        dispatcher: CoroutineDispatcher,
        apiRequest: suspend () -> T
    ): Result<T> {
        /*
        * withContext is a suspend call and does not block the main thread for a coroutine context,
        * we can do other tasks while the IO thread is busy in executing dispatcher and apiRequest
        * */
        return withContext(dispatcher) {
            try {
                //We invoke the api successful response
                Result.Success(apiRequest.invoke())
                /*
                * Throwable is the base class for all errors and exceptions.
                * We catch different error response here.
                *
                * "When" construct here kotlin replaces the Java Switch statement.
                * We will use this to evaluate the Throwable alternatives at a time.
                * */
            } catch (t: Throwable) {
                when (t) {
                    /*
                    * An IOException signals an input or output exception.
                    * When there's an I/O Error we'll collect the message to the Result class
                    * */
                    is IOException -> {
                        //Pass network error message
                        Result.NetworkError(t.message)
                    }
                    /*
                    * Collect Retrofit Exception code and message.
                    * */
                    is HttpException -> {
                        val code = t.code()
                        val message = throwableResponse(t)
                        Timber.d("API Error Message $code : ${message?.code} : ${message?.message}")
                        //Pass Retrofit (server error response)
                        Result.Error(code, message)
                    }
                    else -> {
                        /*
                        * When construct expects an else branch
                        * */
                        Result.Error(null, null)
                    }
                }
            }
        }
    }

    /*
    * The CoroutineDispatcher that is designed for offloading blocking IO tasks to a shared pool of threads.
    * Kotlin coroutines use dispatchers to determine which threads are used for coroutine execution.
    * */
    open val dispatcher = Dispatchers.IO

    /*
    * Create a function that extends APIResponse data class
    * */
    private fun throwableResponse(e: HttpException): APIResponse? {
        return try {
            e.response()?.errorBody()?.source()?.let {
                /*
                * Handling reflection for APIResponse data class
                * The reflection adapter uses Kotlin’s reflection library to convert your Kotlin classes to and from JSON.
                * */
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
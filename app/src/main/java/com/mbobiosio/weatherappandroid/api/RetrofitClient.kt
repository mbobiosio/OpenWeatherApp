package com.mbobiosio.weatherappandroid.api

import com.mbobiosio.weatherappandroid.util.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/*
* Created by Mbuodile Obiosio on Oct 10, 2021.
* Twitter: @cazewonder
* Nigeria
*/
object RetrofitClient {
    val weatherService by lazy { apiService<APIService>()}
}

private fun httpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    return OkHttpClient.Builder().apply {
        addInterceptor(interceptor)
        addInterceptor(QueryInterceptor())
        connectTimeout(60, TimeUnit.SECONDS)
        readTimeout(60, TimeUnit.SECONDS)
        retryOnConnectionFailure(true)
    }.build()
}

private fun getMoshi(): Moshi {
    return Moshi.Builder().apply {
        this.add(KotlinJsonAdapterFactory())
    }.build()
}

private fun getRetrofit(): Retrofit {
    return Retrofit.Builder().apply {
        baseUrl(BASE_URL)
        client(httpClient())
        addConverterFactory(MoshiConverterFactory.create(getMoshi()))
    }.build()
}

private inline fun <reified T> apiService(): T = getRetrofit().create(T::class.java)
package com.mbobiosio.weatherappandroid.api

import okhttp3.Interceptor
import okhttp3.Response

/*
* Created by Mbuodile Obiosio on Oct 10, 2021.
* Twitter: @cazewonder
* Nigeria
*/
class QueryInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val url = chain.request().url.newBuilder()
            .addQueryParameter("appid", "6e76055f1d172fe00a7aa9edd1f0ebf5")
            .build()

        val request = chain.request().newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}
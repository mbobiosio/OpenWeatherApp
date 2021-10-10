package com.mbobiosio.weatherappandroid

import android.app.Application
import timber.log.Timber

/*
* Created by Mbuodile Obiosio on Oct 10, 2021.
* Twitter: @cazewonder
* Nigeria
*/
class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()

        //initialize Timber Logger
        initLogger()
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
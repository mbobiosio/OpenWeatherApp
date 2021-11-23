package com.mbobiosio.weatherappandroid.util

import android.content.Context
import timber.log.Timber
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

/*
* Created by Mbuodile Obiosio on Oct 10, 2021.
* Twitter: @cazewonder
* Nigeria
*/
const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

const val ICON_URL = "https://openweathermap.org/img/w/"

fun Context.loadJsonFromAsset(jsonFileName: String): String? {
    val json: String
    try {
        json = this.assets.open(jsonFileName).bufferedReader().use { it.readText() }
    } catch (e: IOException) {
        Timber.d(e)
        return null
    }
    return json
}

fun Double.kelvinToCelsius() : Int {
    return  (this - 273.15).toInt()
}

fun Int.unixTimeToDateTime() : String {

    try {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = this*1000.toLong()

        val outputDateFormat = SimpleDateFormat("EEE, dd MMM, yyyy - hh:mm a", Locale.ENGLISH)
        outputDateFormat.timeZone = TimeZone.getDefault() // user's default time zone
        return outputDateFormat.format(calendar.time)

    } catch (e: Exception) {
        e.printStackTrace()
    }

    return this.toString()
}

fun Int.unixTimeToString() : String {

    try {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = this*1000.toLong()

        val outputDateFormat = SimpleDateFormat("hh:mm a", Locale.ENGLISH)
        outputDateFormat.timeZone = TimeZone.getDefault()
        return outputDateFormat.format(calendar.time)

    } catch (e: Exception) {
        e.printStackTrace()
    }

    return this.toString()
}
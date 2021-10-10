package com.mbobiosio.weatherappandroid.util

import androidx.databinding.BindingAdapter
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import coil.request.ImageRequest
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import com.mbobiosio.weatherappandroid.model.weather.WeatherResponse
import timber.log.Timber

/*
* Created by Mbuodile Obiosio on Oct 10, 2021.
* Twitter: @cazewonder
* Nigeria
*/
@BindingAdapter("icon")
fun ShapeableImageView.icon(icon: String) {
    val imageLoader = ImageLoader.Builder(context)
        .componentRegistry {
            add(SvgDecoder(context))
        }
        .build()
    val request = ImageRequest.Builder(this.context)
        .data(icon)
        .target(this)
        .build()
    imageLoader.enqueue(request)
}

/**
 * The temperature T in degrees Celsius (°C) is equal to the temperature T in Kelvin (K) minus 273.15:
 * T(°C) = T(K) - 273.15
 *
 * Example
 * Convert 300 Kelvin to degrees Celsius:
 * T(°C) = 300K - 273.15 = 26.85 °C
 */
@BindingAdapter("temperature")
fun MaterialTextView.kelvinToCelsius(temp: Double?) {
    temp?.let {
        val tmp = 0x00B0.toChar()
        this.text = temp.kelvinToCelsius().toString().plus(tmp)
    }
}

@BindingAdapter(value = ["city", "country"], requireAll = false)
fun MaterialTextView.cityCountry(city: String?, country: String?) {
    country?.let {
        this.text = city.plus(", $country")
    }
}

@BindingAdapter("weather")
fun MaterialTextView.weather(weather: WeatherResponse?) {
    weather?.let {
        this.text = weather.weather!![0].description
    }
}

@BindingAdapter("weatherIcon")
fun ShapeableImageView.weatherIcon(icon: WeatherResponse?) {
    icon?.let {
        val url = ICON_URL.plus(it.weather!![0].icon).plus(".png")
        Timber.d(url)
        this.load(url)
    }
}

@BindingAdapter("date")
fun MaterialTextView.date(date: Int?) {
    date?.let {
        val convertDate = it.unixTimeToDateTime()
        this.text = convertDate
    }
}

@BindingAdapter("pressure")
fun MaterialTextView.pressure(pressure: Long?) {
    pressure?.let {
        this.text = it.toString().plus(" mBar")
    }
}

@BindingAdapter("humidity")
fun MaterialTextView.humidity(humidity: Long?) {
    humidity?.let {
        this.text = it.toString().plus("%")
    }
}

@BindingAdapter("windSpeed")
fun MaterialTextView.windSpeed(windSpeed: Double?) {
    windSpeed?.let {
        this.text = it.toString().plus(" km/h")
    }
}

@BindingAdapter("sys")
fun MaterialTextView.sys(sys: Int?) {
    sys?.let {
        this.text = it.unixTimeToString()
    }
}
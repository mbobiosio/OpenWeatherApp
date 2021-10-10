package com.mbobiosio.weatherappandroid.util

import androidx.databinding.BindingAdapter
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.google.android.material.imageview.ShapeableImageView

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
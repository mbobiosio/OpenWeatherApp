package com.mbobiosio.weatherappandroid.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mbobiosio.weatherappandroid.databinding.ActivityMainBinding
import com.mbobiosio.weatherappandroid.viewmodel.WeatherViewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<WeatherViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this

        viewModel.getWeatherInfo("l")

        viewModel.weatherInfo.observe(this) {
            it?.let {
                Timber.d("${it.name}")
            }
        }

        viewModel.errorMessage.observe(this) {
            it?.let {
                Timber.d("Error - ${it.message?.message}")
            }
        }

        viewModel.networkError.observe(this) {
            it?.let {
                Timber.d("Network Error - ${it.error}")
            }
        }
    }


}
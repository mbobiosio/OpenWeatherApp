package com.mbobiosio.weatherappandroid.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.mbobiosio.weatherappandroid.databinding.ActivityCityDetailBinding
import com.mbobiosio.weatherappandroid.viewmodel.WeatherViewModel
import timber.log.Timber

class CityDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCityDetailBinding
    private val viewModel by viewModels<WeatherViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this

        val city = intent.getStringExtra("city")
        viewModel.getWeatherInfo(city)

        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: WeatherViewModel) {
        viewModel.weatherInfo.observe(this) {
            it?.let {
                Timber.d("${it.weather}")
            }
        }

        //
        viewModel.errorMessage.observe(this) {
            it?.let {
                Timber.d("${it.message?.message}")
            }
        }

        viewModel.networkError.observe(this) {
            it?.let {
                Timber.d("${it.error}")
            }
        }
    }
}
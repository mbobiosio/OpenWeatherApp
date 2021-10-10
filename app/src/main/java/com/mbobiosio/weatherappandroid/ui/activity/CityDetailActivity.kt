package com.mbobiosio.weatherappandroid.ui.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.mbobiosio.weatherappandroid.databinding.ActivityCityDetailBinding
import com.mbobiosio.weatherappandroid.viewmodel.WeatherViewModel
import timber.log.Timber

class CityDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCityDetailBinding
    private val viewModel by viewModels<WeatherViewModel>()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this

        val city = intent.getStringExtra("city")
        viewModel.getWeatherInfo(city)

        binding.apply {

            //go back on toolbar back icon press
            materialToolbar.setNavigationOnClickListener { onBackPressed() }

            //setup configurations for power spinner
            cities.apply {
                //set lifecycle owner to avoid memory leaks
                lifecycleOwner = this@CityDetailActivity
                //select item on spinner and make request to server
                setOnSpinnerItemSelectedListener<String> { _, _, _, newItem ->
                    //pass city name to view model
                    viewModel.getWeatherInfo(newItem)

                    //show progress bar
                    progressBar.show()
                }

                //close spinner when use touches outside of the spinner area
                setOnSpinnerOutsideTouchListener { _, motionEvent ->
                    if (motionEvent.actionButton == 0) {
                        cities.dismiss()
                    }
                }
            }
        }

        //initialise view model observers
        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: WeatherViewModel) {
        //observe successful weather response
        viewModel.weatherInfo.observe(this) {
            binding.progressBar.hide()
            it?.let {
                binding.cityDetail = it
                binding.weatherLayout.cityDetails = it
            }
        }

        //observe errors given by the api
        viewModel.errorMessage.observe(this) {
            it?.let {
                Timber.d("${it.message?.message}")
            }
        }

        //observe network related errors
        viewModel.networkError.observe(this) {
            it?.let {
                Timber.d("${it.error}")
            }
        }
    }
}
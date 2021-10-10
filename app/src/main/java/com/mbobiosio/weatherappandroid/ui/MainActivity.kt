package com.mbobiosio.weatherappandroid.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mbobiosio.weatherappandroid.databinding.ActivityMainBinding
import com.mbobiosio.weatherappandroid.listener.CityClickListener
import com.mbobiosio.weatherappandroid.model.City
import com.mbobiosio.weatherappandroid.ui.activity.CityDetailActivity
import com.mbobiosio.weatherappandroid.ui.adapter.CitiesAdapter
import com.mbobiosio.weatherappandroid.viewmodel.WeatherViewModel
import org.json.JSONArray
import org.json.JSONException
import timber.log.Timber
import java.io.IOException
import java.nio.charset.Charset
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<WeatherViewModel>()
    private val cityList = arrayListOf<City>()
    private lateinit var adapter: CitiesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this

        initAdapter()
        addCitiesFromJson()

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

        //Timber.d("City ${addCitiesFromJson()}")


    }

    private fun addCitiesFromJson() {
        try {
            val cities = loadCitiesFromAsset()
            val citiesArray = JSONArray(cities)

            for (i in 0 until citiesArray.length()) {
                val cityObj: JSONObject = citiesArray.getJSONObject(i)
                val id = cityObj.getLong("id")
                val name = cityObj.getString("name")
                val icon = cityObj.getString("icon")
                val country = cityObj.getString("country")
                val city = City(id = id, name = name, icon = icon, country = country)
                Timber.d("$city")
                cityList.add(city)
                adapter.setData(cityList)
                //viewItems.add(holidays)
            }
        } catch (e: JSONException) {
            Timber.d("JSONException $e")
        } catch (e: IOException) {
            Timber.d("IOException $e")
        }
    }


    private fun loadCitiesFromAsset(): String {
        val json: String?
        try {
            val inputStream = assets.open("cities.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)
        } catch (e: IOException) {
            Timber.d(e)
            return ""
        }

        return json
    }

    private fun initAdapter() {
        adapter = CitiesAdapter(object : CityClickListener {
            override fun onItemClicked(city: City) {
                Intent(this@MainActivity, CityDetailActivity::class.java).apply {
                    putExtra("city", city.name)
                }.also {
                    this@MainActivity.startActivity(it)
                }
            }
        })
        binding.cities.adapter = adapter
    }

}
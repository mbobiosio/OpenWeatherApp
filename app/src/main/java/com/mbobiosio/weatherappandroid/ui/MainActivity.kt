package com.mbobiosio.weatherappandroid.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mbobiosio.weatherappandroid.databinding.ActivityMainBinding
import com.mbobiosio.weatherappandroid.listener.CityClickListener
import com.mbobiosio.weatherappandroid.model.City
import com.mbobiosio.weatherappandroid.ui.activity.CityDetailActivity
import com.mbobiosio.weatherappandroid.ui.adapter.CitiesAdapter
import com.mbobiosio.weatherappandroid.util.loadJsonFromAsset

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CitiesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this

        //initialise adapter
        initAdapter()

        //add cities to array list after loading from json file
        addCitiesFromJson()

    }

    private fun addCitiesFromJson() {

        /*
        * Load data from json file.
        * This file must be formatted as an array list in this context
        * */
        val jsonData = loadJsonFromAsset("cities.json")

        //Gson TypeToken is used to deserialize json arrays
        val city: List<City> = Gson().fromJson(jsonData, object : TypeToken<List<City>>() {}.type)

        /*
        * Using ListAdapter allows a better handling of recycler views
        * ListAdapter gets data using a method called submitList() which requires a list
        * Beautiful aspect of this is not needing to use getItemCount in Adapter.
        * */
        adapter.submitList(city)
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
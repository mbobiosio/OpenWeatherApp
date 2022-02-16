package com.mbobiosio.weatherappandroid

import com.mbobiosio.weatherappandroid.repository.WeatherRepository
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/*
* Created by Mbuodile Obiosio
* github: https://github.com/mbobiosio
* linkedin: https://linkedin.com/in/mb-obiosio
* Nigeria
*/
class RepositoryTest {

    @get:Rule


    private lateinit var weatherRepository: WeatherRepository

    @Before
    fun setup() {
        //MockitoAnnotions.initMocks(this)
        weatherRepository = WeatherRepository()
    }

    @After
    fun tearDown() {

    }

    @Test
    fun getWeatherRequest() {

        runBlocking {
            //Make a reference to the Weather repository

        }
    }
}
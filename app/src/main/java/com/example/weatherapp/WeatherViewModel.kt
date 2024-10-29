package com.example.weatherapp

import androidx.lifecycle.ViewModel
import com.example.weatherapp.api.RetrofitInstance

class WeatherViewModel : ViewModel() {

    private val weatherApi = RetrofitInstance.weatherApi

    fun getData(city: String){

        weatherApi.getWeather()

    }
}

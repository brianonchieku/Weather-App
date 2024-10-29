package com.example.weatherapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.api.Constant
import com.example.weatherapp.api.NetworkResponse
import com.example.weatherapp.api.RetrofitInstance
import com.example.weatherapp.api.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val weatherApi = RetrofitInstance.weatherApi
    private val _weatherResults = MutableLiveData<NetworkResponse<WeatherModel>>()
    private val weatherResults: LiveData<NetworkResponse<WeatherModel>> = _weatherResults

    fun getData(city: String){
        _weatherResults.value = NetworkResponse.Loading

        viewModelScope.launch {
            try {
                val response = weatherApi.getWeather(Constant.apikey,city)
                if (response.isSuccessful){
                    response.body()?.let {
                        _weatherResults.value = NetworkResponse.Success(it)
                    }
                }else{
                    _weatherResults.value = NetworkResponse.Error("failed to load data")

                }
            }catch (e: Exception){
                _weatherResults.value = NetworkResponse.Error("failed to load data")
            }

        }



    }

}

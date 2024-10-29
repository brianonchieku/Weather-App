package com.example.weatherapp.api

sealed class NetworkResponse<out T> {
    //T is weathermodel
    data class Success<out T>(val data: T): NetworkResponse<T>()
    data class Error(val message: String): NetworkResponse<Nothing>()
    object Loading: NetworkResponse<Nothing>()
}
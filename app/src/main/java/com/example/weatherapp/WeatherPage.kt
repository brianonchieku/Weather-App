package com.example.weatherapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherapp.api.NetworkResponse
import com.example.weatherapp.api.WeatherModel

@Composable
fun WeatherPage(viewModel: WeatherViewModel){

    var city by remember {
        mutableStateOf("")
    }
    val weatherResults by viewModel.weatherResults.observeAsState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextField(value = city , onValueChange = {city = it},
                label = {
                    Text(text = "search for location")
                }, modifier = Modifier.weight(1f))
            IconButton(onClick = {
                viewModel.getData(city)
            }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "search")

            }
        }
        when(val result = weatherResults){
            is NetworkResponse.Error -> {
                Text(text = result.message)
            }
            NetworkResponse.Loading -> {
                CircularProgressIndicator()
            }
            is NetworkResponse.Success -> {
                WeatherDetails(data = result.data)
            }
            null -> {}
        }
    }
}

@Composable
fun WeatherDetails(data: WeatherModel){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            modifier =Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom
        ){
            Icon(imageVector = Icons.Default.LocationOn, contentDescription = "location icon",
                modifier = Modifier.size(40.dp))

        }

    }

}
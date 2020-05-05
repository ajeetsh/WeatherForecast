package com.example.weatherforecast.api

import com.example.weatherforecast.network.RetrofitProvider
import com.example.weatherforecast.network.services.WeatherService
import retrofit2.Retrofit

object ApiManager {
    var weatherService: WeatherService = create(WeatherService::class.java)

    fun <T> create(serviceClass: Class<T>): T {
        return RetrofitProvider.retrofit.create(serviceClass)
    }

}
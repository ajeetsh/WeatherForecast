package com.example.weatherforecast.network.services

import com.example.weatherforecast.data.WeatherResponse
import io.reactivex.Observable
import retrofit2.http.*

interface WeatherService {
    @GET("forecast")
    fun getWeather(@Query("id") cityCode:String,@Query("APPID") apiKey:String,@Query("units") units:String): Observable<WeatherResponse>
}
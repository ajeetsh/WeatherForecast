package com.example.weatherforecast.ui

import android.content.Context
import com.example.weatherforecast.data.WeatherResponse

interface WeatherContract{
    interface View{
        fun weatherApiResponse(weather: WeatherResponse?)
        fun noNetwork()
    }
    interface Presenter<View>{
        fun attachView(view:View)
        fun callWeatherApi()
    }
}

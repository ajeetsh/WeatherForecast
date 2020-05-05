package com.example.weatherforecast.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.weatherforecast.R
import com.example.weatherforecast.adapter.WeatherAdapter
import com.example.weatherforecast.data.WeatherData
import com.example.weatherforecast.data.WeatherInfo
import com.example.weatherforecast.data.WeatherResponse
import kotlinx.android.synthetic.main.activity_main.*
import com.google.gson.Gson
import java.io.IOException
import java.nio.charset.Charset
import android.support.design.widget.Snackbar
import com.example.weatherforecast.util.NetworkConnectionUtil


class WeatherActivity : AppCompatActivity(), WeatherContract.View {
    override fun noNetwork() {
        progress_bar.visibility = View.GONE
        Snackbar.make(root_layout, "Please check your network connection.", Snackbar.LENGTH_LONG).show()
    }

    override fun weatherApiResponse(weather: WeatherResponse?) {
        progress_bar.visibility = View.GONE
        if (weather == null) {
            Toast.makeText(this, "No data found", Toast.LENGTH_LONG).show()
        }
        weather?.let {
            title = getString(R.string.app_name) + getString(R.string.hyphen) + it.city.name
            showRecyclerView(it)
        }
    }

    private fun showRecyclerView(weather: WeatherResponse) {
        WeatherData.city = weather.city
        recycler_view.adapter = WeatherAdapter(weather.list, this)
        Snackbar.make(root_layout, "Click on card for details", Snackbar.LENGTH_LONG).show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchWeatherReport()
        //commit check - 17
        //commit check - 19
        //commit check - 20
        //commit check - 21


    }

    private fun fetchWeatherReport() {
        progress_bar.visibility = View.VISIBLE
        WeatherPresenter.attachView(this)
        if(NetworkConnectionUtil.isConnected(this))
            WeatherPresenter.callWeatherApi()
        else
            noNetwork()
    }

   

    override fun onDestroy() {
        super.onDestroy()
        WeatherData.weatherInfo = null
        WeatherData.city = null
    }

}

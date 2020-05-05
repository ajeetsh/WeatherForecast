package com.example.weatherforecast.ui

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.weatherforecast.R
import com.example.weatherforecast.data.WeatherData
import com.example.weatherforecast.databinding.ActivityWeatherDetailBinding

class WeatherDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_detail)
        val binding:ActivityWeatherDetailBinding = DataBindingUtil.setContentView(this,R.layout.activity_weather_detail)
        setUpActionBar()
        setData(binding)
    }
    private fun setUpActionBar(){
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
        }
        title = getString(R.string.weather_detail)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            if(it.itemId == android.R.id.home)
                finish()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun setData(binding:ActivityWeatherDetailBinding){
        WeatherData.city?.let {
            binding.cityObject = it
        }
        WeatherData.weatherInfo?.let {
            binding.weather = it
        }
        binding.executePendingBindings()
    }
}

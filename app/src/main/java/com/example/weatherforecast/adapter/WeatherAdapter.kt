package com.example.weatherforecast.adapter

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherforecast.R
import com.example.weatherforecast.data.WeatherData
import com.example.weatherforecast.data.WeatherInfo
import com.example.weatherforecast.databinding.WeatherLayoutRowBinding
import com.example.weatherforecast.ui.WeatherDetailActivity
import kotlin.coroutines.coroutineContext

class WeatherAdapter(val dataList: List<WeatherInfo>,val context:Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(p0.context)
        val binding: WeatherLayoutRowBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.weather_layout_row, p0, false)
        return WeatherViewHolder(binding.root, binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val viewHolder: WeatherViewHolder = p0 as WeatherViewHolder
        viewHolder.apply {
            bindData(dataList.get(p1),getClickListener(dataList.get(p1)))
        }
    }

    private fun getClickListener(weatherInfo: WeatherInfo):View.OnClickListener{
        return View.OnClickListener {
            WeatherData.weatherInfo = weatherInfo
            navigateToWeatherDetails()
        }
    }
    private fun navigateToWeatherDetails(){
        context.startActivity(Intent(context,WeatherDetailActivity::class.java))
    }
    class WeatherViewHolder(
        view: View,
        private val binding: WeatherLayoutRowBinding
    ) :
        RecyclerView.ViewHolder(view) {

        fun bindData(weatherInfo: WeatherInfo,onWeatherClickListener: View.OnClickListener) {
            binding.onClickListener = onWeatherClickListener
            binding.weather = weatherInfo
            binding.executePendingBindings()
        }
    }
}
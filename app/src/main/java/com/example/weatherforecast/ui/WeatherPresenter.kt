package com.example.weatherforecast.ui

import com.example.weatherforecast.api.ApiManager
import com.example.weatherforecast.network.RestConstants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object WeatherPresenter : WeatherContract.Presenter<WeatherContract.View> {
    var view: WeatherContract.View? = null

    override fun attachView(view: WeatherContract.View) {
        this.view = view
    }

    override fun callWeatherApi() {
        ApiManager.weatherService
            .getWeather(RestConstants.CITY_CODE, RestConstants.API_KEY, RestConstants.UNIT_CELCIUS)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (view != null) {
                    view!!.weatherApiResponse(it)
                }
            }, { error ->
                if (view != null) {
                    view!!.weatherApiResponse(null)
                }


            })
    }
}


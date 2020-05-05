package com.example.weatherforecast.util

import android.content.Context
import android.net.ConnectivityManager

object NetworkConnectionUtil {
    fun isConnected(context: Context): Boolean {
        val connectivityManager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        connectivityManager?.let {
            val netInfo = it.activeNetworkInfo
            netInfo?.let {
                if (it.isConnected) return true
            }
        }
        return false
    }
}
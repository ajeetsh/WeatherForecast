package com.example.weatherforecast.network

import com.example.weatherforecast.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier

object RetrofitProvider {

    private val HTTP_CONNECTION_TIMEOUT = 1200 // Seconds
    private val HTTP_READ_TIMEOUT = 1200 // Seconds

    private fun getHostNameVerifier(): HostnameVerifier {
        return HostnameVerifier { hostname, session -> true }
    }
    private fun getOkHttpClient(interceptor: HttpLoggingInterceptor, hostnameVerifier: HostnameVerifier): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.hostnameVerifier(hostnameVerifier)
        builder.connectTimeout(HTTP_CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
        builder.readTimeout(HTTP_READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
        builder.addInterceptor(interceptor)
        return builder.build()
    }

    private fun getLoggingInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logger.level = HttpLoggingInterceptor.Level.BODY
        }
        return logger
    }

    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(RestConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(getOkHttpClient(getLoggingInterceptor(),getHostNameVerifier()))
        .build()




    /*init {
        retrofit?.let {
            it.newBuilder()
                .baseUrl(RestConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            it
        }?.let {
            retrofit = it
        }
    }*/

}
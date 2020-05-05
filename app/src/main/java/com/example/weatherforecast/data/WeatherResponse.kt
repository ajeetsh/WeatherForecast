package com.example.weatherforecast.data


data class WeatherResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherInfo>,
    val message: Double
)

data class City(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String
)

data class Coord(
    val lat: Double,
    val lon: Double
)

data class WeatherInfo(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: Main,
    val rain: Rain,
    val sys: Sys,
    val weather: List<Weather>,
    val wind: Wind
)

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)

data class Main(
    val grnd_level: Double,
    val humidity: Int,
    val pressure: Double,
    val sea_level: Double,
    val temp: Double,
    val temp_kf: Double,
    val temp_max: Double,
    val temp_min: Double
)

data class Sys(
    val pod: String
)

data class Wind(
    val deg: Double,
    val speed: Double
)

data class Clouds(
    val all: Int
)

class Rain(
)
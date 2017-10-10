package com.core.app.ui.module

/**
 * Created by Ting on 17/5/22.
 */

data class ForecastResult(val city: String, val list: List<Forecast>)

data class Forecast(val dt: Long, val temp: Temperature, val pressure: Float, val humidity: Int, val weather: List<Weather>, val speed: Float, val deg: Int, val clouds: Int, val rain: Float)

data class Temperature(val day: Float, val min: Float, val max: Float, val night: Float, val eve: Float, val morn: Float)

data class Weather(val id: Long, val main: String, val description: String, val icon: String)

package com.knchst.goodweather_kotlin

import com.knchst.goodweather_kotlin.model.DailyWeatherModel
import com.knchst.goodweather_kotlin.model.WeatherModel
import retrofit.Callback
import retrofit.http.GET
import retrofit.http.Query

interface OpenWeatherApi {

    @GET("/weather/?appId=$ACCESS_TOKEN")
    fun getWeather(@Query("q") city: String, callback: Callback<WeatherModel>)

    @GET("/forecast/daily/?appId=$ACCESS_TOKEN")
    fun getDailyWeather(@Query("q") city: String, @Query("cnt") cnt: Int, callback: Callback<DailyWeatherModel>)

    companion object {
        const val ACCESS_TOKEN = "fadb1ca49b99fa29316876ee7414c427"
    }
}
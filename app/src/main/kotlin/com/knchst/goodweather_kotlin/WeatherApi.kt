package com.knchst.goodweather_kotlin

import com.knchst.goodweather_kotlin.model.WeatherModel
import retrofit.Callback
import retrofit.http.GET
import retrofit.http.Query

interface WeatherApi {

    @GET("/?appId=$ACCESS_TOKEN")
    fun getWeather(@Query("q") city: String,  callback: Callback<WeatherModel>)

    companion object {
        const val ACCESS_TOKEN = "fadb1ca49b99fa29316876ee7414c427"
    }
}
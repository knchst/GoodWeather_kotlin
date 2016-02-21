package com.knchst.goodweather_kotlin

import com.google.gson.Gson
import retrofit.RestAdapter
import retrofit.converter.GsonConverter

/**
 * Created by knchst on 2/20/16.
 */

class OpenWeatherAPIClient {
    companion object {
        fun getInstance(): RestAdapter {
            return RestAdapter.Builder()
                    .setEndpoint("http://api.openweathermap.org/data/2.5")
                    .setConverter(GsonConverter(Gson()))
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build()
        }
    }
}
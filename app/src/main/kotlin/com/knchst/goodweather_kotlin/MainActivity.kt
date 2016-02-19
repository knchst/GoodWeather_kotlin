package com.knchst.goodweather_kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.knchst.goodweather_kotlin.model.WeatherModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit.Callback
import retrofit.RestAdapter
import retrofit.RetrofitError
import retrofit.client.Response
import retrofit.converter.GsonConverter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val URL = "http://api.openweathermap.org/data/2.5/weather"

        val restAdapter = RestAdapter.Builder()
                .setEndpoint(URL)
                .setConverter(GsonConverter(Gson()))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build()

        restAdapter.create<WeatherApi>(WeatherApi::class.java).getWeather("Tokyo", object : Callback<WeatherModel> {
            override fun success(weatherModel: WeatherModel, response: Response) {
                Log.d("onResponse", response.toString())
                locationTextView.text = weatherModel.name
                descriptionTextView.text = weatherModel.weather?.get(0)?.description
                minTextView.text = weatherModel.main?.temp_min?.minus(273.15)?.toInt().toString() + "°"
                maxTextView.text = weatherModel.main?.temp_max?.minus(273.15)?.toInt().toString() + "°"

                val main = weatherModel.weather?.get(0)?.main
                val drawable: Int = when(main) {
                    "Clear" -> R.drawable.clear
                    "Clouds" -> R.drawable.clouds
                    "Rain" -> R.drawable.rain
                    "Snow" -> R.drawable.snow
                    else -> return
                }
                weatherImageView.setImageResource(drawable)
            }

            override fun failure(error: RetrofitError) {
                Log.d("onError", error.message)
            }
        })
    }
}

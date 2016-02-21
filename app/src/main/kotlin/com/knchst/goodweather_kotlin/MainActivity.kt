package com.knchst.goodweather_kotlin

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.knchst.goodweather_kotlin.model.DailyWeatherModel
import com.knchst.goodweather_kotlin.model.List
import com.knchst.goodweather_kotlin.model.WeatherModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit.Callback
import retrofit.RetrofitError
import retrofit.client.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "GoodWeather"

        OpenWeatherAPIClient.getInstance().create<OpenWeatherApi>(OpenWeatherApi::class.java).getWeather("Tokyo", object : Callback<WeatherModel> {
            override fun success(weatherModel: WeatherModel, response: Response) {
                Log.d("onResponse Weather", response.toString())
                locationTextView.text = weatherModel.name
                descriptionTextView.text = weatherModel.weather?.get(0)?.description
                minTextView.text = weatherModel.main?.temp_min?.minus(273.15)?.toInt().toString() + "°"
                maxTextView.text = weatherModel.main?.temp_max?.minus(273.15)?.toInt().toString() + "°"
                weatherImageView.setImageResource(weatherModel.weather?.get(0)?.getMain()!!)
            }

            override fun failure(error: RetrofitError) {
                Log.d("onError Weather", error.message)
            }
        })

        OpenWeatherAPIClient.getInstance().create(OpenWeatherApi::class.java).getDailyWeather("Tokyo", 7, object : Callback<DailyWeatherModel> {
            override fun success(dailyWeatherModel: DailyWeatherModel, response: Response) {
                Log.d("onResponse DailyWeather", response.toString())
                val adapter = DailyWeatherListAdapter(applicationContext)
                adapter.mDailyWeatherModel = dailyWeatherModel
                val lv = dailyListView
                lv.adapter = adapter
            }

            override fun failure(error: RetrofitError) {
                Log.d("onError DailyWeather", error.message)
            }
        })
    }

    inner class DailyWeatherListAdapter(context: Context): ArrayAdapter<List>(context, 0) {

        var mDailyWeatherModel: DailyWeatherModel? = null

        private val mInflator: LayoutInflater

        init {
            this.mInflator = LayoutInflater.from(context)
        }

        override fun getCount(): Int {
            return mDailyWeatherModel?.list?.size!!
        }

        override fun getItem(position: Int): List? {
            return mDailyWeatherModel?.list?.get(position)!!
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
            val view: View?
            val vh: ListRowHolder
            if (convertView == null) {
                view = this.mInflator.inflate(R.layout.daily_weather_row, parent, false)
                vh = ListRowHolder(view)
                view?.tag = vh
            } else {
                view = convertView
                vh = view?.tag as ListRowHolder
            }

            vh.dailyWeatherRowDateTextView?.text = mDailyWeatherModel?.list?.get(position)?.getDt()
            vh.dailyWeatherRowDescriptionTextView?.text = mDailyWeatherModel?.list?.get(position)?.weather?.first()?.description
            vh.dailyWeatherRowImageView?.setImageResource(mDailyWeatherModel?.list?.get(position)?.weather?.first()?.getMain()!!)
            vh.dailyWeatherRowMaxTextView?.text = mDailyWeatherModel?.list?.get(position)?.temp?.max?.minus(273.15)?.toInt().toString() + "°"
            vh.dailyWeatherRowMinTextView?.text = mDailyWeatherModel?.list?.get(position)?.temp?.min?.minus(273.15)?.toInt().toString() + "°"

            return view
        }

        private inner class ListRowHolder(row: View?) {
            public var dailyWeatherRowDateTextView: TextView? = null
            public var dailyWeatherRowDescriptionTextView: TextView? = null
            public var dailyWeatherRowImageView: ImageView? = null
            public var dailyWeatherRowMaxTextView: TextView? = null
            public var dailyWeatherRowMinTextView: TextView? = null

            init {
                this.dailyWeatherRowDateTextView = row?.findViewById(R.id.dailyWeatherRowDateTextView) as TextView
                this.dailyWeatherRowDescriptionTextView = row?.findViewById(R.id.dailyWeatherRowDescriptionTextView) as TextView
                this.dailyWeatherRowImageView = row?.findViewById(R.id.dailyWeatherRowImageView) as ImageView
                this.dailyWeatherRowMaxTextView = row?.findViewById(R.id.dailyWeatherRowMaxTextView) as TextView
                this.dailyWeatherRowMinTextView = row?.findViewById(R.id.dailyWeatherRowMinTextView) as TextView
            }
        }
    }
}

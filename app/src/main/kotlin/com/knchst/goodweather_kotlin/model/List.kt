package com.knchst.goodweather_kotlin.model

import android.util.Log
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by knchst on 2/21/16.
 */

class List: Serializable {
    var clouds: Double? = 0.0
    var deg: Double? = 0.0
    var dt: Long? = 0
    var humidity: Double? = 0.0
    var pressure: Double? = 0.0
    var rain: Double? = 0.0
    var speed: Double? = 0.0
    var temp: Temp? = null
    var weather: Array<Weather>? = null

    fun getDt(): String {
        val sdf = SimpleDateFormat("M/d")
        val unixTime = Date(dt!! * 1000)
        val dt = sdf.format(unixTime)
        return dt
    }
}
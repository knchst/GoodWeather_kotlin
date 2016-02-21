package com.knchst.goodweather_kotlin.model

import com.knchst.goodweather_kotlin.R

/**
 * Created by knchst on 2/20/16.
 */

class Weather {
    var description: String? = null
    var icon: String? = null
    var id: Int = 0
    var main: String? = null

    fun getMain(): Int {
        val drawable: Int = when(main) {
            "Clear" -> R.drawable.clear
            "Clouds" -> R.drawable.clouds
            "Rain" -> R.drawable.rain
            "Snow" -> R.drawable.snow
            else -> return 0
        }
        return drawable
    }
}

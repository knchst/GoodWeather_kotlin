package com.knchst.goodweather_kotlin.model

import com.knchst.goodweather_kotlin.model.Clouds
import java.io.Serializable

/**
 * Created by knchst on 2/18/16.
 */

class WeatherModel: Serializable {
    var coord: Coord? = null
    var base: String? = null
    var main: Main? = null
    var visibility: Int = 0
    var wind: Wind? = null
    var clouds: Clouds? = null
    var weather: Array<Weather>? = null
    var dt: Int = 0
    var id: Int = 0
    var name: String? = null
    var cod: Int = 0
}
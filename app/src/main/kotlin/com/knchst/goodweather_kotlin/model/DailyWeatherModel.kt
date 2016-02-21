package com.knchst.goodweather_kotlin.model

import java.io.Serializable

/**
 * Created by knchst on 2/21/16.
 */

class DailyWeatherModel: Serializable {
    var city: City? = null
    var cnt: Int? = 0
    var cod: String? = null
    var name: String? = null
    var list: Array<List>? = null
}
package com.knchst.goodweather_kotlin.model

import java.io.Serializable

/**
 * Created by knchst on 2/19/16.
 */

class Main: Serializable {
    var temp: Double = 0.0
    var pressure: Double = 0.0
    var humidity: Double = 0.0
    var temp_min: Double = 0.0
    var temp_max: Double = 0.0
}
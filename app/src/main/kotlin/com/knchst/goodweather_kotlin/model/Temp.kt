package com.knchst.goodweather_kotlin.model

import java.io.Serializable

/**
 * Created by knchst on 2/21/16.
 */

class Temp: Serializable {
    var day: Double = 0.0
    var eve: Double = 0.0
    var max: Double = 0.0
    var min: Double = 0.0
    var morn: Double = 0.0
    var night: Double = 0.0
}
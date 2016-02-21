package com.knchst.goodweather_kotlin.model

import java.io.Serializable

/**
 * Created by knchst on 2/21/16.
 */

class City: Serializable {
    var coord: Coord? = null
    var country: String? = null
    var id: Int = 0
    var name: String? = null
    var population: Int = 0
}
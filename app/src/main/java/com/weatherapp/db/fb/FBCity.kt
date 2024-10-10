package com.weatherapp.db.fb

import com.weatherapp.model.City
import com.google.android.gms.maps.model.LatLng

class FBCity {
    var name : String? = null
    var lat : Double? = null
    var lng : Double? = null
    var isMonitored : Boolean = false

    fun toCity(): City {
        val latlng =  LatLng(lat ?: 0.0, lng ?: 0.0)
        return City(name!!, location = latlng, isMonitored = isMonitored)
    }
}

fun City.toFBCity() : FBCity {
    val fbCity = FBCity()

    fbCity.name = this.name
    fbCity.lat = this.location?.latitude ?: 0.0
    fbCity.lng = this.location?.longitude ?: 0.0
    fbCity.isMonitored = this.isMonitored

    return fbCity
}
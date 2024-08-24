package com.weatherapp.ui

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

data class City(val name: String, val weather: String, var location: LatLng? = null)

class MainViewModel : ViewModel() {
    private val _cities = getCities().toMutableStateList()
    val cities : List<City>
        get() = _cities

    private var _loggedIn = mutableStateOf(false)
    val loggedIn : Boolean
        get() = _loggedIn.value
    private val listener = FirebaseAuth.AuthStateListener {
            firebaseAuth ->
        _loggedIn.value = firebaseAuth.currentUser != null
    }
    init {
        listener.onAuthStateChanged(Firebase.auth)
        Firebase.auth.addAuthStateListener(listener)
    }
    override fun onCleared() {
        Firebase.auth.removeAuthStateListener(listener)
    }

    fun remove(city: City) {
        _cities.remove(city)
    }
    fun add(city: String, location: LatLng? = null) {
        _cities.add(City(city, "Carregando clima...", location))
    }
}

fun getCities() = List(30) { i ->
    City(name = "Cidade $i", weather = "Carregando clima...")
}
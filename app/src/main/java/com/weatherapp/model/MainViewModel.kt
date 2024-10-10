package com.weatherapp.model

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.weatherapp.db.fb.FBDatabase
import com.weatherapp.repo.Repository

class MainViewModel : ViewModel(), Repository.Listener {
    private val _cities = mutableStateMapOf<String, City>()
        val cities : List<City>
            get() = _cities.values.toList()

        private val _user = mutableStateOf(User("", ""))
        val user : User
            get() = _user.value

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
        override fun onUserLoaded(user: User) { _user.value = user }
        override fun onCityAdded(city: City) { _cities[city.name] = city }
        override fun onCityRemoved(city: City) { _cities.remove(city.name) }
    }

    private fun getCities() = List(0) { i ->
        com.weatherapp.ui.City(
            name = "Cidade $i", weather = "Carregando clima..."
        )
    }
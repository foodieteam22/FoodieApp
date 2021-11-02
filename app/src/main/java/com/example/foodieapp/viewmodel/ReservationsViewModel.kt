package com.example.foodieapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodieapp.database.ReservationEntry
import com.example.foodieapp.database.RestaurantDatabase
import com.example.foodieapp.database.RestaurantFeatureEntry
import com.example.foodieapp.repository.ReservationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReservationsViewModel  (application: Application) : AndroidViewModel(application) {
    private val reservationDao = RestaurantDatabase.getDatabase(application).reservationDao()
    private val reservationRepo : ReservationRepository

    init {
        reservationRepo = ReservationRepository(reservationDao)

    }

    fun insertReservation(reservationEntry: ReservationEntry){
        //thread-safe için kullanılmakta.
        viewModelScope.launch(Dispatchers.IO) {
            reservationRepo.insertReservationRepo(reservationEntry)
        }
    }
}
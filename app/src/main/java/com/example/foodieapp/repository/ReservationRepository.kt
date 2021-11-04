package com.example.foodieapp.repository

import androidx.lifecycle.LiveData
import com.example.foodieapp.database.ReservationDao
import com.example.foodieapp.database.ReservationEntry

class ReservationRepository(val reservationDao: ReservationDao) {
    suspend fun insertReservationRepo(reservationEntry: ReservationEntry)=reservationDao.insert(reservationEntry)
    suspend fun updateReservationRepo(reservationEntry: ReservationEntry)=reservationDao.update(reservationEntry)

    suspend fun deleteReservationRepo(reservationEntry: ReservationEntry)=reservationDao.delete(reservationEntry)

    fun getResEmail(email: String)=reservationDao.getResByEmail(email)

    fun getAllReservation(date: String,deskNo: String,restaurantName: String)=reservationDao.getAllReservation(date, deskNo,restaurantName)

}
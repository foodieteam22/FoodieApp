package com.example.foodieapp.database

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

interface ReservationDao {
    @Insert
    suspend fun insert(reservationEntry :ReservationEntry)

    @Delete
    suspend fun delete(reservationEntry :ReservationEntry)

    @Update
    suspend fun update(reservationEntry :ReservationEntry)

    @Query("DELETE FROM reservation_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM reservation_table")
    fun getAllReservation(): LiveData<List<ReservationEntry>>


    @Query("SELECT * FROM reservation_table WHERE id=:id")
    fun getReservation(id: Int):LiveData<List<ReservationEntry>>
}
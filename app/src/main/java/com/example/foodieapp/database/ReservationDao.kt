package com.example.foodieapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ReservationDao {
    @Insert
    suspend fun insert(reservationEntry :com.example.foodieapp.database.ReservationEntry)

    @Delete
    suspend fun delete(reservationEntry :com.example.foodieapp.database.ReservationEntry)


    @Update
    suspend fun update(reservationEntry :com.example.foodieapp.database.ReservationEntry)

    @Query("DELETE FROM reservation_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM reservation_table")
    fun getAllReservation(): LiveData<List<ReservationEntry>>

    @Query("SELECT * FROM reservation_table WHERE email=:email")
    fun getResByEmail(email: String):LiveData<List<ReservationEntry>>


    @Query("SELECT * FROM reservation_table WHERE id=:id")
    fun getReservation(id: Int):LiveData<List<ReservationEntry>>
}
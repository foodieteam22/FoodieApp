package com.example.foodieapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RatingDao {
    //background thread için suppend ekledik
    @Insert
    suspend fun insert(ratingEntry: RatingEntry)

    @Delete
    suspend fun delete(ratingEntry: RatingEntry)

    @Update
    suspend fun update(ratingEntry: RatingEntry)

    @Query("SELECT * FROM rating_table WHERE restaurantId=:restaurantId")
    fun getRating(restaurantId: Int):LiveData<RatingEntry>

    @Query("SELECT * FROM rating_table")
    suspend fun getAll(): List<RatingEntry>

}
package com.example.foodieapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RestaurantDao {
    //background thread için suppend ekledik
    @Insert
    suspend fun insert(restaurantEntry :RestaurantEntry)

    @Delete
    suspend fun delete(restaurantEntry :RestaurantEntry)

    @Update
    suspend fun update(restaurantEntry :RestaurantEntry)

    @Query("DELETE FROM restaurant_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM restaurant_table ORDER BY timestamp DESC")
    fun getAllRestaurants():LiveData<List<RestaurantEntry>>


    @Query("SELECT * FROM restaurant_table WHERE id=:id")
    fun getRestaurant(id: String):LiveData<List<RestaurantEntry>>

}
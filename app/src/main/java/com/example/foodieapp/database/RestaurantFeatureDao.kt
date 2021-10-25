package com.example.foodieapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RestaurantFeatureDao {

    @Insert
    suspend fun insert(restaurantFeatureEntry :RestaurantFeatureEntry)

    @Delete
    suspend fun delete(restaurantFeatureEntry :RestaurantFeatureEntry)

    @Update
    suspend fun update(restaurantFeatureEntry :RestaurantFeatureEntry)



    @Query("SELECT * FROM restaurant_feature_table WHERE restaurantId=:restaurantId")
    fun getRestaurantFeatures(restaurantId: Int): LiveData<List<RestaurantFeatureEntry>>


}
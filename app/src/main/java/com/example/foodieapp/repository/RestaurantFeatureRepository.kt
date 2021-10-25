package com.example.foodieapp.repository

import com.example.foodieapp.database.RestaurantFeatureDao
import com.example.foodieapp.database.RestaurantFeatureEntry

class RestaurantFeatureRepository(val restaurantFeatureDao: RestaurantFeatureDao) {

    suspend fun insertRestaurantFeatureRepo(restaurantFeatureEntry: RestaurantFeatureEntry)=restaurantFeatureDao.insert(restaurantFeatureEntry)

    suspend fun updateRestaurantFeatureRepo(restaurantFeatureEntry: RestaurantFeatureEntry)=restaurantFeatureDao.update(restaurantFeatureEntry)

    suspend fun deleteRestaurantFeatureRepo(restaurantFeatureEntry: RestaurantFeatureEntry)=restaurantFeatureDao.delete(restaurantFeatureEntry)


    fun getRestaurantFeatureRepo(id:Int)=restaurantFeatureDao.getRestaurantFeatures(id)
}
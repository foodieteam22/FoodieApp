package com.example.foodieapp.repository

import com.example.foodieapp.database.RestaurantDao
import com.example.foodieapp.database.RestaurantEntry

class RestaurantRepository(val restaurantDao:RestaurantDao) {
    suspend fun insertRestaurantRepo(restaurantEntry: RestaurantEntry)=restaurantDao.insert(restaurantEntry)

    suspend fun updateRestaurantRepo(restaurantEntry: RestaurantEntry)=restaurantDao.update(restaurantEntry)

    suspend fun deleteRestaurantRepo(restaurantEntry: RestaurantEntry)=restaurantDao.delete(restaurantEntry)

    fun getAllRestaurantRepo()=restaurantDao.getAllRestaurants()

    fun getRestaurantRepo(id:String)=restaurantDao.getRestaurant(id)
}
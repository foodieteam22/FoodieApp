package com.example.foodieapp.repository

import com.example.foodieapp.database.RatingDao
import com.example.foodieapp.database.RatingEntry


class RatingRepository(val ratingDao: RatingDao) {
    suspend fun insertRatingRepo(ratingEntry: RatingEntry)=ratingDao.insert(ratingEntry)

    suspend fun updateRatingRepo(ratingEntry: RatingEntry)=ratingDao.update(ratingEntry)

    suspend fun deleteRatingRepo(ratingEntry: RatingEntry)=ratingDao.delete(ratingEntry)

    fun getRating(restaurantId:Int)=ratingDao.getRating(restaurantId)
}
package com.example.foodieapp.repository

import com.example.foodieapp.database.FavoriteDao
import com.example.foodieapp.database.FavoriteEntry
import com.example.foodieapp.database.UserDao
import com.example.foodieapp.database.UserEntry

class FavoriteRepository(val favoriteDao: FavoriteDao) {

    suspend fun insertFavoriteRepo(favoriteEntry: FavoriteEntry)=favoriteDao.insert(favoriteEntry)

    suspend fun deleteFavoriteRepo(favoriteEntry: FavoriteEntry)=favoriteDao.delete(favoriteEntry)

    fun geFavoriteByEmail(email: String)=favoriteDao.getFavoriteByEmail(email)
}
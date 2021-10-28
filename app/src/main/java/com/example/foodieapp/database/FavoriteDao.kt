package com.example.foodieapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDao {
    @Insert
    suspend fun insert (favoriteEntry: FavoriteEntry)

    @Delete
    suspend fun delete(favoriteEntry: FavoriteEntry)

    @Query("SELECT * FROM favorite_table WHERE userEmail=:userEmail")
    fun getFavoriteByEmail(userEmail: String): LiveData<List<UserEntry>>
}
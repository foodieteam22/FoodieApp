package com.example.foodieapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert
    suspend fun insert (userEntry: UserEntry)

    @Delete
    suspend fun delete(userEntry: UserEntry)

    @Query("SELECT *FROM user_table WHERE id = :id")
    suspend fun getUser(id:Int) :  UserEntry

    @Query("UPDATE user_table SET downloadUrl = :downloadUrl WHERE id =:id")
    suspend fun updatePhoto(downloadUrl: String?, id: Int)

    @Update
    suspend fun update(userEntry: UserEntry)



}
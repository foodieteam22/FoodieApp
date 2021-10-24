package com.example.foodieapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert
    suspend fun insert (userEntry: UserEntry)

    @Delete
    suspend fun delete(userEntry: UserEntry)

   /* @Query("SELECT *FROM user_table WHERE id = :id")
    suspend fun getUser(id:Int) :  UserEntry*/

    @Query("SELECT * FROM user_table WHERE id=:id")
    fun getUser(id: String):LiveData<List<UserEntry>>

    @Query("SELECT * FROM user_table WHERE email=:email")
    fun getUserByEmail(email: String):LiveData<List<UserEntry>>

    @Update
    suspend fun update(userEntry: UserEntry)

    @Query("UPDATE user_table SET downloadUrl = :downloadUrl WHERE email =:email")
    fun updatePhoto(downloadUrl: String?, email: String)


}
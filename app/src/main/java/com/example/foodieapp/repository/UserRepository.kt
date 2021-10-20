package com.example.foodieapp.repository

import androidx.lifecycle.LiveData
import com.example.foodieapp.database.RatingEntry
import com.example.foodieapp.database.UserDao
import com.example.foodieapp.database.UserEntry

class UserRepository(val userDao: UserDao) {
    suspend fun insertUserRepo(userEntry: UserEntry)=userDao.insert(userEntry)

    suspend fun updateUserRepo(userEntry: UserEntry)=userDao.update(userEntry)

    suspend fun deleteUserRepo(userEntry: UserEntry)=userDao.delete(userEntry)

    suspend fun getUser(id:Int)=userDao.getUser(id)



}
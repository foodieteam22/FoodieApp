package com.example.foodieapp.repository

import com.example.foodieapp.database.UserDao
import com.example.foodieapp.database.UserEntry

class UserRepository(val userDao: UserDao) {
    suspend fun insertUserRepo(userEntry: UserEntry)=userDao.insert(userEntry)

    suspend fun updateUserRepo(userEntry: UserEntry)=userDao.update(userEntry)

    fun updatePhoto(userEntry: UserEntry)=userDao.updatePhoto(userEntry.downloadUrl, userEntry.email)

    suspend fun deleteUserRepo(userEntry: UserEntry)=userDao.delete(userEntry)

    fun getUser(id: String)=userDao.getUser(id)

    fun getUserEmail(email: String)=userDao.getUserByEmail(email)



}
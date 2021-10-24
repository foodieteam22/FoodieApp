package com.example.foodieapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodieapp.database.RestaurantDatabase
import com.example.foodieapp.database.RestaurantEntry
import com.example.foodieapp.database.UserEntry
import com.example.foodieapp.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application): AndroidViewModel(application) {

    private val userDao = RestaurantDatabase.getDatabase(application).userDao()
    private val userRepo : UserRepository
    val userLiveData = MutableLiveData<UserEntry>()

    init {
        userRepo = UserRepository(userDao)

    }

    fun updateUser(user: UserEntry){
        viewModelScope.launch(Dispatchers.IO) {
            userRepo.updateUserRepo(user)
        }
    }
    fun getUserById(id: String): LiveData<List<UserEntry>> {
        return userRepo.getUser(id)
    }
    fun getUserByEmail(email: String): LiveData<List<UserEntry>> {
        return userRepo.getUserEmail(email)
    }
    fun updatePhoto(user: UserEntry){
        viewModelScope.launch(Dispatchers.IO) {
            userRepo.updatePhoto(user)
        }
    }


}
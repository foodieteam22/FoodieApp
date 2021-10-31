package com.example.foodieapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodieapp.database.CommentEntry
import com.example.foodieapp.database.RestaurantDatabase
import com.example.foodieapp.database.UserDao
import com.example.foodieapp.database.UserEntry
import com.example.foodieapp.repository.CommentRepository
import com.example.foodieapp.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(application: Application): AndroidViewModel (application){

    private val userDao = RestaurantDatabase.getDatabase(application).userDao()
    private val userRepo : UserRepository
    private val _userLiveData = MutableLiveData<List<UserEntry>>()
    val  userLiveData: LiveData<List<UserEntry>>
    get() = _userLiveData




    init {
        userRepo = UserRepository(userDao)

    }

    fun insertUser(user: UserEntry){
        viewModelScope.launch(Dispatchers.IO) {
            userRepo.insertUserRepo(user)
        }
    }
    fun getUserByEmail(email: String): LiveData<List<UserEntry>> {
        return userRepo.getUserEmail(email)
    }
    fun update(user: UserEntry){
        viewModelScope.launch(Dispatchers.IO) {
            userRepo.updateUserRepo(user)
        }
    }
    fun getAll(){
        viewModelScope.launch(Dispatchers.IO) {
            _userLiveData.value=userRepo.getAllUser()
        }
    }

}
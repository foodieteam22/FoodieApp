package com.example.foodieapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.foodieapp.database.CommentEntry
import com.example.foodieapp.database.FavoriteEntry
import com.example.foodieapp.database.RestaurantDatabase
import com.example.foodieapp.database.UserEntry
import com.example.foodieapp.repository.FavoriteRepository
import com.example.foodieapp.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application):AndroidViewModel(application) {
    private val favoriteDao = RestaurantDatabase.getDatabase(application).favoriteDao()
    private val favoriteRepository : FavoriteRepository
    init {
        favoriteRepository = FavoriteRepository(favoriteDao)

    }
    fun getFavoriteByEmail(email: String): LiveData<List<FavoriteEntry>> {
        return favoriteRepository.geFavoriteByEmail(email)
    }
    fun insertFavorite(favoriteEntry: FavoriteEntry){
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.insertFavoriteRepo(favoriteEntry)
        }
    }
    fun deleteFavorite(favoriteEntry: FavoriteEntry){
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.deleteFavoriteRepo(favoriteEntry)

        }
    }
}
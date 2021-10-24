package com.example.foodieapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.foodieapp.database.RestaurantDatabase
import com.example.foodieapp.database.RestaurantEntry
import com.example.foodieapp.database.RestaurantFeatureEntry
import com.example.foodieapp.repository.RestaurantFeatureRepository
import com.example.foodieapp.repository.RestaurantRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RestaurantDetailViewModel (application: Application) : AndroidViewModel(application) {

    private val restFeatureDao = RestaurantDatabase.getDatabase(application).restaurantFeatureDao()
    private val restFeatureRepo :RestaurantFeatureRepository

    init {
        restFeatureRepo = RestaurantFeatureRepository(restFeatureDao)

    }
    fun getRestFetureById(restaurantId: String):LiveData<List<RestaurantFeatureEntry>>{
        return restFeatureRepo.getRestaurantFeatureRepo(restaurantId)
    }

    fun insertRestFeature(restaurantFeatureEntry: RestaurantFeatureEntry){
        //thread-safe için kullanılmakta.
        viewModelScope.launch(Dispatchers.IO) {
            restFeatureRepo.insertRestaurantFeatureRepo(restaurantFeatureEntry)
        }
    }

}

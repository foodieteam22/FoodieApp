package com.example.foodieapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.foodieapp.database.RestaurantDatabase
import com.example.foodieapp.database.RestaurantEntry
import com.example.foodieapp.repository.RestaurantRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RestaurantViewModel(application: Application):AndroidViewModel(application)
{
    private val restDao = RestaurantDatabase.getDatabase(application).resturantDao()
    private val restRepo :RestaurantRepository
    private val getallRest:LiveData<List<RestaurantEntry>>

    init {
        restRepo = RestaurantRepository(restDao)
        getallRest =restRepo.getAllRestaurantRepo()

    }
    fun getRestById(id: String):LiveData<List<RestaurantEntry>>{
        return restRepo.getRestaurantRepo(id)
    }

    fun insertRest(restaurantEntry: RestaurantEntry){
        //thread-safe için kullanılmakta.
        viewModelScope.launch(Dispatchers.IO) {
            restRepo.insertRestaurantRepo(restaurantEntry)
        }
    }

    fun deleteRest(restaurantEntry: RestaurantEntry){
        viewModelScope.launch(Dispatchers.IO) {

            restRepo.deleteRestaurantRepo(restaurantEntry)
            
        }
    }

    fun getAllRest():LiveData<List<RestaurantEntry>>{
        return restRepo.getAllRestaurantRepo()
    }


}
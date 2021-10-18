package com.example.foodieapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.foodieapp.database.RatingEntry
import com.example.foodieapp.database.RestaurantDatabase
import com.example.foodieapp.repository.RatingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RatingViewModel(application: Application):AndroidViewModel(application)
{
    private val rateDao = RestaurantDatabase.getDatabase(application).ratingDao()
    private val rateRepo :RatingRepository

    init {
        rateRepo = RatingRepository(rateDao)
    }
    fun getRating(restaurantId:Int):LiveData<RatingEntry>{
        return rateRepo.getRating(restaurantId)
    }

    fun insertRating(ratingEntry: RatingEntry){
        //thread-safe için kullanılmakta.
        viewModelScope.launch(Dispatchers.IO) {
            rateRepo.insertRatingRepo(ratingEntry)
        }
    }

    fun deleteRating(ratingEntry: RatingEntry){
        viewModelScope.launch(Dispatchers.IO) {

            rateRepo.deleteRatingRepo(ratingEntry)
            
        }
    }


}
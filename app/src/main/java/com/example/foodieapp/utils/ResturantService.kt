package com.example.foodieapp.utils

import retrofit2.Call
import com.example.foodieapp.model.RestaurantModel
import retrofit2.http.GET
import retrofit2.http.Url

interface ResturantService
{
    @GET
    fun getRest(@Url url:String): Call<List<RestaurantModel>>

}
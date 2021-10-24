package com.example.foodieapp.utils

import com.example.foodieapp.model.MenuModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


interface MenuService {
    @GET
    fun getMenu(@Url url:String): Call<List<MenuModel>>
}



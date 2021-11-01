package com.example.foodieapp.model

import com.google.gson.annotations.SerializedName

data class RestaurantModel(

    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("rest-items")
    var items: List<RestaurantDetailModel>,

)

package com.example.foodieapp.model

import com.google.gson.annotations.SerializedName

data class RestaurantModel(

    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("location")
    val location: String,

    @SerializedName("detail-items")
    val detail : RestaurantDetailModel
)

package com.example.foodieapp.model

import com.google.gson.annotations.SerializedName

data class RestaurantDetailModel (

    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,
    @SerializedName("hasFeature")
    var hasFeature:Boolean,


    )
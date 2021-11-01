package com.example.foodieapp.model

import com.google.gson.annotations.SerializedName

data class RestaurantModel(

    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("rating")
    val rating: String,

    @SerializedName("ratio")
    val ratio: String,

    @SerializedName("percentile")
    val percentile: String,

    @SerializedName("imageResource")
    val imageResource: String,



    @SerializedName("rest-items")
    var items: List<RestaurantDetailModel>,

)

package com.example.foodieapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RestaurantModel(

    @SerializedName("id")
    val id: Int,

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

    @SerializedName("workingHours")
    val workingHours: String,

    @SerializedName("city")
    val city: String,

    @SerializedName("county")
    val county: String,


    @SerializedName("rest-items")
    var items: List<RestaurantDetailModel>,

):Serializable

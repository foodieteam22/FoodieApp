package com.example.foodieapp.model

import com.google.gson.annotations.SerializedName

data class MenuListModel (
    @SerializedName("restaurantId")
    var restaurantId: Int,

    @SerializedName("categories")
    var categories: List<MenuModel>,
)
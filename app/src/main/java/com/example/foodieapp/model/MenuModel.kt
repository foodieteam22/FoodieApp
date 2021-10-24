package com.example.foodieapp.model

import com.google.gson.annotations.SerializedName

data class MenuModel (
    @SerializedName("id")
    var id: Long,
   // @SerializedName("restaurantId")
   // var restaurantId: Long,
    @SerializedName("name")
    var name: String,
    @SerializedName("menu-items")
    var items: List<MenuItemModel>,
)
package com.example.foodieapp.model

import com.google.gson.annotations.SerializedName

data class MenuItemModel(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Float,
    @SerializedName("category_name")
    val categoryName: String
)
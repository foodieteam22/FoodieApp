package com.example.foodieapp.model

data class MenuModel (
    val id: Long,
    val restaurantId: Long,
    val name: String,
    val items: List<MenuItemModel>,
)
package com.example.foodieapp.model

data class RestaurantModel(
    val id: Long,
    val name: String,
    val location: String,
)
object Rest {
    val restaurants = listOf<RestaurantModel>(
        RestaurantModel(12312321312,"name","lc") ,
        RestaurantModel(12312321312,"name","lc")

    )
}
package com.example.foodieapp.database
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "restaurant_table")
data class RestaurantEntry(
    @PrimaryKey(autoGenerate = true)
    var id :Int,
    var restaurantName:String,
    var timestamp: Long
):Parcelable
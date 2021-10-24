package com.example.foodieapp.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "restaurant_feature_table")
data class RestaurantFeatureEntry(
    @PrimaryKey(autoGenerate = true)
    var restaurantId :Int,
    var hasFeature:Boolean,
    var FeatureDescription:String
): Parcelable
package com.example.foodieapp.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "rating_table")
data class RatingEntry (
    @PrimaryKey(autoGenerate = true)
    var id :Int,
    var restaurantId:Int,
    var timestamp: Long,
    var hygieneRating: Float,
    var tasteRating: Float,
    var serviceRating: Float,
    var avgRating: Float
): Parcelable
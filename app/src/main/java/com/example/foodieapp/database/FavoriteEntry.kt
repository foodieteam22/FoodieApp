package com.example.foodieapp.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "favorite_table")
data class FavoriteEntry
    (@PrimaryKey(autoGenerate = true)
    var id :Int,
    @ColumnInfo(name = "userEmail")
    val userEmail :String,
    @ColumnInfo(name = "restaurantName")
    val restaurantName  : String): Parcelable
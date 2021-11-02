package com.example.foodieapp.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "reservation_table")
data class ReservationEntry(@PrimaryKey(autoGenerate = true)
                            var id :Int,
                            val restaurantName :String,
                            val deskNo  : String): Parcelable

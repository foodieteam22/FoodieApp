package com.example.foodieapp.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "comments_table")
data class CommentEntry (
    @PrimaryKey(autoGenerate = true)
    var id :Int,
    var restaurantId:Int,
    var description:String,
    var author:String,
    var timestamp: Long,
    var rating: Float
    ): Parcelable

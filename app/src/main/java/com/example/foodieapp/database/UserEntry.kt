package com.example.foodieapp.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class UserEntry (
    @PrimaryKey(autoGenerate = true)
    var id :Int,
    @ColumnInfo(name = "email")
    val email :String,
    @ColumnInfo(name = "downloadUrl")
    val downloadUrl  : String): Parcelable

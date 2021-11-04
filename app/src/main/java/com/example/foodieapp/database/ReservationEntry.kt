package com.example.foodieapp.database

import android.os.Parcelable
import android.provider.ContactsContract
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "reservation_table", indices = [
    Index(
        value = [ "deskNo", "date" ],
        unique = true
    )
])
data class ReservationEntry(@PrimaryKey(autoGenerate = true)
                            var id :Int,
                            val restaurantName :String,
                            val email: String,
                            val deskNo  : String,
                            val personCount: String,
                            val date: String
                            ): Parcelable

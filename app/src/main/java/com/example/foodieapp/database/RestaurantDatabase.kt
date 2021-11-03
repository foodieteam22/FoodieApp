package com.example.foodieapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(RestaurantEntry::class,CommentEntry::class,RatingEntry::class,UserEntry::class,RestaurantFeatureEntry::class,ReservationEntry::class),version = 2,exportSchema = false)
abstract class RestaurantDatabase:RoomDatabase() {

    abstract fun resturantDao():RestaurantDao
    abstract fun commentDao():CommentDao
    abstract fun ratingDao():RatingDao
    abstract fun restaurantFeatureDao():RestaurantFeatureDao
    abstract fun userDao():UserDao
    abstract fun reservationDao():ReservationDao

    companion object
   {
       private var INSTANCE :RestaurantDatabase? =null
       fun  getDatabase(context: Context):RestaurantDatabase
       {
           synchronized(this){
               var instance = INSTANCE
               if(instance==null) // instance ilkez oluşturuyor ise
               {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    RestaurantDatabase::class.java,
                    "restaurant_database").fallbackToDestructiveMigration().build()
                   INSTANCE = instance
               }
                return instance
           }
       }
   }
}
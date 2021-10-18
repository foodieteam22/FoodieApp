package com.example.foodieapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CommentDao {
    //background thread için suppend ekledik
    @Insert
    suspend fun insert(commentEntry: CommentEntry)

    @Delete
    suspend fun delete(commentEntry: CommentEntry)

    @Update
    suspend fun update(commentEntry: CommentEntry)

    @Query("SELECT * FROM comments_table where author=:author ORDER BY timestamp DESC")
    fun getCommentsByAuthor(author:String):LiveData<List<CommentEntry>>


    @Query("SELECT * FROM comments_table WHERE restaurantId=:restaurantId ORDER BY timestamp DESC")
    fun getRestaurantComments(restaurantId: Int):LiveData<List<CommentEntry>>

}
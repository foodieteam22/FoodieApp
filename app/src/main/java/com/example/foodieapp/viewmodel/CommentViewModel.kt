package com.example.foodieapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.foodieapp.database.CommentEntry
import com.example.foodieapp.database.RestaurantDatabase
import com.example.foodieapp.repository.CommentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CommentViewModel(application: Application):AndroidViewModel(application)
{
    private val commentDao = RestaurantDatabase.getDatabase(application).commentDao()
    private val commentRepo :CommentRepository

    init {
        commentRepo = CommentRepository(commentDao)
    }
    fun getCommentsByAuthor(author: String):LiveData<List<CommentEntry>>{
        return commentRepo.getCommentsByAuthor(author)
    }

    fun getRestaurantComments(restaurantId: Int):LiveData<List<CommentEntry>>{
        return commentRepo.getRestaurantComments(restaurantId)
    }

    fun insertComment(commentEntry: CommentEntry){
        //thread-safe için kullanılmakta.
        viewModelScope.launch(Dispatchers.IO) {
            commentRepo.insertCommentRepo(commentEntry)
        }
    }

    fun deleteComment(commentEntry: CommentEntry){
        viewModelScope.launch(Dispatchers.IO) {

            commentRepo.deleteCommentRepo(commentEntry)
            
        }
    }


}
package com.example.foodieapp.repository

import com.example.foodieapp.database.CommentDao
import com.example.foodieapp.database.CommentEntry

class CommentRepository(val commentDao:CommentDao) {
    suspend fun insertCommentRepo(CommentEntry: CommentEntry)=commentDao.insert(CommentEntry)

    suspend fun updateCommentRepo(CommentEntry: CommentEntry)=commentDao.update(CommentEntry)

    suspend fun deleteCommentRepo(CommentEntry: CommentEntry)=commentDao.delete(CommentEntry)

    fun getCommentsByAuthor(author: String)=commentDao.getCommentsByAuthor(author)

    fun getRestaurantComments(restaurantId:Int)=commentDao.getRestaurantComments(restaurantId)

    fun getAllComments()=commentDao.getAllComments()
}
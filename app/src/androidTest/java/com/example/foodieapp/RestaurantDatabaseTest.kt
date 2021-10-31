package com.example.foodieapp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foodieapp.database.*
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
 class RestaurantDatabaseTest: TestCase(){

    private lateinit var db: RestaurantDatabase
    private lateinit var userDao: UserDao
    private lateinit var commentDao: CommentDao
    private lateinit var ratingDao: RatingDao

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, RestaurantDatabase::class.java).build()
        userDao = db.userDao()
        commentDao = db.commentDao()
        ratingDao = db.ratingDao()



    }

    @After
    fun closeDb() {
        db.close()

    }

    @Test
    fun writeAndReadUser() = runBlocking{
        val testUser = UserEntry(5,"testdilek@gmail.com","ddddd")
        userDao.insert(testUser)
        val users= userDao.getAllUser()
        assertThat(users.contains(testUser)).isTrue()


    }
    @Test
    fun writeAndReadComment() = runBlocking{
        val testComment = CommentEntry(1,2,"comment test description","Test author",100, 1F)
        commentDao.insert(testComment)
        val comments= commentDao.getAll()
        assertThat(comments.contains(testComment)).isTrue()


    }
    @Test
    fun writeAndReadRating() = runBlocking{
        val testRating = RatingEntry(1,1,100,1F,2F,3F,4F)
        ratingDao.insert(testRating)
        val ratings= ratingDao.getAll()
        assertThat(ratings.contains(testRating)).isTrue()

    }
}
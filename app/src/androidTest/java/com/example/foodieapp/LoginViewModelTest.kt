package com.example.foodieapp

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.room.util.DBUtil
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foodieapp.database.RestaurantDatabase
import com.example.foodieapp.database.UserEntry
import com.example.foodieapp.repository.UserRepository
import com.example.foodieapp.view.LoginFragmentDirections
import com.example.foodieapp.viewmodel.LoginViewModel
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock


@RunWith(AndroidJUnit4::class)
class LoginViewModelTest: TestCase() {


   private lateinit var viewModel:LoginViewModel


    @get:Rule
    val instantTaskExecutorRule= InstantTaskExecutorRule()



    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val db = Room.inMemoryDatabaseBuilder(context, RestaurantDatabase::class.java).allowMainThreadQueries().build()
        val userRepo= UserRepository(db.userDao())
        viewModel = LoginViewModel(ApplicationProvider.getApplicationContext())




    }

    @Test
    fun testLoginViewModel(){
        val testUser = UserEntry(6,"testviewmodel@gmail.com","d")
        viewModel.insertUser(testUser)
        viewModel.getAll()
        val result = viewModel.userLiveData.getOrAwaitValue().find {
            it.id==6 && it.email=="testviewmodel@gmail.com"&&it.downloadUrl=="d"
        }
        assertThat(result != null).isTrue()



    }

}
package com.example.foodieapp.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.example.foodieapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.statusBarColor = ContextCompat.getColor(this, R.color.statusBarColor)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#181A1C")))
        window.navigationBarColor = ContextCompat.getColor(this, R.color.navigationBarColor)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.restaurant_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.logout) {
            auth = Firebase.auth
            auth.signOut()
            val action = ProfileFragmentDirections.actionProfileFragmentToLoginFragment()
            Navigation.findNavController(this, R.id.fragmentContainerView).navigate(action)


        }
        return super.onOptionsItemSelected(item)
    }
}
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
        getSupportActionBar()?.setBackgroundDrawable(ColorDrawable(getResources().getColor(android.R.color.transparent)));
      //  supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#181A1C")))
        window.navigationBarColor = ContextCompat.getColor(this, R.color.navigationBarColor)
    }


}
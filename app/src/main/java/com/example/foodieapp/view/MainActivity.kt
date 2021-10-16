package com.example.foodieapp.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.foodieapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.statusBarColor = ContextCompat.getColor(this, R.color.statusBarColor)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#181A1C")))
        window.navigationBarColor = ContextCompat.getColor(this, R.color.navigationBarColor)
    }
}
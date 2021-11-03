package com.example.foodieapp.utils

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.foodieapp.R


@BindingAdapter("setRestDetailIcon")
    fun SetRestDetailIcon (view: ImageView, hasFeature: Boolean) {
        when (hasFeature) {
            true -> {
                view.setBackgroundResource (R.drawable.ic_x_tick_available)
            }

            false-> {
                view.setBackgroundResource (R.drawable.ic_x_not_available)
            }
        }
    }

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    Glide.with(imageView.context).load(url).into(imageView)
}
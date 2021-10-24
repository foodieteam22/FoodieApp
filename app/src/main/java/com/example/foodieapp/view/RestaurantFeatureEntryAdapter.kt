package com.example.foodieapp.view
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodieapp.databinding.RowRestaurantFeatureBinding
import com.example.foodieapp.database.RestaurantFeatureEntry

class RestaurantFeatureEntryAdapter (val list: ArrayList<RestaurantFeatureEntry>) : RecyclerView.Adapter<RestaurantFeatureEntryAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: RowRestaurantFeatureBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind (restaurantFeatureEntry: RestaurantFeatureEntry) {
            binding.restaurantFeatureEntry = restaurantFeatureEntry
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RowRestaurantFeatureBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.binding.tvExample.text = list[position].text
    }

    override fun getItemCount(): Int {
        return list.size
    }
}



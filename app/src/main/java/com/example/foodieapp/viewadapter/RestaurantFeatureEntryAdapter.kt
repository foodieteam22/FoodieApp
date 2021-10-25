package com.example.foodieapp.viewadapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodieapp.database.CommentEntry
import com.example.foodieapp.databinding.RowRestaurantFeatureBinding
import com.example.foodieapp.database.RestaurantFeatureEntry
import com.example.foodieapp.databinding.MenuRowLayoutBinding
import com.example.foodieapp.model.MenuModel

class RestaurantFeatureEntryAdapter (private val featuresData: List<RestaurantFeatureEntry>) : RecyclerView.Adapter<RestaurantFeatureEntryAdapter.ViewHolder>() {

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
        val current = featuresData.get(position)
        holder.bind(current)
        //holder.binding.tvExample.text = list[position].text
    }

    override fun getItemCount(): Int {
        return featuresData.size
    }
}



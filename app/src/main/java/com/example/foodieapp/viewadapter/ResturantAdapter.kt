package com.example.foodieapp.viewadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodieapp.R
import com.example.foodieapp.database.UserEntry
import com.example.foodieapp.databinding.RestaurantRowLayoutBinding
import com.example.foodieapp.model.RestaurantModel
import com.example.foodieapp.view.RestaurantDetailFragmentDirections
import com.example.foodieapp.view.RestaurantFragmentDirections

public class ResturantAdapter(
    val context: Context?,
    private  val restData : List<RestaurantModel>,
    private val userEntry: UserEntry
):RecyclerView.Adapter<ResturantAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RestaurantRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    inner class ViewHolder(val binding:RestaurantRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(restModel: RestaurantModel) {
            binding.restaurantModel = restModel
            binding.executePendingBindings()

            itemView.setOnClickListener(){
                val position:Int = adapterPosition
                val action = RestaurantFragmentDirections.actionRestaurantFragmentToRestaurantDetailFragment(userEntry,restModel)
                Navigation.findNavController(itemView).navigate(action)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = restData.get(position)
        holder.binding.restItemRecyclerView.layoutManager= LinearLayoutManager(context)
        holder.binding.restItemRecyclerView.adapter= RestaurantItemAdapter(current.items)
        holder.bind(current)

    }



    override fun getItemCount(): Int {
        return restData.count()
    }

}
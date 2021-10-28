package com.example.foodieapp.viewadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodieapp.databinding.FragmentRestaurantBinding
import com.example.foodieapp.model.RestaurantModel

public class ResturantAdapter(
    val context: Context?,
    private  val restData : List<RestaurantModel>,
):RecyclerView.Adapter<ResturantAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentRestaurantBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    inner class ViewHolder(val binding:FragmentRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(restModel: RestaurantModel) {
            binding.restaurantModel = restModel
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = restData.get(position)
        holder.binding.resturantrecyclerView.layoutManager = LinearLayoutManager(context)
        holder.bind(current)

    }

    override fun getItemCount(): Int {
        return restData.count()
    }

}
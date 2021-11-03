package com.example.foodieapp.viewadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodieapp.databinding.RestaurantRowItemLayoutBinding
import com.example.foodieapp.model.RestaurantDetailModel

private const val limit = 3
public class RestaurantItemAdapter(
    private val restItemData: List<RestaurantDetailModel>
    ): RecyclerView.Adapter<RestaurantItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RestaurantRowItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    inner class ViewHolder(val binding: RestaurantRowItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(restdeatilModel: RestaurantDetailModel) {
            binding.restaurantDetailModel = restdeatilModel
            binding.executePendingBindings()
        }
    }



    override fun onBindViewHolder(holder: RestaurantItemAdapter.ViewHolder, position: Int) {
        val current = restItemData.get(position)
        holder.bind(current)
    }

    override fun getItemCount(): Int {
        if(restItemData.count() > limit)
        {
            return limit;
        }
        else
        {
            return restItemData.count()
        }

    }
}
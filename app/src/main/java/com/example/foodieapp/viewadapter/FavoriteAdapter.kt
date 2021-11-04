package com.example.foodieapp.viewadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodieapp.database.FavoriteEntry
import com.example.foodieapp.database.ReservationEntry
import com.example.foodieapp.databinding.FavoriteRowBinding
import com.example.foodieapp.databinding.ReservationListRowBinding

class FavoriteAdapter(private  val FavoriteList: ArrayList<FavoriteEntry>):RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder> (){
    class FavoriteHolder(val binding: FavoriteRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHolder {
        val binding = FavoriteRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FavoriteAdapter.FavoriteHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
        holder.binding.tvRecResturantName.text= FavoriteList.get(position).restaurantName
    }

    override fun getItemCount(): Int {
       return FavoriteList.size
    }
}
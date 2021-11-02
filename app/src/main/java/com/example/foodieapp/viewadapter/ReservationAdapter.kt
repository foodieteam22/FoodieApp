package com.example.foodieapp.viewadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodieapp.database.ReservationEntry
import com.example.foodieapp.databinding.ReservationListRowBinding

class ReservationAdapter(private  val ReservationList: ArrayList<ReservationEntry>) : RecyclerView.Adapter<ReservationAdapter.ReservationHolder>(){
    class ReservationHolder(val binding: ReservationListRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationHolder {
        val binding = ReservationListRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ReservationHolder(binding)
    }

    override fun onBindViewHolder(holder: ReservationHolder, position: Int) {
        holder.binding.textView.text = ReservationList.get(position).restaurantName
    }

    override fun getItemCount(): Int {
      return ReservationList.size
    }
}
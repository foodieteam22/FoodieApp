package com.example.foodieapp.viewadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodieapp.database.ReservationEntry
import com.example.foodieapp.databinding.ReservationListRowBinding
import com.example.foodieapp.utils.downloadImage
import com.example.foodieapp.utils.makePlaceholder


class ReservationAdapter(private  val ReservationList: ArrayList<ReservationEntry>) : RecyclerView.Adapter<ReservationAdapter.ReservationHolder>(){
    class ReservationHolder(val binding: ReservationListRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationHolder {
        val binding = ReservationListRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ReservationHolder(binding)
    }

    override fun onBindViewHolder(holder: ReservationHolder, position: Int) {

            holder.binding.tvRecResturantName.text = ReservationList.get(position).restaurantName
            holder.binding.tvRecDeskNo.text= ReservationList.get(position).deskNo
           holder.binding.tvRecPersonCount.text = ReservationList.get(position).personCount
           holder.binding.tvRecDate.text = ReservationList.get(position).date


    }

    override fun getItemCount(): Int {
      return ReservationList.size
    }
}
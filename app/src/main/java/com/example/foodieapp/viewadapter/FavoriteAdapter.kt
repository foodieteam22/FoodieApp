package com.example.foodieapp.viewadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodieapp.R
import com.example.foodieapp.database.CommentEntry
import com.example.foodieapp.database.FavoriteEntry
import com.example.foodieapp.database.ReservationEntry
import com.example.foodieapp.databinding.FavoriteRowBinding
import com.example.foodieapp.databinding.ReservationListRowBinding

class FavoriteAdapter(private  val FavoriteList: List<FavoriteEntry>):RecyclerView.Adapter<FavoriteAdapter.ViewHolder> (){
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val name = itemView.findViewById<TextView>(R.id.tvRecResturantName)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.favorite_row, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.name.text = FavoriteList.get(position).restaurantName

    }

    override fun getItemCount(): Int {
       return FavoriteList.size
    }
}
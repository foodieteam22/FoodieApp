package com.example.foodieapp.viewadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodieapp.R
import com.example.foodieapp.database.CommentEntry
import java.text.SimpleDateFormat
import java.util.*

class CommentsAdapter(private val commentsData: List<CommentEntry>): RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val nameTextView = itemView.findViewById<TextView>(R.id.tvCommentAuthor)
        val rateTextView = itemView.findViewById<TextView>(R.id.tvCommentRating)
        val descTextView = itemView.findViewById<TextView>(R.id.tvCommentDesc)
        val dateTextView = itemView.findViewById<TextView>(R.id.tvCommentDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.comment_row_layout, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)

    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get the data model based on position
        val contact: CommentEntry = commentsData.get(position)
        // Set item views based on your views and data model
        val name = viewHolder.nameTextView
        name.setText(contact.author)
        val rate = viewHolder.rateTextView
        rate.setText(String.format("%.1f",contact.rating).plus("/5"))
        val desc = viewHolder.descTextView
        desc.setText(contact.description)

        val date = viewHolder.dateTextView
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val netDate = Date( contact.timestamp* 1000)
        date.setText(sdf.format(netDate))

    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return commentsData.size
    }
}
package com.example.foodieapp.viewadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodieapp.databinding.MenuRowLayoutBinding
import com.example.foodieapp.model.MenuModel

public class MenuAdapter(
    val context: Context?,
    private val menuData: List<MenuModel>,
) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MenuRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    inner class ViewHolder(val binding: MenuRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(menuModel: MenuModel) {
            binding.menuModel = menuModel
            binding.executePendingBindings()
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = menuData.get(position)
        holder.binding.menuItemRecyclerView.layoutManager= LinearLayoutManager(context)
        holder.binding.menuItemRecyclerView.adapter= MenuItemAdapter(current.items)


        holder.bind(current)

    }

    override fun getItemCount(): Int {
        return menuData.count()
    }
}
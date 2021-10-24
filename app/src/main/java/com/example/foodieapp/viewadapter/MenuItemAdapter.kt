package com.example.foodieapp.viewadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodieapp.databinding.MenuRowItemLayoutBinding
import com.example.foodieapp.model.MenuItemModel

public class MenuItemAdapter(private val menuItemData: List<MenuItemModel>) :
    RecyclerView.Adapter<MenuItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MenuRowItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    inner class ViewHolder(val binding: MenuRowItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(menuItemModel: MenuItemModel) {
            binding.menuItemModel = menuItemModel
            binding.executePendingBindings()
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = menuItemData.get(position)
        holder.bind(current)
    }

    override fun getItemCount(): Int {
        return menuItemData.count()
    }
}
package com.example.foodieapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.foodieapp.R
import com.example.foodieapp.database.CommentEntry
import com.example.foodieapp.database.RestaurantFeatureEntry
import com.example.foodieapp.databinding.FragmentLoginBinding
import com.example.foodieapp.databinding.FragmentRatingBinding
import com.example.foodieapp.databinding.FragmentRestaurantDetailBinding
import com.example.foodieapp.viewadapter.CommentsAdapter
import com.example.foodieapp.viewadapter.RestaurantFeatureEntryAdapter
import com.example.foodieapp.viewmodel.RestaurantDetailViewModel


class RestaurantDetailFragment : Fragment() {

    private val binding get() = _binding!!
    private var _binding: FragmentRestaurantDetailBinding? = null
    private val viewModel: RestaurantDetailViewModel by viewModels()
    private lateinit var adapter: RestaurantFeatureEntryAdapter




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRestaurantDetailBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        insertRestaurantFeature(1,true,"Oyun Parkı")


        viewModel.getRestFetureById(1).observe(viewLifecycleOwner) {
            adapter= RestaurantFeatureEntryAdapter(it)
        }
        binding.apply {

            binding.restaurantFeatureRecyclerView.adapter = adapter

        }

        return binding.root;

    }

    private fun insertRestaurantFeature(restaurantId: Int,hasFeature:Boolean,description:String, ) {
        val restaurantFeatureEntry = RestaurantFeatureEntry(
            0,restaurantId,hasFeature,description)
        viewModel.insertRestFeature(restaurantFeatureEntry)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}
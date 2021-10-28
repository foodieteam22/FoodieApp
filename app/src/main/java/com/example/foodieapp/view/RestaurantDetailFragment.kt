package com.example.foodieapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.fragment.findNavController
import com.example.foodieapp.R
import com.example.foodieapp.database.CommentEntry
import com.example.foodieapp.database.RestaurantFeatureEntry
import com.example.foodieapp.databinding.FragmentCommentsBinding
import com.example.foodieapp.databinding.FragmentLoginBinding
import com.example.foodieapp.databinding.FragmentRatingBinding
import com.example.foodieapp.databinding.FragmentRestaurantDetailBinding
import com.example.foodieapp.viewadapter.CommentsAdapter
import com.example.foodieapp.viewadapter.RestaurantFeatureEntryAdapter
import com.example.foodieapp.viewmodel.LoginViewModel
import com.example.foodieapp.viewmodel.RestaurantDetailViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RestaurantDetailFragment : Fragment() {

    //private val binding get() = _binding!!
    private var _binding: FragmentRestaurantDetailBinding? = null
    private val viewModel: RestaurantDetailViewModel by viewModels()
    private lateinit var adapter: RestaurantFeatureEntryAdapter
    private lateinit var featureList: ArrayList<RestaurantFeatureEntry>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentRestaurantDetailBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        featureList = arrayListOf<RestaurantFeatureEntry>()
        featureList.add(RestaurantFeatureEntry(
            0,1,false,"Oyun Parkı"))
        featureList.add(RestaurantFeatureEntry(
            0,1,true,"Oyun Parkı1"))
        featureList.add(RestaurantFeatureEntry(
            0,1,true,"Oyun Parkı2"))
        featureList.add(RestaurantFeatureEntry(
            0,1,true,"Oyun Parkı3"))
        featureList.add(RestaurantFeatureEntry(
            0,1,true,"Oyun Parkı4"))
        featureList.add(RestaurantFeatureEntry(
            0,1,true,"Oyun Parkı4"))

        featureList.add(RestaurantFeatureEntry(
            0,1,false,"Oyun Parkı4"))

        featureList.add(RestaurantFeatureEntry(
            0,1,false,"Oyun Parkı4"))

        featureList.add(RestaurantFeatureEntry(
            0,1,true,"Oyun Parkı4"))
        featureList.add(RestaurantFeatureEntry(
            0,1,true,"Oyun Parkı4"))
        featureList.add(RestaurantFeatureEntry(
            0,1,true,"Oyun Parkı4"))

        adapter= RestaurantFeatureEntryAdapter(featureList)

        binding.apply {

            binding.restaurantFeatureRecyclerView.adapter = adapter

        }
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*viewModel.getRestFetureById(1).observe(viewLifecycleOwner) {
            adapter= RestaurantFeatureEntryAdapter(it)
        }*/

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
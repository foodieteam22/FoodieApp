package com.example.foodieapp.view

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.foodieapp.database.CommentEntry
import com.example.foodieapp.database.RatingEntry
import com.example.foodieapp.database.RestaurantEntry
import com.example.foodieapp.databinding.FragmentProfileBinding
import com.example.foodieapp.databinding.FragmentRatingBinding
import com.example.foodieapp.viewmodel.CommentViewModel
import com.example.foodieapp.viewmodel.RatingViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RatingFragment : Fragment() {
    private val viewModel: RatingViewModel by viewModels()
    private val viewModelComment: CommentViewModel by viewModels()
    private val binding get() = _binding!!
    private var _binding: FragmentRatingBinding? = null
    private var hygieneRate: Float =0.0F
    private var tasteRate: Float =0.0F
    private var serviceRate: Float =0.0F
    private var avgRate: Float =0.0F

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRatingBinding.inflate(layoutInflater,container,false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = RatingFragmentArgs.fromBundle(requireArguments())
        binding.tvRatingRestaurantName.setText(args.restaurantInfo.restaurantName)

        binding.ratingBarHygiene.numStars=5
        binding.ratingBarHygiene.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            hygieneRate= ratingBar.rating
            avgRate+=hygieneRate/3
        }

        binding.ratingBarService.numStars=5
        binding.ratingBarService.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            serviceRate= ratingBar.rating
            avgRate+=hygieneRate/3
        }

        binding.ratingBarTaste.numStars=5
        binding.ratingBarTaste.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            tasteRate= ratingBar.rating
            avgRate+=hygieneRate/3
        }

        binding.btnRatingOk.setOnClickListener {

            insertRating(args.restaurantInfo.id)

            insertComment(args.restaurantInfo.id, "Gamze",binding)

            //val action = RatingFragmentDirections.actionRatingFragmentToProfileFragment()
            //Navigation.findNavController(view).navigate(action)

        }
    }


    private fun insertComment(restaurantId: Int,author:String, binding: FragmentRatingBinding) {
        val commentEntry = CommentEntry(
            0,
            restaurantId,
            binding.tvRatingCommentText.text.toString(),
            author,
            System.currentTimeMillis() / 1000,
            avgRate
        )
        viewModelComment.insertComment(commentEntry)
    }

    private fun insertRating(restaurantId: Int) {
        val ratingEntry = RatingEntry(
            0,
            restaurantId,
            System.currentTimeMillis() / 1000,
            hygieneRate,
            tasteRate,
            serviceRate,
            avgRate
        )

        viewModel.insertRating(
            ratingEntry,
        )
    }


}
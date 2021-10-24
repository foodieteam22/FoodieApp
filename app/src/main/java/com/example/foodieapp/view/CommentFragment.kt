package com.example.foodieapp.view

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.foodieapp.database.CommentEntry
import com.example.foodieapp.databinding.FragmentCommentsBinding
import com.example.foodieapp.viewadapter.CommentsAdapter
import com.example.foodieapp.viewmodel.CommentViewModel


class CommentFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private val viewModel: CommentViewModel by viewModels()
    private lateinit var adapter: CommentsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentCommentsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val args = CommentFragmentArgs.fromBundle(requireArguments())

        if (args.restaurantId != 0)

            viewModel.getRestaurantComments(args.restaurantId).observe(viewLifecycleOwner) {
                adapter= CommentsAdapter(it)
            }
        else if (!TextUtils.isEmpty(args.author))
            viewModel.getCommentsByAuthor(args.author!!).observe(viewLifecycleOwner) {
                adapter= CommentsAdapter(it)
            }
        else {
            adapter= CommentsAdapter(ArrayList<CommentEntry>())
        }

        binding.apply {

            binding.commentsRecyclerView.adapter = adapter

        }
        return binding.root
    }


}
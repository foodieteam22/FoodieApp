package com.example.foodieapp.view

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Visibility
import com.example.foodieapp.databinding.FragmentCommentsBinding
import com.example.foodieapp.viewadapter.CommentsAdapter
import com.example.foodieapp.viewmodel.CommentViewModel
import java.util.*


class CommentFragment : Fragment() {
    private val viewModel: CommentViewModel by viewModels()
    private lateinit var adapter: CommentsAdapter
    private val binding get() = _binding!!
    private var _binding: FragmentCommentsBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCommentsBinding.inflate(inflater)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        setViewVisibility(View.INVISIBLE,View.INVISIBLE)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            readCommentsFromDb()
        }
    }

    private fun readCommentsFromDb() {
        val args = CommentFragmentArgs.fromBundle(requireArguments())
        if (args.restaurantId != 0)

            viewModel.getRestaurantComments(args.restaurantId).observe(viewLifecycleOwner) {

                bindRecyclerView(CommentsAdapter(it))
            }
        else if (!TextUtils.isEmpty(args.author))
            viewModel.getCommentsByAuthor(args.author!!).observe(viewLifecycleOwner) {

                bindRecyclerView(CommentsAdapter(it))
            }
        else {
            /*viewModel.getAllComments().observe(viewLifecycleOwner){

                bindRecyclerView(CommentsAdapter(it))

            }*/
            adapter= CommentsAdapter(emptyList())
        }


    }

    private fun setViewVisibility(tvVisibility: Int,recycleVisibility: Int ){

            binding.tvCommentNotFound.visibility = tvVisibility
            binding.commentsRecyclerView.visibility = recycleVisibility
    }

    private fun bindRecyclerView(adapter: CommentsAdapter) {
        binding.commentsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.commentsRecyclerView.adapter = adapter
        if(adapter.itemCount>0)
            setViewVisibility(View.INVISIBLE,View.VISIBLE)
        else
            setViewVisibility(View.VISIBLE,View.INVISIBLE)

    }

}
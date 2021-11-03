package com.example.foodieapp.view

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodieapp.R
import com.example.foodieapp.databinding.FragmentCommentsBinding
import com.example.foodieapp.viewadapter.CommentsAdapter
import com.example.foodieapp.viewmodel.CommentViewModel


class CommentFragment : Fragment() {
    private val viewModel: CommentViewModel by viewModels()
    private lateinit var adapter: CommentsAdapter
    private val binding get() = _binding!!
    private var _binding: FragmentCommentsBinding? = null
    private lateinit var args: CommentFragmentArgs


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        args = CommentFragmentArgs.fromBundle(requireArguments())
        _binding = FragmentCommentsBinding.inflate(inflater)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        setViewVisibility(View.INVISIBLE,View.INVISIBLE)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        readCommentsFromDb()

        binding.bottomNavigationMenu.setOnNavigationItemSelectedListener {
            if (it.itemId== R.id.home){
                val action = CommentFragmentDirections.actionCommentFragmentToRestaurantFragment(args.user)
                Navigation.findNavController(view).navigate(action)

            }
            if (it.itemId== R.id.profile){
                val action = CommentFragmentDirections.actionCommentFragmentToProfileFragment(args.user)
                Navigation.findNavController(view).navigate(action)

            }
            if (it.itemId== R.id.booking){
                val action = CommentFragmentDirections.actionCommentFragmentToListReservationFragment(args.user)
                Navigation.findNavController(view).navigate(action)

            }
            true


        }
    }

    private fun readCommentsFromDb() {
        args = CommentFragmentArgs.fromBundle(requireArguments())
        if (args.restaurantId != 0)

            viewModel.getRestaurantComments(args.restaurantId).observe(viewLifecycleOwner) {
                bindRecyclerView(CommentsAdapter(it))
            }
        else if (!TextUtils.isEmpty(args.user.email))
            viewModel.getCommentsByAuthor(args.user.email!!).observe(viewLifecycleOwner) {
                bindRecyclerView(CommentsAdapter(it))
            }
        else {
            viewModel.getAllComments().observe(viewLifecycleOwner){
                //adapter=
                bindRecyclerView(CommentsAdapter(it))

            }
        }

    }

    private fun bindRecyclerView(adapter: CommentsAdapter) {
        binding.commentsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.commentsRecyclerView.adapter = adapter
        if(adapter.itemCount>0)
            setViewVisibility(View.INVISIBLE,View.VISIBLE)
        else
            setViewVisibility(View.VISIBLE,View.INVISIBLE)
    }

    private fun setViewVisibility(tvVisibility: Int,recycleVisibility: Int ){

        binding.tvCommentNotFound.visibility = tvVisibility
        binding.commentsRecyclerView.visibility = recycleVisibility
    }
}
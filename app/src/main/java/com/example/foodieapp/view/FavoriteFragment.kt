package com.example.foodieapp.view

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodieapp.R
import com.example.foodieapp.database.FavoriteEntry
import com.example.foodieapp.database.ReservationEntry
import com.example.foodieapp.databinding.FragmentFavoriteBinding
import com.example.foodieapp.databinding.FragmentProfileBinding
import com.example.foodieapp.viewadapter.CommentsAdapter
import com.example.foodieapp.viewadapter.FavoriteAdapter
import com.example.foodieapp.viewadapter.ReservationAdapter
import com.example.foodieapp.viewmodel.FavoriteViewModel
import com.example.foodieapp.viewmodel.ProfileViewModel


class FavoriteFragment : Fragment() {
    private val viewModel: FavoriteViewModel by viewModels()
    private val binding get() = _binding!!
    private var _binding: FragmentFavoriteBinding? = null
    private lateinit var args: FavoriteFragmentArgs
    private  lateinit var favoriteArrayList : ArrayList<FavoriteEntry>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        favoriteArrayList = ArrayList<FavoriteEntry>()
        args = FavoriteFragmentArgs.fromBundle(requireArguments())
        _binding = FragmentFavoriteBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFavoriteByEmail(args.user.email).observe(viewLifecycleOwner){

            if (!it.isEmpty()) {
                binding.tvrec.visibility = View.GONE
                bindRecyclerView(FavoriteAdapter(it))

            }
            else{
                binding.tvrec.visibility = View.VISIBLE

            }



        }
        binding.bottomNavigationMenu.setOnNavigationItemSelectedListener {
            if (it.itemId==R.id.home){
                val action = FavoriteFragmentDirections.actionFavoriteFragmentToRestaurantFragment(args.user)
                Navigation.findNavController(view).navigate(action)

            }
            if (it.itemId==R.id.booking){
                val action = FavoriteFragmentDirections.actionFavoriteFragmentToListReservationFragment(args.user)
                Navigation.findNavController(view).navigate(action)

            }
            if (it.itemId==R.id.profile){
                val action = FavoriteFragmentDirections.actionFavoriteFragmentToProfileFragment(args.user)
                Navigation.findNavController(view).navigate(action)

            }
            true


        }
    }
    private fun bindRecyclerView(adapter: FavoriteAdapter) {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
    }




}
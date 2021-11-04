package com.example.foodieapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodieapp.R
import com.example.foodieapp.database.FavoriteEntry
import com.example.foodieapp.database.ReservationEntry
import com.example.foodieapp.databinding.FragmentFavoriteBinding
import com.example.foodieapp.databinding.FragmentProfileBinding
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

            if (!it.isEmpty()){
                binding.tvrec.visibility=View.GONE
                favoriteArrayList.clear()

                for (res in it){
                    favoriteArrayList.add(res)
                }



                bindRecyclerView(FavoriteAdapter(favoriteArrayList))

            }



        }
    }
    private fun bindRecyclerView(adapter: FavoriteAdapter) {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
    }


}
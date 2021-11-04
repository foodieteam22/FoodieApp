package com.example.foodieapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodieapp.R
import com.example.foodieapp.database.ReservationEntry
import com.example.foodieapp.database.UserEntry
import com.example.foodieapp.databinding.FragmentListReservationBinding
import com.example.foodieapp.databinding.FragmentProfileBinding
import com.example.foodieapp.viewadapter.CommentsAdapter
import com.example.foodieapp.viewadapter.ReservationAdapter
import com.example.foodieapp.viewadapter.ResturantAdapter
import com.example.foodieapp.viewmodel.ProfileViewModel
import com.example.foodieapp.viewmodel.ReservationsViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


class ListReservationFragment : Fragment() {

    private val viewModel: ReservationsViewModel by viewModels()
    private  lateinit var reservationArrayList : List<ReservationEntry>
    private lateinit var reservationEntry: ReservationEntry
    private lateinit var reservationAdapter: ReservationAdapter
    private lateinit var auth: FirebaseAuth
    private val binding get() = _binding!!
    private var _binding: FragmentListReservationBinding? = null
    private lateinit var args: ListReservationFragmentArgs
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        reservationArrayList = ArrayList<ReservationEntry>()
        // Inflate the layout for this fragment
         args = ListReservationFragmentArgs.fromBundle(requireArguments())
        _binding = FragmentListReservationBinding.inflate(layoutInflater,container,false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getResByEmail(args.user.email).observe(viewLifecycleOwner){

            if (!it.isEmpty()){
                binding.tvrec.visibility=View.GONE
                bindRecyclerView(ReservationAdapter(it))

            }
            else
            {
                binding.tvrec.visibility=View.VISIBLE

            }



        }
        binding.bottomNavigationMenu.setOnNavigationItemSelectedListener {
            if (it.itemId==R.id.home){
                val action = ListReservationFragmentDirections.actionListReservationFragmentToRestaurantFragment(args.user)
                Navigation.findNavController(view).navigate(action)

            }
            if (it.itemId==R.id.profile){
                val action = ListReservationFragmentDirections.actionListReservationFragmentToProfileFragment(args.user)
                Navigation.findNavController(view).navigate(action)
            }
            true


        }



       // binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        //reservationAdapter = ReservationAdapter(ReservationArrayList)
       // binding.recyclerView.adapter = reservationAdapter
    }
    private fun bindRecyclerView(adapter: ReservationAdapter) {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
    }


}



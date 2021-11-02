package com.example.foodieapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodieapp.R
import com.example.foodieapp.database.ReservationEntry
import com.example.foodieapp.database.UserEntry
import com.example.foodieapp.databinding.FragmentListReservationBinding
import com.example.foodieapp.databinding.FragmentProfileBinding
import com.example.foodieapp.viewadapter.ReservationAdapter
import com.example.foodieapp.viewadapter.ResturantAdapter
import com.example.foodieapp.viewmodel.ProfileViewModel
import com.example.foodieapp.viewmodel.ReservationsViewModel
import com.google.firebase.auth.FirebaseAuth


class ListReservationFragment : Fragment() {

    private val viewModel: ReservationsViewModel by viewModels()
    private  lateinit var ReservationArrayList : ArrayList<ReservationEntry>
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
        // Inflate the layout for this fragment
         args = ListReservationFragmentArgs.fromBundle(requireArguments())
        _binding = FragmentListReservationBinding.inflate(layoutInflater,container,false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getResByEmail(args.user.email).observe(viewLifecycleOwner){

            for (reservation in it)
            {
                val id = reservation.id
                val resName = reservation.restaurantName
                val email = reservation.email
                val desk = reservation.deskNo

                val ReservationEntry = ReservationEntry(id,resName,email,desk)
                ReservationArrayList.add(ReservationEntry)


            }



        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        reservationAdapter = ReservationAdapter(ReservationArrayList)
        binding.recyclerView.adapter = reservationAdapter
    }



}



package com.example.foodieapp.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodieapp.R
import com.example.foodieapp.viewmodel.ReservationsViewModel

class ReservationsFragment : Fragment() {

    companion object {
        fun newInstance() = ReservationsFragment()
    }

    private lateinit var viewModel: ReservationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reservations, container, false)
    }
}
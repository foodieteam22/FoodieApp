package com.example.foodieapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.foodieapp.R
import com.example.foodieapp.database.ReservationEntry
import com.example.foodieapp.database.RestaurantFeatureEntry
import com.example.foodieapp.database.UserEntry
import com.example.foodieapp.databinding.FragmentProfileBinding
import com.example.foodieapp.databinding.FragmentReservationsBinding
import com.example.foodieapp.viewmodel.ReservationsViewModel

class ReservationsFragment : Fragment(), AdapterView.OnItemSelectedListener {

    companion object {
        fun newInstance() = ReservationsFragment()
    }

    private lateinit var viewModel: ReservationsViewModel
    private val binding get() = _binding!!
    private var _binding: FragmentReservationsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReservationsBinding.inflate(layoutInflater,container,false)
        val view = binding.root

        val desks = arrayOf("Masa Seç","1","2","3")
        val personCount = arrayOf("Kişi Sayısı","1","2","3","4")
        val hours = arrayOfNulls<String>(24)

        val arrayAdapterDesks =
            context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item,desks) }
        val arrayAdapterPersonCount =
            context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item,personCount) }
        val arrayAdapterHours =
            context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item,hours) }

        val spinnerDesk = view.findViewById<Spinner>(R.id.spinnerDesk)
        val spinnerPersonCount = view.findViewById<Spinner>(R.id.spinnerPersonCount)
        val spinnerHour = view.findViewById<Spinner>(R.id.spinnerHour);

        for (i in 0..23) {
            hours[i] = Integer.toString(i).plus(":00").plus(" - ").plus(Integer.toString(i+1).plus(":00"))
        }


        spinnerDesk.adapter = arrayAdapterDesks
        spinnerDesk.onItemSelectedListener = this
        spinnerPersonCount.adapter = arrayAdapterPersonCount
        spinnerPersonCount.onItemSelectedListener = this
        spinnerHour.adapter = arrayAdapterHours
        spinnerHour.onItemSelectedListener = this

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnShowDesk.setOnClickListener {
            val action = ReservationsFragmentDirections.actionReservationsFragmentToTableSchemeFragment("https://www.gstatic.com/webp/gallery/1.jpg")
            Navigation.findNavController(view).navigate(action)
        }

        binding.btnShowMenu.setOnClickListener {
            val action = ReservationsFragmentDirections.actionReservationsFragmentToMenuFragment(1)
            Navigation.findNavController(view).navigate(action)
        }
    }

    fun complete(view: View){

    }

    private fun insertReservation(Id: Int,restaurantName:String,desks:String, ) {
        val reservationEntry = ReservationEntry(
            1,"İzmir Sakız Restoran","5")
        viewModel.insertReservation(reservationEntry)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
     
    }
}
package com.example.foodieapp.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.Navigation
import com.example.foodieapp.R
import com.example.foodieapp.utils.downloadImage
import com.example.foodieapp.utils.makePlaceholder
import com.example.foodieapp.viewmodel.LoginViewModel
import com.example.foodieapp.viewmodel.ReservationsViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ReservationsFragment : Fragment(), AdapterView.OnItemSelectedListener {

    companion object {
        fun newInstance() = ReservationsFragment()
    }

    private lateinit var viewModel: ReservationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reservations,container,false)

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

    fun complete(view: View){

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}
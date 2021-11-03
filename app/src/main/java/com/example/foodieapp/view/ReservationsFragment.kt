package com.example.foodieapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.foodieapp.R
import com.example.foodieapp.database.ReservationEntry
import com.example.foodieapp.database.RestaurantFeatureEntry
import com.example.foodieapp.database.UserEntry
import com.example.foodieapp.databinding.FragmentProfileBinding
import com.example.foodieapp.databinding.FragmentReservationsBinding
import com.example.foodieapp.viewmodel.ProfileViewModel
import com.example.foodieapp.viewmodel.ReservationsViewModel

class ReservationsFragment : Fragment(), AdapterView.OnItemSelectedListener {

    companion object {
        fun newInstance() = ReservationsFragment()
    }

    private val viewModel: ReservationsViewModel by viewModels()
    private val binding get() = _binding!!
    private var _binding: FragmentReservationsBinding? = null
    private lateinit var args: ReservationsFragmentArgs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        args = ReservationsFragmentArgs.fromBundle(requireArguments())
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
        binding.bottomNavigationMenu.setOnNavigationItemSelectedListener {
            if (it.itemId== R.id.profile){
                val action = ReservationsFragmentDirections.actionReservationsFragmentToProfileFragment(args.user)
                Navigation.findNavController(view).navigate(action)

            }
            if (it.itemId== R.id.booking){
                val action = ReservationsFragmentDirections.actionReservationsFragmentToListReservationFragment(args.user)
                Navigation.findNavController(view).navigate(action)

            }
            if (it.itemId== R.id.home){
                val action = ReservationsFragmentDirections.actionReservationsFragmentToRestaurantFragment(args.user)
                Navigation.findNavController(view).navigate(action)

            }
            true


        }
        binding.btnComplete.setOnClickListener {
            val restaurantName = args.restaurant.name
            val email= args.user.email
            val deskNo= binding.spinnerDesk.selectedItem.toString()
            val personCount = binding.spinnerPersonCount.selectedItem.toString()
            val date = binding.spinnerHour.selectedItem.toString()
            val reservationEntry = ReservationEntry(
                0,restaurantName,email,deskNo,personCount,date)
           viewModel.insertReservation(reservationEntry)
            Toast.makeText(requireContext(), "OK", Toast.LENGTH_SHORT).show()

            val action= ReservationsFragmentDirections.actionReservationsFragmentToListReservationFragment(args.user)
            Navigation.findNavController(view).navigate(action)
        }
        binding.etRestaurantName.text=args.restaurant.name
        binding.progressBarHorizontal.progress=args.restaurant.ratio.toInt()
        binding.etRestaurantDensityValue.text=args.restaurant.ratio


    }

    fun complete(view: View){


    }

    private fun insertReservation( ) {
        val reservationEntry = ReservationEntry(
            7,"İzmir Sakız Restoran",args.user.email,"5")
        viewModel.insertReservation(reservationEntry)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
     
    }
}
package com.example.foodieapp.view

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodieapp.R
import com.example.foodieapp.databinding.FragmentLoginBinding
import com.example.foodieapp.databinding.FragmentRatingBinding
import com.example.foodieapp.databinding.FragmentRestaurantDetailBinding
import com.example.foodieapp.databinding.FragmentTableSchemeBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class TableSchemeFragment : Fragment() {

    private var binding: FragmentTableSchemeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment



        binding = FragmentTableSchemeBinding.inflate(layoutInflater,container,false)

        binding.apply {

        }
        return inflater.inflate(R.layout.fragment_table_scheme, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = TableSchemeFragmentArgs.fromBundle(requireArguments())
        binding?.imgTableScheme?.setImageURI(Uri.parse(args.tableSchemeUrl))
        binding.apply {

        }

    }


}
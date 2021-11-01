package com.example.foodieapp.view

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodieapp.R
import com.example.foodieapp.databinding.*
import com.example.foodieapp.utils.downloadImage
import com.example.foodieapp.utils.makePlaceholder
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class TableSchemeFragment : Fragment() {

    private val binding get() = _binding!!
    private var _binding: FragmentTableSchemeBinding? = null
    //private var binding: FragmentTableSchemeBinding? = null
    private lateinit var args: TableSchemeFragmentArgs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        args = TableSchemeFragmentArgs.fromBundle(requireArguments())
        _binding = FragmentTableSchemeBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        return view

       // binding = FragmentTableSchemeBinding.inflate(layoutInflater,container,false)

      //  binding.apply {

      //  }
      //  return inflater.inflate(R.layout.fragment_table_scheme, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.imgTableScheme?.downloadImage(args.tableSchemeUrl, makePlaceholder(requireContext()))


    }


}
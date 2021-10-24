package com.example.foodieapp.view

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentResultOwner
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.foodieapp.R
import com.example.foodieapp.databinding.FragmentLoginBinding
import com.example.foodieapp.databinding.FragmentResetPasswordBinding
import com.example.foodieapp.viewmodel.LoginViewModel
import com.example.foodieapp.viewmodel.ProfileViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ResetPasswordFragment : Fragment() {
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var auth: FirebaseAuth
    private val binding get() = _binding!!
    private var _binding: FragmentResetPasswordBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentResetPasswordBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let{

            val info = ResetPasswordFragmentArgs.fromBundle(it).info

            if (info=="reset"){
                binding.etEmailAddress.visibility= View.VISIBLE
                binding.btnResetPassword.visibility = View.VISIBLE
                binding.tvForgotPassword.visibility = View.VISIBLE
                binding.etnewPassword.visibility = View.GONE
                binding.btnUpdatePassword.visibility = View.GONE


            }
            if(info =="changePassword"){
                binding.etEmailAddress.visibility= View.GONE
                binding.btnResetPassword.visibility = View.GONE
                binding.tvForgotPassword.text = "CHANGE PASSWORD?"
                binding.etnewPassword.visibility = View.VISIBLE
                binding.btnUpdatePassword.visibility = View.VISIBLE

            }


        }

        binding.btnResetPassword.setOnClickListener {
            val email =binding.etEmailAddress.text.toString()
            if(email.equals("")) {
                Toast.makeText(requireContext(), "Enter email!", Toast.LENGTH_LONG).show()
            }
            else{
                Firebase.auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d(ContentValues.TAG, "Email sent.")
                            Toast.makeText(requireContext(),"Email sent.", Toast.LENGTH_SHORT).show()
                            val action = ResetPasswordFragmentDirections.actionResetPasswordFragmentToLoginFragment()
                            Navigation.findNavController(view).navigate(action)
                        }
                    }.addOnFailureListener {
                        Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_SHORT).show()
                    }

            }

        }

        binding.btnUpdatePassword.setOnClickListener {
            val newPassword =binding.etnewPassword.text.toString()

            val user = Firebase.auth.currentUser

            if(newPassword.equals("")) {
                Toast.makeText(requireContext(), "Enter password!", Toast.LENGTH_LONG).show()
            }
            else
            {
                user!!.updatePassword(newPassword)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(requireContext(), "User password updated.", Toast.LENGTH_SHORT).show()
                            viewModel.getUserByEmail(user.email.toString()).observe(viewLifecycleOwner){
                                val action = ResetPasswordFragmentDirections.actionResetPasswordFragmentToProfileFragment(it[0])
                                Navigation.findNavController(view).navigate(action)
                            }

                        }
                    }.addOnFailureListener {
                        Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_SHORT).show()
                    }


            }

        }

    }

}
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
import androidx.navigation.Navigation
import com.example.foodieapp.R
import com.example.foodieapp.databinding.FragmentLoginBinding
import com.example.foodieapp.databinding.FragmentResetPasswordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ResetPasswordFragment : Fragment() {
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
                binding.btnUpdateEmail.visibility = View.GONE
                binding.etnewPassword.visibility = View.GONE
                binding.btnUpdatePassword.visibility = View.GONE


            }
            if(info =="changePassword"){
                binding.etEmailAddress.visibility= View.GONE
                binding.btnResetPassword.visibility = View.GONE
                binding.tvForgotPassword.visibility = View.GONE
                binding.btnUpdateEmail.visibility = View.GONE
                binding.etnewPassword.visibility = View.VISIBLE
                binding.btnUpdatePassword.visibility = View.VISIBLE

            }
            if (info=="changeEmail"){
                binding.etEmailAddress.visibility= View.VISIBLE
                binding.btnResetPassword.visibility = View.GONE
                binding.tvForgotPassword.visibility = View.GONE
                binding.btnUpdateEmail.visibility = View.VISIBLE
                binding.etnewPassword.visibility = View.GONE
                binding.btnUpdatePassword.visibility = View.GONE

            }

        }

        binding.btnResetPassword.setOnClickListener {
            val email =binding.etEmailAddress.text.toString()
            Firebase.auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(ContentValues.TAG, "Email sent.")
                        Toast.makeText(requireContext(),"Email sent.", Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener {
                    Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_SHORT).show()
                }

            val action = ResetPasswordFragmentDirections.actionResetPasswordFragmentToLoginFragment()
            Navigation.findNavController(view).navigate(action)
        }
    }

}
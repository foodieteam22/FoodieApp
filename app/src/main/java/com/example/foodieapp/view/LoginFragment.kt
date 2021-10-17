package com.example.foodieapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.foodieapp.R
import com.example.foodieapp.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private val binding get() = _binding!!
    private var _binding: FragmentLoginBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        val currentUser = auth.currentUser

       if(currentUser != null){
           val action = LoginFragmentDirections.actionLoginFragmentToProfileFragment()
           Navigation.findNavController(view).navigate(action)
       }
        binding.btnSignIn.setOnClickListener { signIn(view)}
        binding.btnSignUp.setOnClickListener { signUp(view)}



        binding.btnForgotPassword.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToResetPasswordFragment("reset")
            Navigation.findNavController(view).navigate(action)
        }
    }
    fun signIn(view: View){
        val email =  binding.etEmailAddress.text.toString()
        val password = binding.etPassword.text.toString()
        if(email.equals("") || password.equals("")){
            Toast.makeText(requireContext(),"Enter email and password!", Toast.LENGTH_LONG).show()
        }else{
            auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
                val action = LoginFragmentDirections.actionLoginFragmentToProfileFragment()
                Navigation.findNavController(view).navigate(action)
            }.addOnFailureListener {
                Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun signUp(view: View){
        val email =  binding.etEmailAddress.text.toString()
        val password = binding.etPassword.text.toString()
        if(email.equals("") || password.equals("")){
            Toast.makeText(requireContext(),"Enter email and password!", Toast.LENGTH_LONG).show()
        }else{
            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
                val action = LoginFragmentDirections.actionLoginFragmentToProfileFragment()
                Navigation.findNavController(view).navigate(action)
            }.addOnFailureListener {
                Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }

    }





}
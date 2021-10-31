package com.example.foodieapp.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.foodieapp.R
import com.example.foodieapp.database.UserEntry
import com.example.foodieapp.databinding.FragmentLoginBinding
import com.example.foodieapp.viewmodel.CommentViewModel
import com.example.foodieapp.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage


class LoginFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private val binding get() = _binding!!
    private var _binding: FragmentLoginBinding? = null
    private lateinit var viewModel: LoginViewModel
    private lateinit var storage: FirebaseStorage
    private  var userId: Int =0
    private lateinit var user: UserEntry



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

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

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        auth = Firebase.auth

        val currentUser = auth.currentUser

       if(currentUser != null){


           viewModel.getUserByEmail(currentUser.email.toString()).observe(viewLifecycleOwner){
               user=it[0]
               val action = LoginFragmentDirections.actionLoginFragmentToRestaurantFragment(user)
               Navigation.findNavController(view).navigate(action)


           }


       }
        binding.btnSignIn.setOnClickListener {
            signIn(view)

        }
        binding.btnSignUp.setOnClickListener {
            signUp(view)


        }



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

                viewModel.getUserByEmail(email).observe(viewLifecycleOwner) {
                    if(it.size!=0){
                        user = it[0]
                        val action = LoginFragmentDirections.actionLoginFragmentToRestaurantFragment(user)
                        Navigation.findNavController(view).navigate(action)


                    }
                    else{
                        saveUser()
                    }

                }
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
                saveUser()

            }.addOnFailureListener {
                Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }

    }

   private fun saveUser(){
        storage = Firebase.storage
        auth = Firebase.auth
        val uploadPictureReference= storage.reference.child("images").child("defaultUserPhoto.png")
        uploadPictureReference.downloadUrl.addOnSuccessListener {
            val downloadUrl = it.toString()
            val email = auth.currentUser?.email.toString()
            val user = UserEntry(0,email,downloadUrl)
            viewModel.insertUser(user)
            val action = LoginFragmentDirections.actionLoginFragmentToRestaurantFragment(user)
            Navigation.findNavController(requireActivity(), R.id.fragmentContainerView).navigate(action)




        }.addOnFailureListener {
            Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_LONG).show()
        }


    }





}
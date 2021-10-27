package com.example.foodieapp.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.foodieapp.R
import com.example.foodieapp.databinding.FragmentHelpBinding
import com.example.foodieapp.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class HelpFragment : Fragment() {
    private val binding get() = _binding!!
    private var _binding: FragmentHelpBinding? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHelpBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recipientEt.hint= "foodieteam2022@gmail.com"
        auth = Firebase.auth
        val currentUser = auth.currentUser

        binding.sendEmailBtn.setOnClickListener {
            val recipient = "foodieteam2022@gmail.com"
            val subject= binding.subjectEt.text.toString()
            val message = binding.messageEt.text.toString()
            sendEmail(view,recipient,subject,message) }
    }

    private fun sendEmail(view: View, recipient: String, subject: String, message: String){

        val mIntent = Intent(Intent.ACTION_SEND)
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"
        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        mIntent.putExtra(Intent.EXTRA_TEXT, message)
        try {

            startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
        }
        catch (e: Exception){

            Toast.makeText(requireActivity(), e.message, Toast.LENGTH_LONG).show()
        }

    }


}
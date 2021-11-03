package com.example.foodieapp.view

import android.app.FragmentManager
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.foodieapp.R
import com.example.foodieapp.database.UserEntry
import com.example.foodieapp.databinding.FragmentProfileBinding
import com.example.foodieapp.utils.downloadImage
import com.example.foodieapp.utils.makePlaceholder
import com.example.foodieapp.viewmodel.ProfileViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage


class ProfileFragment : Fragment() {
    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var storage: FirebaseStorage
    private lateinit var auth: FirebaseAuth
    private val binding get() = _binding!!
    private var _binding: FragmentProfileBinding? = null
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private  lateinit var permissonLauncher : ActivityResultLauncher<String>
    var selectedPicture : Uri? = null
    private lateinit var user: UserEntry
    private lateinit var args: ProfileFragmentArgs



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       // val actionBar = ActionBar.DISPLAY_SHOW_TITLE
        args = ProfileFragmentArgs.fromBundle(requireArguments())
        _binding = FragmentProfileBinding.inflate(layoutInflater,container,false)
        val view = binding.root

        return view

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.profile_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId==R.id.logout) {
            auth=Firebase.auth
            auth.signOut()
            val action = ProfileFragmentDirections.actionProfileFragmentToLoginFragment()
            Navigation.findNavController(requireActivity(),R.id.fragmentContainerView).navigate(action)

        }


        return super.onOptionsItemSelected(item)
    }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         binding.tvUserName.text = args.user.email
         binding.imgProfile.downloadImage(args.user.downloadUrl, makePlaceholder(requireContext()))
         registerLauncher()




        binding.btnchangePassword.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToResetPasswordFragment("changePassword")
            Navigation.findNavController(view).navigate(action)
        }
        binding.btnLogout.setOnClickListener {
            auth = Firebase.auth
            auth.signOut()

            val action = ProfileFragmentDirections.actionProfileFragmentToLoginFragment()
            Navigation.findNavController(view).navigate(action)
        }
       binding.imgProfile.setOnClickListener {
           selectImage(view)






       }



        binding.btnRating.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToCommentFragment(0, args.user)
            Navigation.findNavController(view).navigate(action)

        }
       binding.btnHelp.setOnClickListener {
           val action = ProfileFragmentDirections.actionProfileFragmentToHelpFragment()
           Navigation.findNavController(view).navigate(action)
       }

       binding.bottomNavigationMenu.setOnNavigationItemSelectedListener {
           if (it.itemId==R.id.home){
               val action = ProfileFragmentDirections.actionProfileFragmentToRestaurantFragment(args.user)
               Navigation.findNavController(view).navigate(action)

           }
           if (it.itemId==R.id.booking){
               val action = ProfileFragmentDirections.actionProfileFragmentToListReservationFragment(args.user)
               Navigation.findNavController(view).navigate(action)

           }
           true


       }


    }
    fun selectImage(view: View){
        if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),android.Manifest.permission.READ_EXTERNAL_STORAGE)){
                Snackbar.make(view, "Permission needed for gallery", Snackbar.LENGTH_INDEFINITE).setAction("Give Permission") {
                    permissonLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                }.show()
            }else{

                permissonLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)

            }

        } else{
            val intentToGallery= Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            activityResultLauncher.launch(intentToGallery)



        }





    }

    private fun registerLauncher() {
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
            if (result.resultCode == AppCompatActivity.RESULT_OK){
                val intentFromResult = result.data
                if(intentFromResult != null){
                    selectedPicture = intentFromResult.data
                    selectedPicture?.let {
                        binding.imgProfile.setImageURI(it)
                        updateUser()
                        }
                    }
                }
        }

        permissonLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){ result->
            if(result){
                val intentToGallery= Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)

            }else{
                Toast.makeText(requireContext(), "Permisson needed!", Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun updateUser(){
        if (selectedPicture!=null){
            storage = Firebase.storage
            auth = Firebase.auth
            val reference = storage.reference
            val imageReference = reference.child("images").child(auth.currentUser?.uid.toString())
            imageReference.putFile(selectedPicture!!).addOnSuccessListener{
                val uploadPictureReference =
                    storage.reference.child("images").child(auth.currentUser?.uid.toString())
                uploadPictureReference.downloadUrl.addOnSuccessListener {
                    val downloadUrl = it.toString()
                        val user = UserEntry(
                            args.user.id,
                            args.user.email,
                            downloadUrl
                        )
                         viewModel.updatePhoto(user)
                         Toast.makeText(requireContext(), "güncellendi", Toast.LENGTH_SHORT).show()

                }.addOnFailureListener {
                    Toast.makeText(requireContext(), "NOOOO", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}
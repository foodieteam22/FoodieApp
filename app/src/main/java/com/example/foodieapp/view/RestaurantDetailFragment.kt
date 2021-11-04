package com.example.foodieapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodieapp.R
import com.example.foodieapp.database.*
import com.example.foodieapp.databinding.FragmentCommentsBinding
import com.example.foodieapp.databinding.FragmentLoginBinding
import com.example.foodieapp.databinding.FragmentRatingBinding
import com.example.foodieapp.databinding.FragmentRestaurantDetailBinding
import com.example.foodieapp.utils.downloadImage
import com.example.foodieapp.utils.makePlaceholder
import com.example.foodieapp.viewadapter.CommentsAdapter
import com.example.foodieapp.viewadapter.RestaurantFeatureEntryAdapter
import com.example.foodieapp.viewmodel.FavoriteViewModel
import com.example.foodieapp.viewmodel.LoginViewModel
import com.example.foodieapp.viewmodel.RestaurantDetailViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RestaurantDetailFragment : Fragment() {

    //private val binding get() = _binding!!
    private var _binding: FragmentRestaurantDetailBinding? = null
    private val viewModel: RestaurantDetailViewModel by viewModels()
    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private lateinit var adapter: RestaurantFeatureEntryAdapter
    private lateinit var featureList: ArrayList<RestaurantFeatureEntry>
    private lateinit var user: UserEntry
    private lateinit var args: RestaurantDetailFragmentArgs
    private lateinit var restaurantInfo : RestaurantEntry
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        args = RestaurantDetailFragmentArgs.fromBundle(requireArguments())

        val binding = FragmentRestaurantDetailBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        adapter= RestaurantFeatureEntryAdapter(args.restaurant.items)

        binding.apply {

            favoriteViewModel.getFavoriteByEmail(args.user.email).observe(viewLifecycleOwner){
                if (it.isEmpty()){
                    binding.addFavorite.visibility=View.VISIBLE
                    binding.deleteFavorite.visibility=View.GONE

                }
                else
                {
                    for (favorites in it){
                        if (favorites.restaurantName==args.restaurant.name){
                            binding.addFavorite.visibility=View.GONE
                            binding.deleteFavorite.visibility=View.VISIBLE
                            break
                        }
                        else{
                            binding.deleteFavorite.visibility=View.GONE
                            binding.addFavorite.visibility=View.VISIBLE
                        }

                    }

                }

            }
            binding.addFavorite.setOnClickListener {
                binding.addFavorite.visibility=View.GONE
                binding.deleteFavorite.visibility=View.VISIBLE
                val restaurantName = args.restaurant.name
                val userEmai= args.user.email
                val favoriteEntry = FavoriteEntry(0,userEmai,restaurantName)
                favoriteViewModel.insertFavorite(favoriteEntry)

            }
            binding.deleteFavorite.setOnClickListener {
                binding.addFavorite.visibility=View.VISIBLE
                binding.deleteFavorite.visibility=View.GONE
                val restaurantName = args.restaurant.name
                val userEmai= args.user.email
                favoriteViewModel.getFavoriteByEmail(args.user.email).observe(viewLifecycleOwner){
                    for(fav in it){
                        if (it.isEmpty()){
                            binding.addFavorite.visibility=View.VISIBLE
                            binding.deleteFavorite.visibility=View.GONE

                        }
                        else
                        {
                            if (fav.restaurantName==args.restaurant.name)
                            {
                                favoriteViewModel.deleteFavorite(fav)
                                val action= RestaurantDetailFragmentDirections.actionRestaurantDetailFragmentSelf(args.user,args.restaurant)
                                Navigation.findNavController(requireView()).navigate(action)

                            }

                        }

                    }

                }


            }
            binding.restaurantFeatureRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.restaurantFeatureRecyclerView.adapter = adapter
            layoutResDetailComments.setOnClickListener{
               onCommentsClick()
            }

            layoutResDetailTableScheme.setOnClickListener{
                onTableSchemeClick()
            }
            floatingActionButtonAddReservation.setOnClickListener{
                onAddReservationClick()
            }
            layoutResDetailMenu.setOnClickListener{
                onDetailMenuClick()
            }
            layoutResDetailRate.setOnClickListener{
                onDetailRateClick()
            }
            bottomNavigationMenu.setOnNavigationItemSelectedListener {
                if (it.itemId== R.id.profile){
                    val action = RestaurantDetailFragmentDirections.actionRestaurantDetailFragmentToProfileFragment(args.user)
                    Navigation.findNavController(requireView()).navigate(action)

                }
                if (it.itemId== R.id.booking){
                    val action = RestaurantDetailFragmentDirections.actionRestaurantDetailFragmentToListReservationFragment(args.user)
                    Navigation.findNavController(requireView()).navigate(action)

                }
                if (it.itemId== R.id.home){
                    val action = RestaurantDetailFragmentDirections.actionRestaurantDetailFragmentToRestaurantFragment(args.user)
                    Navigation.findNavController(requireView()).navigate(action)

                }
                true


            }
            binding.imgRestaurant.downloadImage(args.restaurant.image, makePlaceholder(requireContext()))
            binding.tvDetailRestName.text= args.restaurant.name
            binding.tvDetailRestRate.text= args.restaurant.rating
            binding.tvDeatilLocation.text = args.restaurant.county + " , " + args.restaurant.city
            binding.tvDetailWorkingHours.text = args.restaurant.workingHours

        }
        val view = binding.root
        return view

    }
    fun onCommentsClick()
    {
        val action = RestaurantDetailFragmentDirections.actionDetailFragmentToCommentFragment(args.restaurant.id,args.user)
        Navigation.findNavController(requireActivity(), R.id.fragmentContainerView).navigate(action)
    }
    fun onTableSchemeClick()
    {
        val url = "https://www.gstatic.com/webp/gallery/1.jpg"
        val action = RestaurantDetailFragmentDirections.actionDetailFragmentToTableSchemeFragment(args.restaurant.imageResource,args.user)
        Navigation.findNavController(requireActivity(), R.id.fragmentContainerView).navigate(action)
    }
    fun onDetailRateClick()
    {
        val action = RestaurantDetailFragmentDirections.actionRestaurantDetailFragmentToRatingFragment(args.restaurant,args.user)
        Navigation.findNavController(requireActivity(), R.id.fragmentContainerView).navigate(action)
    }
    fun onAddReservationClick()
    {
        //val user :UserEntry = UserEntry(1,"ss","ss")
        val action = RestaurantDetailFragmentDirections.actionDetailFragmentToReservationFragment(args.user,args.restaurant)
        Navigation.findNavController(requireActivity(), R.id.fragmentContainerView).navigate(action)
    }
    fun onDetailMenuClick()
    {
        val action = RestaurantDetailFragmentDirections.actionRestaurantDetailFragmentToMenuFragment(args.restaurant.id)
        Navigation.findNavController(requireActivity(), R.id.fragmentContainerView).navigate(action)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*viewModel.getRestFetureById(1).observe(viewLifecycleOwner) {
            adapter= RestaurantFeatureEntryAdapter(it)
        }*/


    }

    private fun insertRestaurantFeature(restaurantId: Int,hasFeature:Boolean,description:String, ) {
        val restaurantFeatureEntry = RestaurantFeatureEntry(
            0,restaurantId,hasFeature,description)
        viewModel.insertRestFeature(restaurantFeatureEntry)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}
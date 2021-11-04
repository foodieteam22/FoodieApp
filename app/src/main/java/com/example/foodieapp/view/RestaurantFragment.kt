package com.example.foodieapp.view

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodieapp.R
import com.example.foodieapp.databinding.FragmentRestaurantBinding
import com.example.foodieapp.model.RestaurantModel
import com.example.foodieapp.utils.ResturantService
import com.example.foodieapp.viewadapter.ResturantAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

class RestaurantFragment : Fragment() {
    private lateinit var adapter: ResturantAdapter
    private lateinit var restData : ArrayList<RestaurantModel>
    private lateinit var tempData: ArrayList<RestaurantModel>
    private val binding get() = _binding!!
    private var _binding: FragmentRestaurantBinding? = null
    private lateinit var args: RestaurantFragmentArgs
    private var count :Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        args = RestaurantFragmentArgs.fromBundle(requireArguments())
        _binding = FragmentRestaurantBinding.inflate(layoutInflater,container,false)
               val supportActionBar: ActionBar? = (requireActivity() as AppCompatActivity).supportActionBar
              if (supportActionBar != null) supportActionBar.hide()

        val search = binding.restaurantSearch as SearchView

        binding.tvfilter.setOnClickListener{
            restData.clear()
            if(count %2 ==0)
            {
                if(tempData!=null)
                {
                    Collections.sort(tempData, object : Comparator<RestaurantModel> {
                        override fun compare(o1: RestaurantModel?, o2: RestaurantModel?): Int = o2!!.ratio.toInt() - o1!!.ratio.toInt()
                    })
                }
                restData.addAll(tempData)
                bindRestData(restData)
            }

            else
            {
                tempData.reverse()
                restData.addAll(tempData)
                bindRestData(restData)
            }
            count++
        }
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    bindRestData(restData)
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {

                    if(newText!!.isNotEmpty())
                    {
                        restData.clear()
                        var search = newText.toLowerCase(Locale.getDefault())
                        for (restitem in tempData)
                        {

                            if(restitem.name!!.toLowerCase(Locale.getDefault()).contains(search))
                            {

                                restData.add(restitem)
                                bindRestData(restData)

                            }
                        }
                    }
                    else
                    {
                        restData.clear()
                        restData.addAll(tempData)
                        bindRestData(restData)

                    }

                    return true
                }
            })

               return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        parseJSON()

        binding.bottomNavigationMenu.setOnNavigationItemSelectedListener {
            if (it.itemId== R.id.profile){
                val action = RestaurantFragmentDirections.actionRestaurantFragmentToProfileFragment(args.user)
                Navigation.findNavController(view).navigate(action)

            }
            if (it.itemId== R.id.booking){
                val action = RestaurantFragmentDirections.actionRestaurantFragmentToListReservationFragment(args.user)
                Navigation.findNavController(view).navigate(action)

            }

            true


        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    fun parseJSON() {
        val retrofit = Retrofit.Builder()
            .baseUrl(getString(R.string.rest_base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ResturantService::class.java)
        CoroutineScope(Dispatchers.IO).launch {

            var response = service.getRest(getString(R.string.rest_url))

            response!!.enqueue(object : Callback<ArrayList<RestaurantModel>> {
                override fun onResponse(call: Call<ArrayList<RestaurantModel>>, response: Response<ArrayList<RestaurantModel>>)
                {

                    if (response.code() == 200) {
                        val restDataList = response.body()!!
                        if(restDataList!=null)
                        {
                            Collections.sort(restDataList, object : Comparator<RestaurantModel> {
                                override fun compare(o1: RestaurantModel?, o2: RestaurantModel?): Int = o2!!.rating.toFloat().roundToInt() - o1!!.rating.toFloat().roundToInt()
                            })

                        }
                        restData = restDataList
                        tempData =ArrayList<RestaurantModel>()
                        tempData.addAll(restData)
                        adapter = ResturantAdapter(context, restData, args.user)
                        binding.resturantrecyclerView.layoutManager =LinearLayoutManager(context)
                        binding.resturantrecyclerView.adapter =adapter

                    }

                    else
                    {
                        Log.e("restCallFailed","AndACustomTag")
                    }
                }
                override fun onFailure(call: Call<ArrayList<RestaurantModel>>, t: Throwable) {
                    Log.e("failure","Fail")
                }
            })
        }

    }


        fun bindRestData(arraylist:ArrayList<RestaurantModel>)
        {
            adapter = ResturantAdapter(context, arraylist, args.user)
            binding.resturantrecyclerView.layoutManager =LinearLayoutManager(context)
            binding.resturantrecyclerView.adapter =adapter
        }

}


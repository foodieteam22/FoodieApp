package com.example.foodieapp.view

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodieapp.R
import com.example.foodieapp.databinding.FragmentRestaurantBinding
import com.example.foodieapp.model.MenuModel
import com.example.foodieapp.viewadapter.ResturantAdapter
import com.example.foodieapp.model.RestaurantModel
import com.example.foodieapp.utils.ApiManager
import com.example.foodieapp.utils.MenuService
import com.example.foodieapp.utils.ResturantService
import com.example.foodieapp.viewadapter.MenuAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestaurantFragment : Fragment() {

    private lateinit var adapter: ResturantAdapter
    private lateinit var restData : List<RestaurantModel>
    private val binding get() = _binding!!
    private var _binding: FragmentRestaurantBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        _binding = FragmentRestaurantBinding.inflate(layoutInflater,container,false)
               val supportActionBar: ActionBar? = (requireActivity() as AppCompatActivity).supportActionBar
              if (supportActionBar != null) supportActionBar.hide()
               return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       // parseJSON()
    }


    fun parseJSON() {

        val retrofit = ApiManager.retrofitInstance

        // Create Service
        val service = retrofit.create(ResturantService::class.java)
        CoroutineScope(Dispatchers.IO).launch {

            var response = service.getRest("")
            response!!.enqueue(object : Callback<List<RestaurantModel>> {
                override fun onResponse(call: Call<List<RestaurantModel>>, response: Response<List<RestaurantModel>>)
                {
                    if (response.code() == 200) {
                        val restDataList = response.body()!!
                        restData = restDataList
                        adapter = ResturantAdapter(context, restData)
                        binding.resturantrecyclerView.layoutManager =LinearLayoutManager(context)
                        binding.resturantrecyclerView.adapter =adapter

                    }
                }
                override fun onFailure(call: Call<List<RestaurantModel>>, t: Throwable) {
                }
            })
        }

    }

}


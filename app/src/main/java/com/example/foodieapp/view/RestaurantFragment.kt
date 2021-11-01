package com.example.foodieapp.view

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodieapp.databinding.FragmentRestaurantBinding
import com.example.foodieapp.viewadapter.ResturantAdapter
import com.example.foodieapp.model.RestaurantModel
import com.example.foodieapp.utils.ResturantService
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
        parseJSON()
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Toast.makeText(context,"you click on hello", Toast.LENGTH_LONG).show()
        return super.onOptionsItemSelected(item)
    }

    fun parseJSON() {

        //val retrofit = ApiManager.retrofitInstance

        val retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create Service
        val service = retrofit.create(ResturantService::class.java)
        CoroutineScope(Dispatchers.IO).launch {

            var response = service.getRest("/foodieteam22/mockjson/main/test.json")

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

                    else
                    {
                        Log.e("onurmessage","AndACustomTag")
                    }
                }
                override fun onFailure(call: Call<List<RestaurantModel>>, t: Throwable) {
                    Log.e("failure","Fail")
                }
            })
        }

    }

}


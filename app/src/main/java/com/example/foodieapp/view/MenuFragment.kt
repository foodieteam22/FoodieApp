package com.example.foodieapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodieapp.R
import com.example.foodieapp.databinding.FragmentMenuBinding
import com.example.foodieapp.model.MenuListModel
import com.example.foodieapp.model.MenuModel
import com.example.foodieapp.utils.MenuService
import com.example.foodieapp.viewadapter.MenuAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory




class MenuFragment : Fragment() {
    private lateinit var adapter: MenuAdapter
    private lateinit var menuList: List<MenuListModel>
    private lateinit var menu: List<MenuModel>
    private val binding get() = _binding!!
    private var _binding: FragmentMenuBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       _binding = FragmentMenuBinding.inflate(layoutInflater,container,false)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parseJSON()
    }

    fun parseJSON() {

        val args = MenuFragmentArgs.fromBundle(requireArguments())
        val restaurantId = args.restaurantId

        val retrofit = Retrofit.Builder()
            .baseUrl(getString(R.string.menu_base_url))
            .addConverterFactory(GsonConverterFactory.create())

            .build()

        val service = retrofit.create(MenuService::class.java)
        CoroutineScope(Dispatchers.IO).launch {


            //val response = service.getMenu(String.format(getString(R.string.menu_url),menuId))
            val response = service.getMenu(getString(R.string.menu_url))

            response.enqueue(object : Callback<List<MenuListModel>> {
                override fun onResponse(call: Call<List<MenuListModel>>, response: Response<List<MenuListModel>>)
                {
                    if (response.code() == 200) {
                        val menures = response.body()!!
                        menuList=menures
                        val singleMenu =menuList.first{ x-> x.restaurantId == restaurantId}
                        menu= singleMenu.categories
                        adapter = MenuAdapter(context, menu)
                        binding.menuRecyclerView.layoutManager = LinearLayoutManager(context)
                        binding.menuRecyclerView.adapter = adapter
                    }
                }

                override fun onFailure(call: Call<List<MenuListModel>>, t: Throwable) {
                }
            })
        }

    }

}
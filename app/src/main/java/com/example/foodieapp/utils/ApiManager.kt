package com.example.foodieapp.utils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    private var APP_ID :String =""
    private var APP_KEY:String= ""
    companion object{
        private  lateinit var retrofit:Retrofit
        private  val BASE_URL = "https://worldwide-restaurants.p.rapidapi.com/search"
        val retrofitInstance :Retrofit

        get() {
            if(retrofit==null)
            {
                retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }


}
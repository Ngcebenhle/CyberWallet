package com.example.cyberwallet

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    object RetrofitIN {

        val lenientGson = GsonBuilder()
            .setLenient()
            .create()
        private const val BASE_URL = "http://192.168.0.39:5000/api/"

        val api: ApiServices by lazy {
            Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(lenientGson))
                .build()
                .create(ApiServices::class.java)

        }
    }
}

//192.168.0.39
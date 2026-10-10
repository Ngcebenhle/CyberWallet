package com.example.cyberwallet

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    // 1. Create a lenient Gson instance
    val lenientGson = GsonBuilder()
        .setLenient()
        .create()
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl("http://192.168.0.99:3000/api/restaurant/")
            .addConverterFactory(GsonConverterFactory.create(lenientGson))
            .build()
    }

    val apiInterface by lazy {
        retrofit.create(ApiServices::class.java)
    }
}
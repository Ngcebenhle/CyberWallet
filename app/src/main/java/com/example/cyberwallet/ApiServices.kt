package com.example.cyberwallet

import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {


    @GET("restaurant")
    fun getData(): Call<ResponseData>
//    @GET("end/point")
//    fun getExampleData(): Call<ExampleResponse>
}
package com.example.cyberwallet

import retrofit2.Response
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiServices {


    @GET("restaurant/restaurant")
    suspend fun Test(): Response<ResponseData>

//    @GET("/user/login")
//    suspend fun sendUserData(@Body user: UserRequest): Response<ApiResponse>

//    @GET("restaurant")
//    suspend fun Test(): Response<ResponseData>
//    @GET("restaurant")
//    suspend fun Test(): Response<ResponseData>



    @POST("/user/signup")
    suspend fun signup(@Body request : backendUserNodejs): Response<backendUserNodejsResponse>


//
//    @POST("/api/user")
//    suspend fun sendUserData(@Body user: UserRequest): Response<ApiResponse>
}
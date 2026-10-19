package com.example.cyberwallet

import okhttp3.Request
import retrofit2.Response
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiServices {


    @GET("restaurant/restaurant")
    suspend fun Test(): Response<ResponseData>

//    @GET("/user/login")
//    suspend fun sendUserData(@Body user: UserRequest): Response<ApiResponse>

//    @GET("restaurant")
//    suspend fun Test(): Response<ResponseData>
//    @GET("restaurant")
//    suspend fun Test(): Response<ResponseData>

    @FormUrlEncoded
    @POST("user/signup")
    suspend fun signup(
        @Field("name") name : String,
        @Field("email") email : String,
        @Field("password") password : String,
        @Field("type") type : String
    ): Response<backendUserNodejsResponse>

//    @Header("Authorization") token : String,
    @GET("user/login")
    suspend fun logIn(

        @Query("email") email : String,
        @Query("password") password : String
    ) : Response<backendUserNodejsLogInResponse>

    @FormUrlEncoded
    @GET("item/login")
    suspend fun AddExpenses(
        @Field("email") email : String,
        @Field("password") password : String,
    ) : Response<backendUserNodejsLogInResponse>

    @FormUrlEncoded
    @GET("item/login")
    suspend fun Addincome(
        @Field("email") email : String,
        @Field("password") password : String,
    ) : Response<backendUserNodejsLogInResponse>


    @GET("user/login")
    suspend fun logIn2(@Body email : String, password : String
    ) : Response<backendUserNodejsLogInResponse>

//
//    @POST("/api/user")
//    suspend fun sendUserData(@Body user: UserRequest): Response<ApiResponse>
}
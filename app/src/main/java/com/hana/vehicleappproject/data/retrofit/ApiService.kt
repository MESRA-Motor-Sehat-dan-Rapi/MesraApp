package com.hana.vehicleappproject.data.retrofit

import com.hana.vehicleappproject.data.response.LoginResponse
import com.hana.vehicleappproject.data.response.RegisterResponse
import okhttp3.MultipartBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @FormUrlEncoded
    @POST("/auth/register")
    suspend fun register(
        @Field("nama") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("/auth/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): LoginResponse}


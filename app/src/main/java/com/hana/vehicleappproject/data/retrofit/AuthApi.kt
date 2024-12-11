package com.hana.vehicleappproject.data.retrofit

import com.hana.vehicleappproject.data.login.LoginRequest
import com.hana.vehicleappproject.data.login.LoginResponse
import com.hana.vehicleappproject.data.signup.SignUpRequest
import com.hana.vehicleappproject.data.signup.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("/register")
    suspend fun register(@Body request: SignUpRequest): Response<SignUpResponse>
}

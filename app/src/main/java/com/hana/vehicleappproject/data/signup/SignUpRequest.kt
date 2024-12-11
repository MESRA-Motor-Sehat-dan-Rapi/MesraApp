package com.hana.vehicleappproject.data.signup

data class SignUpRequest(
    val email: String,
    val password: String
)

data class SignUpResponse(
    val success: Boolean,
    val message: String
)

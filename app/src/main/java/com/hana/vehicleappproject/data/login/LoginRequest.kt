package com.hana.vehicleappproject.data.login

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val success: Boolean,
    val token: String?, // Token untuk autentikasi (jika ada)
    val message: String // Pesan dari server
)


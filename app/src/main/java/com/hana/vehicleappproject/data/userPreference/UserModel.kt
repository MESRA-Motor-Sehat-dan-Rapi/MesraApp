package com.hana.vehicleappproject.data.userPreference

data class UserModel(
    val token: String,
    val name: String,
    val userId: String,
    val isLogin: Boolean = false
)
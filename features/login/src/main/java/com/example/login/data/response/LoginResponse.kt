package com.example.login.data.response

data class LoginResponse (
    val userId : Int,
    val accessToken: String,
    val refreshToken: String,
    val tokenType: String,
    val expiresIn: Int,
)

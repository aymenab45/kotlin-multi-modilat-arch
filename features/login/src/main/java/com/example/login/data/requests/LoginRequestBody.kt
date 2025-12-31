package com.example.login.data.requests

import com.google.gson.annotations.SerializedName

data class LoginRequestBody(
    @SerializedName("userName")
    val userName: String,
    @SerializedName("password")
    val password: String,
)

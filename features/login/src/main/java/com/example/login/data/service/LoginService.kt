package com.example.login.data.service

import com.example.login.data.requests.LoginRequestBody
import com.example.login.data.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

const val BASE_URL = "https://mydomain.com"
const val EMAIL = "email"

interface LoginService {

    @POST("$BASE_URL/login")
    suspend fun login(
        @Body loginRequestBody: LoginRequestBody,
    ): Response<UserResponse>

    @POST("$BASE_URL/forgetPassword")
    suspend fun forgetPassword(
        @Query(EMAIL) email: String,
    ): Response<Unit>
}

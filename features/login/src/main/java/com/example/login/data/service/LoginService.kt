package com.example.login.data.service

import com.example.login.data.requests.LoginRequestBody
import com.example.login.data.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

const val BASE_URL = "https://api.mockfly.dev/mocks/6ce0c106-cce3-4d84-a21d-0afc4865f7d2"
const val EMAIL = "email"

interface LoginService {

    @POST("$BASE_URL/auth/login")
    suspend fun login(
        @Body loginRequestBody: LoginRequestBody,
    ): Response<UserResponse>

    @POST("$BASE_URL/forgetPassword")
    suspend fun forgetPassword(
        @Query(EMAIL) email: String,
    ): Response<Unit>
}

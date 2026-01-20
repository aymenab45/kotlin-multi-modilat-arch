package com.example.login.data.service

import com.example.login.data.requests.LoginRequestBody
import com.example.login.data.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

const val BASE_URL = "https://api.mockfly.dev/mocks/65933e1a-d663-4351-af20-db0bae81dc23"
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

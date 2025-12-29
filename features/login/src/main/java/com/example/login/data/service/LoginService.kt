package com.example.login.data.service

import com.example.login.data.requests.LoginRequestBody
import com.example.login.data.response.LoginResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

const val BASE_URL = "https://mydomain.com"
const val EMAIL ="email"

interface LoginService {

    @POST("$BASE_URL/login")
  suspend  fun login(
        @Body loginRequestBody: LoginRequestBody
    ): LoginResponse


  @POST("$BASE_URL/forgetPassword")
    suspend fun forgetPassword(
        @Query(EMAIL) email: String
    ): Unit



}
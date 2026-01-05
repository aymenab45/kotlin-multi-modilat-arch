package com.example.data.service

import com.example.data.constants.BASE_URL
import com.example.data.constants.REFRESH_TOKEN
import com.example.data.response.TokenResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header

interface SessionService {

    @GET("${BASE_URL}auth/refresh")
    suspend fun getAccessToken(
        @Header(REFRESH_TOKEN) refreshToken: String,
    ): Response<TokenResponse>

    @DELETE("${BASE_URL}auth/logout")
    suspend fun logOut(): Response<Unit>
}

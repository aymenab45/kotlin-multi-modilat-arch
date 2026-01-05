package com.example.protodatastore.manager.session

import kotlinx.coroutines.flow.Flow

interface SessionsDataStoreInterface {

    suspend fun setAccessToken(accessToken: String)

    suspend fun setRefreshToken(refreshToken: String)

    suspend fun setUserId(userID: String)

    // getters

    suspend fun getAccessToken(): String
    fun getAccessTokenFlow(): Flow<String>

    suspend fun getRefreshToken(): String
    fun getRefreshTokenFlow(): Flow<String>

    suspend fun getUserID(): String
    fun getUserIDFlow(): Flow<String>
}

package com.example.protodatastore.manager.session

import androidx.datastore.core.DataStore
import com.aymen.proto.Session
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class SessionsDataStoreImplementer(private val sessionDataStore: DataStore<Session>) :
    SessionsDataStoreInterface {
    override suspend fun setAccessToken(accessToken: String) {

        sessionDataStore.updateData { currentPreferencesData ->
            currentPreferencesData.toBuilder().setAccessToken(accessToken).build()
        }
    }

    override suspend fun setRefreshToken(refreshToken: String) {
        sessionDataStore.updateData { currentPreferencesData ->
            currentPreferencesData.toBuilder().setRefreshToken(refreshToken).build()
        }
    }

    override suspend fun setUserId(userID: String) {
        sessionDataStore.updateData { currentPreferencesData ->
            currentPreferencesData.toBuilder().setUserId(userID).build()
        }
    }

    override suspend fun getAccessToken(): String = sessionDataStore.data.first().accessToken


    override fun getAccessTokenFlow(): Flow<String> {
        return sessionDataStore.data.map { session ->
            session.accessToken
        }
    }

    override suspend fun getRefreshToken(): String = sessionDataStore.data.first().refreshToken


    override fun getRefreshTokenFlow(): Flow<String> {
        return sessionDataStore.data.map { session ->
            session.refreshToken
        }
    }

    override suspend fun getUserID(): String = sessionDataStore.data.first().userId


    override fun getUserIDFlow(): Flow<String> {
        return sessionDataStore.data.map { session ->
            session.userId
        }
    }

}

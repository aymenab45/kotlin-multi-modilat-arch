package com.example.data.interceptors

import com.example.data.constants.BEARER
import com.example.data.response.TokenResponse
import com.example.data.service.SessionService
import com.example.data.source.DataSource.Companion.UNAUTHORISED
import com.example.protodatastore.manager.session.SessionsDataStoreInterface
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthenticationInterceptor @Inject constructor(
    private val sessionDataStore: SessionsDataStoreInterface,
    private val coroutineDispatchers: CoroutineDispatcher,
) : Interceptor {
    @Inject
    lateinit var sessionService: SessionService
    override fun intercept(chain: Interceptor.Chain): Response {
        val mutex = Mutex()
        val request = chain.request()
        val accessToken = runBlocking(coroutineDispatchers) { sessionDataStore.getAccessToken() }
        val authenticatedRequest =
            request.newBuilder().header(AUTHORIZATION_HEADER, "$BEARER $accessToken").build()
        val response = chain.proceed(authenticatedRequest)

        if (response.code != UNAUTHORISED) {
            // ACCESS Token is valid
            return response
        }

        val tokenResponse: TokenResponse? = runBlocking {
            mutex.withLock {
                val tokenResponse = getAccessToken()
                tokenResponse.body().also {
                    sessionDataStore.setAccessToken(it?.accessToken ?: "")
                    sessionDataStore.setRefreshToken(it?.refreshToken ?: "")
                }
            }
        }
        return if (tokenResponse?.accessToken != null) {
            response.close()

            // retry the original request with the new token
            val authenticatedRequest =
                request.newBuilder()
                    .header(AUTHORIZATION_HEADER, "Bearer ${tokenResponse.accessToken}").build()

            val response = chain.proceed(authenticatedRequest)

            response
        } else {
            response
        }
    }

    suspend fun getAccessToken(): retrofit2.Response<TokenResponse> {
        val refreshToken = sessionDataStore.getRefreshToken()
        return withContext(coroutineDispatchers) {
            sessionService.getAccessToken(refreshToken)
        }
    }
}

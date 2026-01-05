package com.example.data.di

import com.example.data.BuildConfig
import com.example.data.connectivity.NetworkMonitorInterface
import com.example.data.constants.Authentication_INTERCEPTOR_TAG
import com.example.data.constants.CLIENT_ID_TAG
import com.example.data.constants.CONNECTIVITY_INTERCEPTOR_TAG
import com.example.data.constants.HEADER_INTERCEPTOR_TAG
import com.example.data.constants.IO_DISPATCHER_TAG
import com.example.data.constants.LANGUAGE_TAG
import com.example.data.constants.LOGGING_INTERCEPTOR_TAG
import com.example.data.interceptors.AUTHORIZATION_HEADER
import com.example.data.interceptors.AuthenticationInterceptor
import com.example.data.interceptors.CLIENT_ID_HEADER
import com.example.data.interceptors.ConnectivityInterceptor
import com.example.data.interceptors.HeaderInterceptor
import com.example.protodatastore.manager.session.SessionsDataStoreInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import java.util.Locale
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class InterceptorModule {

    @Provides
    @Singleton
    @Named(Authentication_INTERCEPTOR_TAG)
    fun provideAuthenticationInterceptor(
        sessionDataStore: SessionsDataStoreInterface,
        @Named(IO_DISPATCHER_TAG) dispatchers: CoroutineDispatcher,
    ): Interceptor {
        return AuthenticationInterceptor(sessionDataStore = sessionDataStore, coroutineDispatchers = dispatchers)
    }

    @Provides
    @Singleton
    @Named(CONNECTIVITY_INTERCEPTOR_TAG)
    fun provideConnectivityInterceptor(
        networkMonitor: NetworkMonitorInterface,
    ): Interceptor {
        return ConnectivityInterceptor(networkMonitor)
    }

    @Provides
    @Singleton
    @Named(HEADER_INTERCEPTOR_TAG)
    fun provideHeaderInterceptor(
        @Named(CLIENT_ID_TAG) clientId: String,
        @Named(LANGUAGE_TAG) language: () -> Locale,
    ): Interceptor {
        return HeaderInterceptor(
            clientId = clientId,
            language = language,
        )
    }

    @Provides
    @Singleton
    @Named(LOGGING_INTERCEPTOR_TAG)
    fun provideHttpLogger(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        if (!BuildConfig.DEBUG) {
            interceptor.redactHeader(AUTHORIZATION_HEADER)
            interceptor.redactHeader(CLIENT_ID_HEADER)
        }
        return interceptor
    }
}

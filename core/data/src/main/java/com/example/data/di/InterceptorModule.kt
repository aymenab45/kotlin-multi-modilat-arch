package com.example.data.di

import com.example.data.BuildConfig
import com.example.data.constants.ACCESS_TOKEN_TAG
import com.example.data.constants.CLIENT_ID_TAG
import com.example.data.constants.HEADER_INTERCEPTOR_TAG
import com.example.data.constants.LANGUAGE_TAG
import com.example.data.constants.LOGGING_INTERCEPTOR_TAG
import com.example.data.constants.REFRESH_TOKEN_TAG
import com.example.data.interceptors.AUTHORIZATION_HEADER
import com.example.data.interceptors.CLIENT_ID_HEADER
import com.example.data.interceptors.HeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
    @Named(HEADER_INTERCEPTOR_TAG)
    fun provideHeaderInterceptor(
        @Named(CLIENT_ID_TAG) clientId: String,
        @Named(ACCESS_TOKEN_TAG) accessToken: () -> String?,
        @Named(REFRESH_TOKEN_TAG) refreshToken: () -> String?,
        @Named(LANGUAGE_TAG) language: () -> Locale,
    ): Interceptor {
        return HeaderInterceptor(
            clientId = clientId,
            accessToken = accessToken,
            refreshToken = refreshToken,
            language = language
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
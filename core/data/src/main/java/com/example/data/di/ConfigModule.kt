package com.example.data.di

import com.example.data.constants.ACCESS_TOKEN_TAG
import com.example.data.constants.CLIENT_ID_TAG
import com.example.data.constants.LANGUAGE_TAG
import com.example.data.constants.REFRESH_TOKEN_TAG
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.Locale
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ConfigModule {
    @Provides
    @Singleton
    @Named(LANGUAGE_TAG)
    fun provideLocalLanguage(): () -> Locale {
        return { Locale.ENGLISH }
    }

    @Provides
    @Singleton
    @Named(ACCESS_TOKEN_TAG)
    fun provideAccessToken(): () -> String? {
        return { "" }
    }

    @Provides
    @Singleton
    @Named(REFRESH_TOKEN_TAG)
    fun provideRefreshToken(): () -> String? {
        return { "" }
    }

    @Provides
    @Singleton
    @Named(CLIENT_ID_TAG)
    fun provideClientId(): String {
        return ""
    }
}
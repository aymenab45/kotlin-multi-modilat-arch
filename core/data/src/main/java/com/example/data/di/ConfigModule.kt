package com.example.data.di

import com.example.data.constants.ACCESS_TOKEN_TAG
import com.example.data.constants.CLIENT_ID_TAG
import com.example.data.constants.LANGUAGE_TAG
import com.example.data.constants.REFRESH_TOKEN_TAG
import com.example.protodatastore.manager.prefrences.PreferencesDataStoreInterface
import com.example.protodatastore.manager.session.SessionsDataStoreInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import java.util.Locale
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ConfigModule {
    @Provides
    @Singleton
    @Named(LANGUAGE_TAG)
    fun provideLocalLanguage(preferencesDataStore: PreferencesDataStoreInterface): () -> Locale {
        val language = runBlocking { preferencesDataStore.getLanguage() }
        if (language.isEmpty()) {
            return { Locale.getDefault() }
        } else {

            return { Locale(language) }
        }

    }

    @Provides
    @Singleton
    @Named(ACCESS_TOKEN_TAG)
    fun provideAccessToken(sessionDataStore: SessionsDataStoreInterface): () -> String? {
        val accessToken = runBlocking { sessionDataStore.getAccessToken() }

        return { accessToken }
    }

    @Provides
    @Singleton
    @Named(REFRESH_TOKEN_TAG)
    fun provideRefreshToken(sessionDataStore: SessionsDataStoreInterface): () -> String? {
        val refreshToken = runBlocking { sessionDataStore.getRefreshToken() }

        return { refreshToken }
    }

    @Provides
    @Singleton
    @Named(CLIENT_ID_TAG)
    fun provideClientId(sessionDataStore: SessionsDataStoreInterface): String {
        val userID = runBlocking { sessionDataStore.getUserID() }
        return userID.ifEmpty {
            ""
        }
    }
}

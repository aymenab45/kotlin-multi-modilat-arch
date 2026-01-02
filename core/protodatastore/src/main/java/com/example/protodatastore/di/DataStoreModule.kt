package com.example.protodatastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import com.aymen.proto.Preferences
import com.aymen.proto.Session
import com.example.protodatastore.factory.preferencesDataStore
import com.example.protodatastore.factory.sessionDataStore
import com.example.protodatastore.manager.prefrences.PreferencesDataStoreImplementer
import com.example.protodatastore.manager.prefrences.PreferencesDataStoreInterface
import com.example.protodatastore.manager.session.SessionsDataStoreImplementer
import com.example.protodatastore.manager.session.SessionsDataStoreInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class DataStoreModule {

    @Provides
    @Singleton
    fun provideSessionDataStore(@ApplicationContext context: Context): DataStore<Session>{
        return context.sessionDataStore

    }
    @Provides
    @Singleton
    fun providePreferencesDataStore(@ApplicationContext context: Context): DataStore<Preferences>{
        return context.preferencesDataStore

    }

    @Provides
    @Singleton
    fun provideSessionDataStoreManager(sessionDataStore: DataStore<Session>): SessionsDataStoreInterface{
        return SessionsDataStoreImplementer(sessionDataStore)

    }
    @Provides
    @Singleton
    fun providePreferencesDataStoreManager(preferencesDataStore: DataStore<Preferences>): PreferencesDataStoreInterface{
        return PreferencesDataStoreImplementer(preferencesDataStore)

    }

}
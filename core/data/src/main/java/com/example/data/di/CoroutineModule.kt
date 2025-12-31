package com.example.data.di

import com.example.data.constants.DEFAULT_DISPATCHER_TAG
import com.example.data.constants.IO_DISPATCHER_TAG
import com.example.data.constants.MAIN_DISPATCHER_TAG
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoroutineModule {

    @Provides
    @Singleton
    @Named(MAIN_DISPATCHER_TAG)
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @Singleton
    @Named(DEFAULT_DISPATCHER_TAG)
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @Singleton
    @Named(IO_DISPATCHER_TAG)
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}

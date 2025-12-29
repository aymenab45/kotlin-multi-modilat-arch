package com.example.login.data.di

import com.example.data.factory.ServiceFactory
import com.example.login.data.service.LoginService
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    fun provideLoginService(serviceFactory: ServiceFactory): LoginService {
        return serviceFactory.create(LoginService::class.java)
    }
}
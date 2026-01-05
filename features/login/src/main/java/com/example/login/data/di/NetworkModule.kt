package com.example.login.data.di

import com.example.data.constants.CLIENT_ID_TAG
import com.example.data.constants.IO_DISPATCHER_TAG
import com.example.data.factory.ServiceFactory
import com.example.data.source.NetworkDataSource
import com.example.login.data.mapper.LoginMapper
import com.example.login.data.mapper.LoginMapperImplementer
import com.example.login.data.service.LoginService
import com.example.login.data.source.LoginRemote
import com.example.login.data.source.LoginRemoteImplementer
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideLoginService(serviceFactory: ServiceFactory): LoginService {
        return serviceFactory.create(LoginService::class.java)
    }

    @Provides
    @Singleton
    fun provideLoginNetworkDataSource(
        service: LoginService,
        gson: Gson,
        @Named(CLIENT_ID_TAG) userIdProvider: () -> String,
    ): NetworkDataSource<LoginService> {
        return NetworkDataSource(
            service = service,
            gson = gson,
            userIdProvider = userIdProvider,
        )
    }

    @Provides
    @Singleton
    fun provideLoginMapper(@Named(IO_DISPATCHER_TAG) dispatcher: CoroutineDispatcher): LoginMapper {
        return LoginMapperImplementer(dispatcher)
    }

    @Provides
    @Singleton
    fun provideLoginRemote(networkDataSource: NetworkDataSource<LoginService>, loginMapper: LoginMapper): LoginRemote {
        return LoginRemoteImplementer(networkDataSource, loginMapper)
    }
}

package com.example.data.di

import com.aymen.debug.OkHttpClientProvider
import com.example.data.BuildConfig
import com.example.data.constants.HEADER_INTERCEPTOR_TAG
import com.example.data.constants.LOGGING_INTERCEPTOR_TAG
import com.example.data.factory.ServiceFactory
import com.example.data.okHttp.OkHttpClientProviderInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClientProvider(): OkHttpClientProviderInterface {
        return OkHttpClientProvider()
    }

    @Provides
    @Singleton
    fun provideOkHttpCallFactory(
        @Named(LOGGING_INTERCEPTOR_TAG) provideHttpLogger: Interceptor,
        @Named(HEADER_INTERCEPTOR_TAG) provideHeaderInterceptor: Interceptor,
        okHttpClientProvider: OkHttpClientProviderInterface
    ): Call.Factory {
        return okHttpClientProvider.getOkHttpClient(BuildConfig.DEV_PIN_CERTIFICATE)
            .addInterceptor(provideHttpLogger).addInterceptor(provideHeaderInterceptor)
            .retryOnConnectionFailure(true)
            .followRedirects(false)
            .followSslRedirects(false)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val build = Retrofit.Builder()
            .baseUrl("")
            .client(okHttpClient)
        return build.build()
    }

    @Provides
    @Singleton
    fun provideServiceFactory(retrofit: Retrofit): ServiceFactory {
        return ServiceFactory(retrofit)
    }
}




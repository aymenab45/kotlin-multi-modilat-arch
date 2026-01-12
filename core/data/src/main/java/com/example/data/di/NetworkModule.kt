package com.example.data.di

import android.content.Context
import com.aymen.debug.OkHttpClientProvider
import com.example.data.BuildConfig
import com.example.data.connectivity.NetworkMonitorImplementer
import com.example.data.connectivity.NetworkMonitorInterface
import com.example.data.constants.Authentication_INTERCEPTOR_TAG
import com.example.data.constants.BASE_URL
import com.example.data.constants.CONNECTIVITY_INTERCEPTOR_TAG
import com.example.data.constants.HEADER_INTERCEPTOR_TAG
import com.example.data.constants.LOGGING_INTERCEPTOR_TAG
import com.example.data.factory.ServiceFactory
import com.example.data.okHttp.OkHttpClientProviderInterface
import com.example.data.service.SessionService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkMonitor(@ApplicationContext context: Context): NetworkMonitorInterface {
        return NetworkMonitorImplementer(context)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideOkHttpClientProvider(): OkHttpClientProviderInterface {
        return OkHttpClientProvider()
    }

    @Provides
    @Singleton
    fun provideSessionService(serviceFactory: ServiceFactory): SessionService {
        return serviceFactory.create(SessionService::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpCallFactory(
        @Named(LOGGING_INTERCEPTOR_TAG) provideHttpLogger: Interceptor,
        @Named(HEADER_INTERCEPTOR_TAG) provideHeaderInterceptor: Interceptor,
        @Named(Authentication_INTERCEPTOR_TAG) provideAuthenticationInterceptor: Interceptor,
        @Named(CONNECTIVITY_INTERCEPTOR_TAG) provideConnectivityInterceptor: Interceptor,
        okHttpClientProvider: OkHttpClientProviderInterface,
    ): OkHttpClient {
        return okHttpClientProvider.getOkHttpClient(BuildConfig.DEV_PIN_CERTIFICATE)
            .addInterceptor(provideHttpLogger)
            .addInterceptor(provideHeaderInterceptor)
            .addInterceptor(provideConnectivityInterceptor)
            .addInterceptor(provideAuthenticationInterceptor)
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
        val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideServiceFactory(retrofit: Retrofit): ServiceFactory {
        return ServiceFactory(retrofit)
    }
}

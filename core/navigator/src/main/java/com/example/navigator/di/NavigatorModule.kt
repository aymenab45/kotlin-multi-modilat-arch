package com.example.navigator.di

import com.example.navigator.core.AppNavigator
import com.example.navigator.core.AppNavigatorImpl
import dagger.Module

import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class NavigatorModule {


   abstract fun navigator(appNavigatorImpl: AppNavigatorImpl): AppNavigator
}
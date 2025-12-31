package com.example.data.okHttp

import okhttp3.OkHttpClient

interface OkHttpClientProviderInterface {

    fun getOkHttpClient(pin: String): OkHttpClient.Builder

    fun cancelAllRequests()
}

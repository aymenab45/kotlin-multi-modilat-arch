package com.example.data.source

import com.example.data.connectivity.NetworkMonitorInterface
import com.google.gson.Gson

class NetworkDataSource<SERVICE> (
    private val serviceClass: Class<SERVICE>,
    private val gson: Gson,
    private val networkMonitor: NetworkMonitorInterface,
    private val userIdProvider: () -> String,
)


package com.example.protodatastore.factory

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.aymen.proto.Preferences
import com.aymen.proto.Session
import com.example.protodatastore.serializer.PreferencesSerializer
import com.example.protodatastore.serializer.SessionSerializer

val Context.sessionDataStore: DataStore<Session> by dataStore(
    fileName = "session.pb",
    serializer = SessionSerializer,
)
val Context.preferencesDataStore: DataStore<Preferences> by dataStore(
    fileName = "preferences.pb",
    serializer = PreferencesSerializer,
)

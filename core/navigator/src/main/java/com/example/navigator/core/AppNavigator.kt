package com.example.navigator.core

import androidx.navigation.NavOptionsBuilder
import com.example.navigator.event.NavigatorEvent
import kotlinx.coroutines.flow.Flow

interface AppNavigator {

    fun navigateUp(): Boolean

    fun popBackStack()

    fun navigate(
        route: String,
        builder: NavOptionsBuilder.() -> Unit = { launchSingleTop =true}
    )

    val destinations: Flow<NavigatorEvent>

}
package com.example.navigator.core

import androidx.navigation.NavOptionsBuilder
import com.example.navigator.event.NavigatorEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Singleton

@Singleton
class AppNavigatorImpl() :AppNavigator {
    private val navigationEvents = Channel<NavigatorEvent>()

    override fun navigateUp(): Boolean  = navigationEvents.trySend(NavigatorEvent.NavigateUp).isSuccess

    override fun popBackStack() {
        navigationEvents.trySend(NavigatorEvent.PopBackStack)
    }

    override fun navigate(
        route: String,
        builder: NavOptionsBuilder.() -> Unit
    ) {
        navigationEvents.trySend(NavigatorEvent.Directions(route, builder))
    }

    override val destinations: Flow<NavigatorEvent>
        get() = navigationEvents.receiveAsFlow()

}
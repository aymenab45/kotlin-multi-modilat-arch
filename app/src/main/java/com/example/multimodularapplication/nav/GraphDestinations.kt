package com.example.multimodularapplication.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.home.HomeScreenView
import com.example.login.presentation.view.LoginScreen
import com.example.navigator.core.AppNavigator
import com.example.navigator.destinitions.HomeDestination
import com.example.navigator.destinitions.LoginDestination
import com.example.navigator.destinitions.NavigationDestination
import com.example.navigator.destinitions.SignUpDestination
import com.example.signup.SignUpView

private val composableDestinations: Map<NavigationDestination, @Composable (AppNavigator, NavHostController) -> Unit> =
    mapOf(
        SignUpDestination to { _, _ -> SignUpView() },
        HomeDestination to { _, navHostController -> HomeScreenView(navHostController) },
        LoginDestination() to { appNavigator, _ -> LoginScreen(appNavigator = appNavigator) },
    )

fun NavGraphBuilder.addComposableDestinations(
    appNavigator: AppNavigator,
    navHostController: NavHostController,
) {
    composableDestinations.forEach { entry ->
        val destination = entry.key
        composable(
            route = destination.destination(),
            arguments = destination.arguments,
            deepLinks = destination.deepLinks,
        ) {
            entry.value(appNavigator, navHostController)
        }
    }
}

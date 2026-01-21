package com.example.navigator.destinitions

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

const val HOME_ROOT = "home-root"
const val USER_ID = "id"
const val USER_NAME = "name"
const val User_EMAIL = "email"

class HomeDestination : NavigationDestination {
    override fun destination(): String = Screens.HomeScreenRoot.route

    override val arguments: List<NamedNavArgument>
        get() = listOf(
            navArgument("id") {
                type = NavType.IntType
            },
            navArgument("name") {
                type = NavType.StringType
            },
            navArgument("email") {
                type = NavType.StringType
            }
        )
}


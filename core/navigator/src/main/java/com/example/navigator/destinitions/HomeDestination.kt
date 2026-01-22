package com.example.navigator.destinitions

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

const val HOME_ROOT = "home-root"
const val USER_ID = "id"
const val USER_NAME = "name"
const val USER_EMAIL = "email"
const val USER = "user"

object HomeDestination : NavigationDestination {
    fun createHome(user: String, fullName: String, age: Int): String =
        "$HOME_ROOT/$user/$fullName/$age"

    override fun destination(): String = Screens.HomeScreenRoot.route

    override val arguments: List<NamedNavArgument>
        get() = listOf(
            navArgument(USER) {
                type = NavType.StringType
            },
            navArgument(USER_ID) {
                type = NavType.IntType
            },
            navArgument(USER_NAME) {
                type = NavType.StringType
            },
            navArgument(USER_EMAIL) {
                type = NavType.StringType
            },
        )
}

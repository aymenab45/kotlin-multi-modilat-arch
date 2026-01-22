package com.example.navigator.destinitions

const val SignUp_ROOT = "home-root"
object SignUpDestination : NavigationDestination {
    override fun destination(): String = Screens.SignUpScreenRoot.route
}

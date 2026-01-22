package com.example.navigator.destinitions

const val LOGIN_ROOT = "login-root"

class LoginDestination : NavigationDestination {
    override fun destination(): String = Screens.LoginScreenRoot.route
}

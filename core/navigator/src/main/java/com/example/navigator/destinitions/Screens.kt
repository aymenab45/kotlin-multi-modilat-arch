package com.example.navigator.destinitions

sealed class Screens(val route: String) {
    data object LoginScreenRoot : Screens(route = LOGIN_ROOT)
    data object HomeScreenRoot : Screens(route = "$HOME_ROOT/$USER/$USER_NAME/$USER_EMAIL")
    data object SignUpScreenRoot : Screens(route = SignUp_ROOT)
}

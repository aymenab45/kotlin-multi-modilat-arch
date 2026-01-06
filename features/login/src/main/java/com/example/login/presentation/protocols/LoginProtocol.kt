package com.example.login.presentation.protocols

import com.example.login.presentation.error.LoginError

sealed class LoginActions {

    data class UserNameUpdated(val userName: String) : LoginActions()
    data class PasswordUpdated(val password: String) : LoginActions()
    data object LoginClicked : LoginActions()
    data object RegisterClicked : LoginActions()
}


sealed class LoginEvents {
    data object NavigateToMain : LoginEvents()
    data object NavigateToRegister : LoginEvents()
    data object ShowLoading : LoginEvents()
    data class ShowError(val error: LoginError) : LoginEvents()
}


data class LoginState (
    val userName: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: LoginError? = null,
    val isLoginButtonEnabled: Boolean = false,
    val userNameError: LoginError = LoginError.NoEntry,
    val passwordError: LoginError = LoginError.NoEntry,
){
    fun showPasswordError(): Boolean = passwordError != LoginError.NoError && passwordError != LoginError.NoEntry

    fun showUserNameError(): Boolean = userNameError != LoginError.NoError && userNameError != LoginError.NoEntry
}



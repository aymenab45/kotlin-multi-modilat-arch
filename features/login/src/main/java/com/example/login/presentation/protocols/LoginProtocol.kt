package com.example.login.presentation.protocols

import com.example.domain.model.User
import com.example.login.presentation.error.LoginError

sealed class LoginInput {

    data class UserNameUpdated(val userName: String) : LoginInput()
    data class PasswordUpdated(val password: String) : LoginInput()
    data object LoginClicked : LoginInput()
    data object RegisterClicked : LoginInput()
}

sealed class LoginOutput {
    data class NavigateToMain(val user: User) : LoginOutput()
    data object NavigateToRegister : LoginOutput()
    data class ShowError(val error: LoginError) : LoginOutput()
}

data class LoginState(
    val userName: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: LoginError? = null,
    val isLoginButtonEnabled: Boolean = false,
    val userNameError: LoginError = LoginError.NoEntry,
    val passwordError: LoginError = LoginError.NoEntry,
) {
    fun showPasswordError(): Boolean = passwordError != LoginError.NoError && passwordError != LoginError.NoEntry

    fun showUserNameError(): Boolean = userNameError != LoginError.NoError && userNameError != LoginError.NoEntry
}

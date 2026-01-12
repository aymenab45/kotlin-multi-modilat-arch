package com.example.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login.domain.usecase.LoginUseCase
import com.example.login.presentation.protocols.LoginInput
import com.example.login.presentation.protocols.LoginOutput
import com.example.login.presentation.protocols.LoginState
import com.example.login.presentation.validator.LoginValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {
    var loginViewState = LoginState()

    private val _event: Channel<LoginOutput> = Channel()
    val event = _event.receiveAsFlow()

    fun setAction(action: LoginInput) {
        when (action) {
            is LoginInput.LoginClicked -> login()
            is LoginInput.PasswordUpdated -> updateState { copy(password = action.password) }
            is LoginInput.RegisterClicked -> sendEvent { LoginOutput.NavigateToRegister }
            is LoginInput.UserNameUpdated -> updateState { copy(userName = action.userName) }
        }
    }

    private fun updateState(updateState: LoginState.() -> LoginState) {
        loginViewState = loginViewState.updateState()
        validate()
    }

    private fun validate() {
        val usernameError = LoginValidator.userNameError(loginViewState.userName)
        val passwordError = LoginValidator.passwordError(loginViewState.password)
        val isButtonEnabled = LoginValidator.checkforLoginButton(userNameError = usernameError, passwordError = passwordError)
        loginViewState = loginViewState.copy(
            userNameError = usernameError,
            passwordError = passwordError,
            isLoginButtonEnabled = isButtonEnabled,
        )
    }
    fun sendEvent(action: () -> LoginOutput) {
        viewModelScope.launch {
            _event.send(action())
        }
    }

    fun login() {
        viewModelScope.launch {
            loginUseCase.execute(
                LoginUseCase.Input(loginViewState.userName, loginViewState.password),
                success = { },
                error = {},
            )
        }
    }
}

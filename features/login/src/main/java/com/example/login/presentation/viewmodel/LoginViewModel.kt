package com.example.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login.presentation.protocols.LoginActions
import com.example.login.presentation.protocols.LoginEvents
import com.example.login.presentation.protocols.LoginState
import com.example.login.presentation.validator.LoginValidator
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class LoginViewModel : ViewModel() {
    var loginViewState = LoginState()

    private val _event : Channel<LoginEvents> = Channel()
    val event = _event.receiveAsFlow()



    fun setAction(action: LoginActions){
        when(action){
            is LoginActions.LoginClicked -> login()
            is LoginActions.PasswordUpdated -> updateState{copy(password = action.password)}
            is LoginActions.RegisterClicked -> sendEvent{LoginEvents.NavigateToRegister}
            is LoginActions.UserNameUpdated -> updateState{copy(userName = action.userName)}
        }
    }


   private fun updateState(updateState: LoginState.()-> LoginState){
        loginViewState = loginViewState.updateState()
       validate()
    }


    private fun validate(){
        val usernameError = LoginValidator.userNameError(loginViewState.userName)
        val passwordError = LoginValidator.passwordError(loginViewState.password)
        val isButtonEnabled = LoginValidator.checkforLoginButton(userNameError = usernameError, passwordError = passwordError)
        loginViewState = loginViewState.copy(
            userNameError = usernameError,
            passwordError = passwordError,
            isLoginButtonEnabled = isButtonEnabled,
        )
    }


    fun sendEvent(action:()->LoginEvents){
        viewModelScope.launch {
            _event.send(action())
        }

    }



    fun login() {
    }
}

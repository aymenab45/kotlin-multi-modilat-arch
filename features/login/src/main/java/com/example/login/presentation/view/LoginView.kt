package com.example.login.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.model.toJson
import com.example.login.R
import com.example.login.presentation.protocols.LoginInput
import com.example.login.presentation.protocols.LoginOutput
import com.example.login.presentation.protocols.LoginState
import com.example.login.presentation.viewmodel.LoginViewModel
import com.example.navigator.core.AppNavigator
import com.example.navigator.destinitions.HomeDestination
import com.example.navigator.destinitions.SignUpDestination
import com.example.presentation.StateRenderer

@Composable
fun LoginScreen(appNavigator: AppNavigator) {
    val loginViewModel: LoginViewModel = hiltViewModel()

    val stateRenderer by loginViewModel.stateRenderer.collectAsState()

    // React to viewOutput events

    LaunchedEffect(loginViewModel) {
        loginViewModel.event.collect { output ->
            when (output) {
                is LoginOutput.NavigateToMain -> {
                    appNavigator.navigate(HomeDestination.createHome(output.user.toJson(), output.user.userName, 24))
                }
                is LoginOutput.NavigateToRegister -> {
                    appNavigator.navigate(SignUpDestination.destination())
                }
                is LoginOutput.ShowError -> TODO()
            }
        }
    }

    // State Renderer

    StateRenderer.of(stateRenderer = stateRenderer, retryAction = { loginViewModel.login() }) {
        onUiState { updatedState ->
            ScreeUiContent(updatedState, loginViewModel)
        }
        onLoadingState { _ ->
            // ScreeUiContent(updatedState, loginViewModel)
        }

        onEmptyState {
        }
        onErrorState { _ ->
            // ScreeUiContent(updatedState, loginViewModel)
        }
        onSuccessState {
            appNavigator.navigate(HomeDestination.createHome(it.toJson(), it.userName, 24))
        }
    }
}

@Composable
fun ScreeUiContent(loginViewState: LoginState, loginViewModel: LoginViewModel) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            CustomTextField(
                label = stringResource(id = R.string.username),
                value = loginViewState.userName,
                errorText = stringResource(id = loginViewState.userNameError.getErrorMessage()),
                showError = loginViewState.showUserNameError(),
            ) { userName ->
                loginViewModel.setAction(LoginInput.UserNameUpdated(userName))
            }
            Spacer(modifier = Modifier.height(16.dp))
            CustomTextField(
                label = stringResource(id = R.string.password),
                value = loginViewState.password,
                errorText = stringResource(id = loginViewState.passwordError.getErrorMessage()),
                showError = loginViewState.showPasswordError(),
            ) { password ->
                loginViewModel.setAction(LoginInput.PasswordUpdated(password))
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { loginViewModel.setAction(LoginInput.LoginClicked) },
            ) {
                Text(text = "Login")
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(onClick = { loginViewModel.setAction(LoginInput.RegisterClicked) }) {
                Text(text = "Sign up Now!")
            }
        }
    }
}

@Composable
fun CustomTextField(
    label: String,
    value: String,
    showError: Boolean,
    errorText: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onChanged: (String) -> Unit,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onChanged(it) },
        label = { Text(text = label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        isError = showError,
        visualTransformation = visualTransformation,
    )
    if (showError) {
        Text(
            text = errorText,
            color = Color.Red,
            modifier = Modifier.padding(all = 8.dp),
        )
    }
}

package com.example.login.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.login.R
import com.example.login.presentation.protocols.LoginInput
import com.example.login.presentation.protocols.LoginOutput
import com.example.login.presentation.viewmodel.LoginViewModel

@Composable
fun LoginView(viewModel: LoginViewModel) {
    LaunchedEffect(viewModel) {
        viewModel.event.collect { event ->
            when (event) {
                is LoginOutput.NavigateToMain -> TODO()
                is LoginOutput.NavigateToRegister -> TODO()
                is LoginOutput.ShowError -> TODO()
                is LoginOutput.ShowLoading -> TODO()
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,

        ) {
            OutlinedTextFieldComponent(
                value = viewModel.loginViewState.userName,
                onValueChange = { viewModel.setAction(LoginInput.UserNameUpdated(it)) },
                label = R.string.username,
                errorMessage = viewModel.loginViewState.userNameError.getErrorMessage(),
                showError = viewModel.loginViewState.showUserNameError(),
                visualTransformation = VisualTransformation.None,
            )

            OutlinedTextFieldComponent(
                value = viewModel.loginViewState.password,
                onValueChange = { viewModel.setAction(LoginInput.PasswordUpdated(it)) },
                label = R.string.password,
                errorMessage = viewModel.loginViewState.passwordError.getErrorMessage(),
                showError = viewModel.loginViewState.showPasswordError(),
                visualTransformation = VisualTransformation.None,
            )

            Button(
                onClick = { viewModel.login() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp, horizontal = 12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue,
                ),

            ) {
                Text(text = stringResource(R.string.login))
            }

            TextButton(
                onClick = { viewModel.setAction(LoginInput.RegisterClicked) },
            ) {
                Text(stringResource(R.string.register))
            }
        }
    }
}

@Composable
fun OutlinedTextFieldComponent(
    label: Int,
    value: String,
    errorMessage: Int,
    showError: Boolean,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(stringResource(label)) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 12.dp),
        isError = showError,
        visualTransformation = visualTransformation,
    )
    if (showError
    ) {
        Text(
            text = stringResource(errorMessage),
            color = Color.Red,
        )
    }
}

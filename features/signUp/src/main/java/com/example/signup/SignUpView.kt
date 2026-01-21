package com.example.signup

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp


@Composable
fun SignUpView() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            CustomTextField(
                label = "Username",
                value = username,
                errorText = "Error",
                showError = false,
            ) { value -> username = value }
            Spacer(modifier = Modifier.height(16.dp))
            CustomTextField(
                label = "Password",
                value = password,
                errorText = "Error",
                showError = false,
            ) { value -> password = value }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { },
            ) {
                Text(text = "Login")
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(onClick = { }) {
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
    if (showError){
        Text(
            text = errorText,)
    }

}

package com.example.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.domain.model.toUser
import com.example.navigator.destinitions.USER
import com.example.navigator.destinitions.USER_NAME

@Composable
fun HomeScreenView(navController: NavHostController) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val user = backStackEntry?.arguments?.getString(USER)
    val userName = backStackEntry?.arguments?.getString(USER_NAME)
    val userObject = user?.toUser()

    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(text = "Hello $userName")

                Spacer(Modifier.height(10.dp))
                Text(text = "Email : ${userObject?.email}")
                Spacer(Modifier.height(10.dp))
                Text(text = "Access Token :  ${userObject?.accessToken}")
            }
        },
    )
}

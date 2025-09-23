package me.bodnarsg.jwtsample.presentation.auth.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun RegistrationView(
    viewModel: RegistrationViewModel = hiltViewModel()
) {
    val username by viewModel.username
    val password by viewModel.password
    val secondaryPassword by viewModel.secondaryPassword
    val name by viewModel.name


    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            TextField(
                value = username,
                onValueChange = { viewModel.setUsername(it) },
                label = { Text(text = "Username") }
            )
            TextField(
                value = password,
                onValueChange = { viewModel.setPassword(it) },
                label = { Text(text = "Password") },
                isError = !viewModel.passwordsMatch()
            )
            TextField(
                value = secondaryPassword,
                onValueChange = { viewModel.setSecondaryPassword(it) },
                label = { Text(text = "Repeat password") },
                isError = !viewModel.passwordsMatch()
            )
            TextField(
                value = name,
                onValueChange = { viewModel.setName(it) },
                label = { Text(text = "Name") }
            )
            Button(
                onClick = {
                    viewModel.registration()
                }
            ) {
                Text(text = "Register")
            }
        }
    }
}
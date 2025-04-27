package com.example.agricare.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction



@Composable
fun SignUpScreen(
    onSignUpSuccess: () -> Unit,
    onSwitchAuthMode: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = "Sign Up",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Full Name
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Password
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Sign Up Button
        Button(
            onClick = {
                // Validation or API call logic here
                onSignUpSuccess()  // <- THIS IS CALLED
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Sign Up")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Switch to Login Button
        TextButton(
            onClick = { onSwitchAuthMode() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Already have an account? Login")
        }
    }
}

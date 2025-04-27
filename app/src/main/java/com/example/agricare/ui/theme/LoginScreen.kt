package com.example.agricare.ui.theme

import android.util.Patterns
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.agricare.ui.theme.navigation.Screen

@Composable
fun LoginScreen(
    navController: NavController,
    onLoginSuccess: () -> Unit,    // callback for successful login
    onSwitchAuthMode: () -> Unit    // callback for switching between login/signup
) {
    var email by remember { mutableStateOf(TextFieldValue()) }
    var password by remember { mutableStateOf(TextFieldValue()) }
    var errorMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    // Validation
    fun validateInputs(): Boolean {
        return when {
            email.text.isEmpty() -> {
                errorMessage = "Email cannot be empty"
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(email.text).matches() -> {
                errorMessage = "Please enter a valid email"
                false
            }
            password.text.isEmpty() -> {
                errorMessage = "Password cannot be empty"
                false
            }
            password.text.length < 6 -> {
                errorMessage = "Password should be at least 6 characters long"
                false
            }
            else -> {
                errorMessage = ""
                true
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Login",
            fontSize = 32.sp,
            color = Color(0xFF2E7D32),
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Email Input
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Password Input
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Show error
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red, modifier = Modifier.padding(bottom = 8.dp))
        }

        // Login Button
        Button(
            onClick = {
                try {
                    if (validateInputs()) {
                        isLoading = true
                        Log.d("LoginScreen", "Login successful with email: ${email.text}")

                        onLoginSuccess() // <--- Call back to MainActivity
                    }
                } catch (e: Exception) {
                    Log.e("LoginScreen", "Error during login", e)
                    errorMessage = "An error occurred. Please try again."
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            Text("Login")
        }

        if (isLoading) {
            Spacer(modifier = Modifier.height(16.dp))
            CircularProgressIndicator()
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Switch to Signup
        TextButton(
            onClick = onSwitchAuthMode
        ) {
            Text("Don't have an account? Sign Up")
        }
    }
}

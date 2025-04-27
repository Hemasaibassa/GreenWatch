package com.example.agricare.ui.theme

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.agricare.R
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.agricare.ui.theme.navigation.Screen


data class User(
    val name: String,
    val email: String,
    val language: String,
    val phone: String,
    val profilePicture: String? = null // URL or local path to the image
)

@Composable
fun ProfilePage(user: User, navController: NavController, onProfilePicChange: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF1F8E9)), // Soft green
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Profile Picture
                Image(
                    painter = rememberAsyncImagePainter(user.profilePicture ?: R.drawable.profile_placeholder),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // User Info
                Text(user.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(user.language, fontSize = 16.sp, color = Color.Gray)

                Spacer(modifier = Modifier.height(20.dp))

                // Profile Info (email, phone)
                ProfileItem(label = "Email", value = user.email)
                ProfileItem(label = "Phone", value = user.phone)

                Spacer(modifier = Modifier.height(32.dp))

                // Change Profile Picture Button
                Button(
                    onClick = onProfilePicChange,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Change Profile Picture")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Logout Button
                Button(
                    onClick = {
                        // Clear back stack and navigate to the login screen
                        navController.navigate(Screen.LoginScreen.route) {
                            // Clearing back stack to avoid returning to ProfilePage after logout
                            popUpTo(Screen.ProfilePage.route) { inclusive = true }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Logout", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun ProfileItem(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(text = "$label:", fontSize = 14.sp, color = Color.Gray)
        Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun EditPhoneNumberScreen(user: User, onSave: (String) -> Unit) {
    var phone by remember { mutableStateOf(user.phone) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Edit Phone Number", fontSize = 24.sp)

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { onSave(phone) }) {
            Text("Save")
        }
    }
}

@Composable
fun ProfileScreenWithEdit(user: User, navController: NavController) {
    var showPhoneEdit by remember { mutableStateOf(false) }

    ProfilePage(
        user = user,
        navController = navController,
        onProfilePicChange = {
            // Trigger image picker for changing the profile picture
            Log.d("ProfileScreen", "Change Profile Picture")
        }
    )

    // Show Edit Phone Number screen if required
    if (showPhoneEdit) {
        EditPhoneNumberScreen(user = user, onSave = { newPhone ->
            // Handle saving new phone number
            Log.d("ProfileScreen", "Updated Phone: $newPhone")
            showPhoneEdit = false
        })
    }
}

@Composable
fun ProfilePagePreview() {
    ProfilePage(
        user = User(
            name = "Ashwini",
            email = "ashwini@example.com",
            language = "English",
            phone = "+91 98765 43210",
            profilePicture = "https://example.com/profile.jpg"
        ),
        navController = rememberNavController(),
        onProfilePicChange = { /* Implement image picker logic */ }
    )
}

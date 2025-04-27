package com.example.agricare.ui.theme

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun WeatherAndImageUploadScreen(
    cropName: String,  // Crop name passed as a parameter
    weatherData: String,  // Weather data passed as a parameter
    soilType: String,  // Soil type passed as a parameter
    selectedImageUri: Uri?,  // Image URI passed as a parameter
    onImageSelected: (Uri) -> Unit, // Callback for image selection
    onUploadImage: () -> Unit, // Callback for uploading image
    onNavigateToHome: () -> Unit,  // Callback for Home navigation
    onNavigateToProfilePage: () -> Unit,  // Callback for Profile navigation
    onNavigateToCropDetails: () -> Unit,  // Callback for CropDetails navigation
    onNavigateToResources: () -> Unit,  // Callback for Resources navigation
    onNavigateToDiseasePrediction: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(Color(0xFFFAF2E5)),  // Soft beige background color
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Header section with title and weather information
        Text(
            text = "Weather Analysis for $cropName",
            style = MaterialTheme.typography.headlineSmall,
            color = Color(0xFF4CAF50),  // Vibrant green
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Current Weather: $weatherData",
            fontSize = 18.sp,
            color = Color(0xFF4CAF50),  // Vibrant green
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Display the soil type
        Text(
            text = "Soil Type: $soilType",
            fontSize = 16.sp,
            color = Color(0xFF4CAF50),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Image Upload Section with a fun, engaging button
        Text(
            text = "Upload Infected Leaf Image",
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF4CAF50),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Upload button with a rounded shape and shadow
        Button(
            onClick = { onUploadImage() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 20.dp),
            shape = RoundedCornerShape(8.dp),  // Rounded button shape
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))  // Vibrant green button color
        ) {
            Icon(
                imageVector = Icons.Filled.Image,
                contentDescription = "Upload Image",
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Select Image", color = Color.White)
        }

        // Spacer to provide spacing between the button and the image preview
        Spacer(modifier = Modifier.height(20.dp))

        // Display selected image (if any)
        selectedImageUri?.let {
            val context = LocalContext.current
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(it)
                    .crossfade(true)
                    .build(),
                contentDescription = "Uploaded Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(bottom = 16.dp)
            )
        }

        // Spacer to add space at the bottom before the navigation buttons
        Spacer(modifier = Modifier.weight(1f))

        // Bottom Navigation Section with buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BottomNavigationButton("Home", onNavigateToHome)
            BottomNavigationButton("Profile", onNavigateToProfilePage)
            BottomNavigationButton("Crop Details", onNavigateToCropDetails)
            BottomNavigationButton("Resources", onNavigateToResources)
        }
    }
}

@Composable
fun BottomNavigationButton(label: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()  // Equal width for all buttons
            .height(56.dp)
            .padding(horizontal = 4.dp), // Small space between buttons
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
    ) {
        Text(label, color = Color.White, fontSize = 12.sp) // Slightly smaller font to fit
    }
}


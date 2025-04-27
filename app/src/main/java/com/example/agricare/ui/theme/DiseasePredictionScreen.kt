package com.example.agricare.ui.theme

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.delay
import androidx.compose.ui.platform.LocalContext

data class DiseaseInfo(
    val diseaseName: String,
    val description: String,
    val diseaseImages: List<Uri> // Images related to the disease
)

@Composable
fun DiseasePredictionScreen(
    cropName: String,
    soilType: String,
    weatherData: String,
    imageUri: Uri?,
    diseaseInfo: DiseaseInfo?,  // This holds the disease information
    onNavigateToPesticides: () -> Unit
) {
    var loading by remember { mutableStateOf(false) }

    // Simulate network call for disease prediction
    LaunchedEffect(Unit) {
        loading = true
        // Simulate network delay
        delay(2000)
        loading = false
    }

    // Layout for the Disease Prediction Screen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(Color(0xFFFAF2E5)), // Soft beige background
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Header section with disease prediction title
        Text(
            text = "Disease Prediction",
            style = MaterialTheme.typography.headlineSmall,
            color = Color(0xFF4CAF50),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Display crop and soil information (new additions)
        Text(
            text = "Crop: $cropName",
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Soil Type: $soilType",
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Weather: $weatherData",
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Show loading text if data is being fetched
        if (loading) {
            CircularProgressIndicator(color = Color(0xFF4CAF50), modifier = Modifier.padding(bottom = 16.dp))
            Text(text = "Fetching disease prediction...", fontSize = 16.sp, color = Color.Black)
        } else {
            // Display disease information (if available)
            diseaseInfo?.let { info ->
                // Display disease name
                Text(
                    text = "Disease: ${info.diseaseName}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4CAF50),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Display disease description
                Text(
                    text = info.description,
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Display disease-related images (if any)
                if (info.diseaseImages.isNotEmpty()) {
                    Text(
                        text = "Symptoms of the detected disease:",
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Display each disease-related image
                    info.diseaseImages.forEach { uri ->
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(uri)
                                .crossfade(true)
                                .build(),
                            contentDescription = "Disease Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(bottom = 16.dp)
                        )
                    }
                }
            } ?: run {
                // In case no disease information is available
                Text(
                    text = "No disease detected yet.",
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 32.dp)
                )
            }

            // Optionally show the uploaded image (imageUri)
            imageUri?.let {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
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

            // Spacer
            Spacer(modifier = Modifier.height(32.dp))

            // Button to navigate to the Pesticides Page
            Button(
                onClick = onNavigateToPesticides,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 20.dp)
                    .background(Color(0xFF4CAF50)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Warning,
                    contentDescription = "Navigate to Pesticides Page",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Next: Get Pesticides", color = Color.White)
            }
        }
    }
}

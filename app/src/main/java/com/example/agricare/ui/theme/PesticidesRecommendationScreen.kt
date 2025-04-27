package com.example.agricare.ui.theme

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.delay
import androidx.core.net.toUri

@Composable
fun PesticidesRecommendationScreen(
    diseaseName: String,
    chemicalPesticideImageUri: Uri?,
    organicPesticideImageUri: Uri?,
    onNavigateToPurchase: () -> Unit
) {
    // Dummy recommendations with loading simulation
    var chemicalPesticide by remember { mutableStateOf("Loading...") }
    var organicPesticide by remember { mutableStateOf("Loading...") }
    var loading by remember { mutableStateOf(true) }

    // Simulate a network call to fetch pesticide recommendations
    LaunchedEffect(Unit) {
        delay(2000) // Simulate network delay
        chemicalPesticide = "Fungicide: X-1000 for Anthracnose"
        organicPesticide = "Neem Oil Solution for Organic Control"
        loading = false
    }

    // Layout for the Pesticides Recommendation Screen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(Color(0xFFF1F8E9)), // Soft green background
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Header Section
        Text(
            text = "Pesticides Recommendations for $diseaseName",
            style = MaterialTheme.typography.headlineSmall,
            color = Color(0xFF4CAF50),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Show loading indicator
        if (loading) {
            CircularProgressIndicator(color = Color(0xFF4CAF50), modifier = Modifier.padding(bottom = 16.dp))
            Text(text = "Fetching pesticide recommendations...", fontSize = 16.sp, color = Color.Black)
        } else {
            // Chemical Pesticide Recommendation
            Text(
                text = "Chemical Pesticide Recommendation:",
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = chemicalPesticide,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Display Chemical Pesticide Image (if any)
            chemicalPesticideImageUri?.let {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(it) // Load image from Uri
                        .crossfade(true)
                        .build(),
                    contentDescription = "Chemical Pesticide Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(bottom = 16.dp)
                )
            }

            // Organic Pesticide Recommendation
            Text(
                text = "Organic Pesticide Recommendation:",
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = organicPesticide,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Display Organic Pesticide Image (if any)
            organicPesticideImageUri?.let {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(it) // Load image from Uri
                        .crossfade(true)
                        .build(),
                    contentDescription = "Organic Pesticide Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(bottom = 32.dp)
                )
            }
        }

        // Button to navigate to the purchase page
        Button(
            onClick = onNavigateToPurchase,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 20.dp)
                .background(Color(0xFF4CAF50)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.ShoppingCart,
                contentDescription = "Navigate to Purchase",
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Buy Pesticides", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PesticidesRecommendationScreenPreview() {
    // Sample URIs for pesticide images (Replace with actual URIs)
    val chemicalPesticideImageUri =
        "/Users/ashwinipawar/AndroidStudioProjects/AgriCare/app/src/main/res/drawable/chemical.jpg".toUri() // Example URI
    val organicPesticideImageUri =
        "/Users/ashwinipawar/AndroidStudioProjects/AgriCare/app/src/main/res/drawable/organic.jpg".toUri() // Example URI

    PesticidesRecommendationScreen(
        diseaseName = "Anthracnose",
        chemicalPesticideImageUri = chemicalPesticideImageUri,
        organicPesticideImageUri = organicPesticideImageUri,
        onNavigateToPurchase = {}
    )
}

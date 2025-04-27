package com.example.agricare.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.grid.*

@Composable
fun CropSelectionScreen(onCropSelected: (String) -> Unit) {
    var selectedCrop by remember { mutableStateOf("") }

    // List of all crops
    val crops = listOf("Mango", "Banana", "Pomegranate", "Guava", "Orange",
        "Tomato", "Bell Pepper", "Potato", "Onion", "Cucumber")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Title
        Text(
            text = "Choose the crop you want",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Crop selection buttons in a grid format
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 100.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(crops) { crop ->
                Button(
                    onClick = {
                        selectedCrop = crop
                        onCropSelected(crop)  // Notify parent composable of selected crop
                    },
                    shape = CircleShape, // Circular button shape
                    modifier = Modifier
                        .padding(8.dp)
                        .size(100.dp) // Size of the circle
                        .background(Color(0xFFE0E0E0), CircleShape), // Gray background
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary, // Use theme primary color
                    )
                ) {
                    Text(
                        text = crop,
                        color = Color.White, // White text for better contrast
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(10.dp) // Padding inside the button
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display the selected crop name
        Text(
            text = if (selectedCrop.isNotEmpty()) "Selected: $selectedCrop" else "No crop selected",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCropSelectionScreen() {
    // Provide an empty callback for preview
    CropSelectionScreen(onCropSelected = { })
}

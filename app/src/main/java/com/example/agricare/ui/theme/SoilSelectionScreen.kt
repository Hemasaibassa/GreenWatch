
package com.example.agricare.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SoilSelectionScreen(cropName: String, language: String, onSoilSelected: (String) -> Unit) {
    val soilTypes = listOf("Loamy", "Sandy", "Clayey", "Peaty", "Saline")

    var selectedSoil by remember { mutableStateOf("") }  // Store selected soil type

    // Localized text based on language
    val titleText = when (language) {
        "Hindi" -> "मिट्टी का प्रकार चुनें"
        "Marathi" -> "मातीचा प्रकार निवडा"
        "Telugu" -> "భూమి రకం ఎంచుకోండి"
        else -> "Select Soil Type for $cropName"  // Default to English
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = titleText,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Display each soil type in a button
        soilTypes.forEach { soil ->
            val isSelected = selectedSoil == soil
            Button(
                onClick = {
                    selectedSoil = soil
                    onSoilSelected(soil)  // Send selected soil to parent
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(
                        if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                    ), // Highlight selected button with primary color, otherwise secondary
                shape = MaterialTheme.shapes.medium,  // Rounded button shape
                contentPadding = PaddingValues(vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                )
            ) {
                Text(
                    text = soil,
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (isSelected) Color.White else Color.Black  // White text on selected button
                )
            }
        }
    }
}

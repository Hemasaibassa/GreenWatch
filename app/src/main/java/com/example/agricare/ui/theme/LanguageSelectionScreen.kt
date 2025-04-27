package com.example.agricare.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.agricare.ui.theme.AgricareTheme
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.foundation.interaction.MutableInteractionSource


@Composable
fun LanguageSelectionScreen(onLanguageSelected: (String) -> Unit) {
    var selectedLanguage by remember { mutableStateOf("Select Language") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp), // Increased padding for a cleaner layout
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Select your Language",
            fontSize = 28.sp, // Slightly larger font for heading
            fontWeight = FontWeight.Bold, // Bold for better emphasis
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(40.dp)) // Adjusted space

        // Language Buttons with rounded corners and custom colors
        LanguageButton("English", selectedLanguage, onLanguageSelected)
        LanguageButton("Hindi", selectedLanguage, onLanguageSelected)
        LanguageButton("Marathi", selectedLanguage, onLanguageSelected)
        LanguageButton("Telugu", selectedLanguage, onLanguageSelected)

        Spacer(modifier = Modifier.height(30.dp)) // Adjusted space

        // Display selected language
        Text(
            text = "Selected Language: $selectedLanguage",
            fontSize = 20.sp,
            color = Color.Gray, // Lighter color for better readability
            fontStyle = FontStyle.Italic
        )
    }
}

@Composable
fun LanguageButton(
    language: String,
    selectedLanguage: String,
    onLanguageSelected: (String) -> Unit
) {
    val isSelected = language == selectedLanguage
    val interactionSource = remember { MutableInteractionSource() } // Interaction source for ripple effect

    Button(
        onClick = {
            onLanguageSelected(language)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp) // Make buttons wider for better touch targets
            .height(48.dp), // Consistent height for buttons
        shape = CircleShape, // Rounded corners for a cleaner look
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isSelected) Color(0xFF2E7D32) else Color(0xFF388E3C) // Highlight selected button
        ),
        interactionSource = interactionSource // Handle ripple with interaction source
    ) {
        Text(
            text = language,
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.Medium
        )
    }

    Spacer(modifier = Modifier.height(15.dp)) // Adjusted space between buttons
}



@Preview(showBackground = true)
@Composable
fun PreviewLanguageSelectionScreen() {
    AgricareTheme {
        LanguageSelectionScreen(onLanguageSelected = {})
    }
}

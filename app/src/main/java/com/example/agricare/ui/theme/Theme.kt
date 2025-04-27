package com.example.agricare.ui.theme

import androidx.compose.material3.*  // Use this import for Material3
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

// Define colors for the light theme
private val LightColorPalette = lightColorScheme(
    primary = Color(0xFF4CAF50),
    secondary = Color(0xFF81C784),
    background = Color(0xFFF1F8E9),
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)

// Define the Typography for Material3
private val customTypography = Typography(
    bodyLarge = TextStyle(
        fontSize = 16.sp,
        color = Color.Black
    ),
    titleLarge = TextStyle(
        fontSize = 30.sp,
        color = Color(0xFF388E3C)
    ),
    displayMedium = TextStyle(
        fontSize = 24.sp,
        color = Color.Black
    )
)

@Composable
fun AgricareTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorPalette,  // You can switch to DarkColorPalette if needed
        typography = customTypography,    // Use custom typography
        content = content
    )
}

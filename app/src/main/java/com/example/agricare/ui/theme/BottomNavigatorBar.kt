package com.example.agricare.ui.theme

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.agricare.MainActivity.AppScreen

@Composable
fun BottomNavigationBar(
    onTabSelected: (AppScreen) -> Unit,
    currentScreen: AppScreen
) {
    NavigationBar(
        modifier = Modifier.height(60.dp),
        containerColor = Color(0xFF4CAF50)
    ) {
        NavigationBarItem(
            selected = currentScreen == AppScreen.ProfilePage,
            onClick = { onTabSelected(AppScreen.ProfilePage) },
            label = { Text("Profile") },
            icon = { Icon(Icons.Filled.AccountCircle, contentDescription = null) }
        )
        NavigationBarItem(
            selected = currentScreen == AppScreen.CropDetails,
            onClick = { onTabSelected(AppScreen.CropDetails) },
            label = { Text("Crop Details") },
            icon = { Icon(Icons.Filled.Spa, contentDescription = null) }
        )
        NavigationBarItem(
            selected = currentScreen == AppScreen.DiseasePrediction,
            onClick = { onTabSelected(AppScreen.DiseasePrediction) },
            label = { Text("Disease Prediction") },
            icon = { Icon(Icons.Filled.Warning, contentDescription = null) }
        )
        NavigationBarItem(
            selected = currentScreen == AppScreen.PesticideRecommendation,
            onClick = { onTabSelected(AppScreen.PesticideRecommendation) },
            label = { Text("Pesticides") },
            icon = { Icon(Icons.Filled.ShoppingCart, contentDescription = null) }
        )
    }
}

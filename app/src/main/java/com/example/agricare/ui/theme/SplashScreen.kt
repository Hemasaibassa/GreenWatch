package com.example.agricare.ui.theme

import android.os.Bundle
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Eco
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.alpha

@Composable
fun SplashScreen(onNavigateNext: () -> Unit) {
    val alpha = remember { Animatable(0f) }
    val scale = remember { Animatable(0.8f) }

    LaunchedEffect(Unit) {
        // Animate the text and icon with fade-in and scale-up effects
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1500)
        )
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1500)
        )
        delay(1000) // Delay for a smoother transition
        onNavigateNext() // Navigate after the splash screen
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF388E3C)), // Green background for more earth-tone look
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Eco,
                    contentDescription = "Logo",
                    tint = Color(0xFF388E3C), // Match with background color
                    modifier = Modifier
                        .size(64.dp)
                        .graphicsLayer { scaleX = scale.value; scaleY = scale.value } // Animate logo scale
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "AgriCare",
                fontSize = 32.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.alpha(alpha.value) // Apply fade-in effect
            )
            Text(
                text = "Empowering Farmers, Enriching Fields",
                fontSize = 16.sp,
                color = Color.White,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.alpha(alpha.value) // Apply fade-in effect
            )
        }
    }
}

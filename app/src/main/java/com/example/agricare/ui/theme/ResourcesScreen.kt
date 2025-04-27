package com.example.agricare.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape


@Composable
fun ResourcesScreen(
    onNavigateBack: (() -> Unit)? = null
) {
    val resourcesList = listOf(
        "Crop Care Guide",
        "Pest Management Tips",
        "Soil Health Improvement",
        "Weather Impact on Crops",
        "Latest Farming Techniques",
        "Organic Farming Resources"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Resources",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4CAF50),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(resourcesList) { resource ->
                ResourceCard(title = resource)
            }
        }

        // Show back button only if onNavigateBack is provided
        onNavigateBack?.let {
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = onNavigateBack,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF388E3C))
            ) {
                Text("Back", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun ResourceCard(title: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F8E9)),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF2E7D32)
            )
        }
    }
}

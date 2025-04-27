package com.example.agricare.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.agricare.R

@Composable
fun CropDetailsScreen(
    cropName: String,
    onNavigateBack: () -> Unit
) {
    val details = listOf(
        "Disease Detected" to "Anthracnose",
        "Spray Timing" to "7:00 AM - 9:00 AM",
        "Weather Alert" to "Thunderstorm expected in 2 hours",
        "Pesticide Timing" to "Every 15 days",
        "Soil Health" to "Soil pH level: 6.5 (Optimal)"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFF1F8E9)) // Soft green background
    ) {
        // Crop Image
        Image(
            painter = painterResource(id = R.drawable.crop_image),
            contentDescription = "Crop Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(16.dp))
                .shadow(8.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "$cropName Crop Report",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2E7D32),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(24.dp))

        details.forEach { (title, content) ->
            DetailCard(title, content)
            Spacer(modifier = Modifier.height(16.dp))
        }

        Spacer(modifier = Modifier.weight(1f))

        // Back Button
        Button(
            onClick = onNavigateBack,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF388E3C))
        ) {
            Text("Back", color = Color.White, fontSize = 16.sp)
        }
    }
}

@Composable
fun DetailCard(title: String, content: String) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF2E7D32)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = content,
                fontSize = 16.sp,
                color = Color.DarkGray
            )
        }
    }
}

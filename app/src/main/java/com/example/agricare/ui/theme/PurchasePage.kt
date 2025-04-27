package com.example.agricare.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.shadow
import com.example.agricare.R // make sure your R file is properly imported

@Composable
fun PurchasePage(
    pesticideName: String,
    pesticidePrice: String,
    onPurchase: () -> Unit,
    onNavigateBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF1F8E9))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Add an Indian crop/pesticide image
        Image(
            painter = painterResource(id = R.drawable.chemical), // your drawable image
            contentDescription = "Pesticide Image",
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
                .shadow(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "खरीदीचे तपशील (Purchase Details)",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color(0xFF2E7D32)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text("कीटकनाशक (Pesticide): $pesticideName", fontSize = 18.sp, color = Color.DarkGray)
                Spacer(modifier = Modifier.height(8.dp))
                Text("किंमत (Price): ₹$pesticidePrice", fontSize = 18.sp, color = Color.DarkGray)
            }
        }

        Button(
            onClick = onPurchase,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF388E3C))
        ) {
            Icon(
                imageVector = Icons.Filled.ShoppingCart,
                contentDescription = "Buy Pesticide",
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("खरेदी करा (Buy Now)", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = onNavigateBack,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF388E3C))
        ) {
            Text("मागे जा (Back)")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PurchasePagePreview() {
    PurchasePage(
        pesticideName = "Fungicide: Mancozeb 75% WP", // Real Indian pesticide name
        pesticidePrice = "450.00", // ₹ in rupees
        onPurchase = {},
        onNavigateBack = {}
    )
}

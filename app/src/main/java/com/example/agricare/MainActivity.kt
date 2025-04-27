package com.example.agricare

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.agricare.ui.theme.*
import com.example.agricare.ui.theme.BottomNavigationBar

class MainActivity : ComponentActivity() {

    private var selectedCrop by mutableStateOf("")
    private var selectedLanguage by mutableStateOf("")
    private var selectedSoilType by mutableStateOf("")
    private var selectedImageUri by mutableStateOf<Uri?>(null)
    private var predictedDiseaseInfo by mutableStateOf<DiseaseInfo?>(null)
    private var hasPurchased by mutableStateOf(false)

    enum class AppScreen {
        Splash,
        LanguageSelection,
        SignUp,
        CropSelection,
        SoilSelection,
        WeatherAndImageUpload,
        ProfilePage,
        CropDetails,
        Resources,
        DiseasePrediction,
        PesticideRecommendation,
        Purchase,
        Login
    }

    private var currentScreen by mutableStateOf(AppScreen.Splash)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AgricareTheme {
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        if (currentScreen in listOf(
                                AppScreen.ProfilePage,
                                AppScreen.CropDetails,
                                AppScreen.DiseasePrediction,
                                AppScreen.PesticideRecommendation
                            )
                        ) {
                            BottomNavigationBar(
                                onTabSelected = { selectedScreen ->
                                    currentScreen = selectedScreen
                                },
                                currentScreen = currentScreen
                            )
                        }
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        when (currentScreen) {
                            AppScreen.Splash -> SplashScreen(
                                onNavigateNext = { currentScreen = AppScreen.LanguageSelection }
                            )

                            AppScreen.LanguageSelection -> LanguageSelectionScreen(
                                onLanguageSelected = { language ->
                                    selectedLanguage = language
                                    currentScreen = AppScreen.SignUp
                                }
                            )

                            AppScreen.SignUp -> SignUpScreen(

                                onSignUpSuccess = {
                                    currentScreen = AppScreen.CropSelection
                                },
                                onSwitchAuthMode = {
                                    currentScreen = AppScreen.Login
                                }
                            )

                            AppScreen.Login -> LoginScreen(
                                navController = navController,
                                onLoginSuccess = {
                                    currentScreen = AppScreen.ProfilePage
                                },
                                onSwitchAuthMode = {
                                    currentScreen = AppScreen.SignUp
                                }
                            )

                            AppScreen.CropSelection -> CropSelectionScreen(
                                onCropSelected = { crop ->
                                    selectedCrop = crop
                                    currentScreen = AppScreen.SoilSelection
                                }
                            )

                            AppScreen.SoilSelection -> SoilSelectionScreen(
                                cropName = selectedCrop,
                                language = selectedLanguage,
                                onSoilSelected = { soilType ->
                                    selectedSoilType = soilType
                                    currentScreen = AppScreen.WeatherAndImageUpload
                                }
                            )

                            AppScreen.WeatherAndImageUpload -> WeatherAndImageUploadScreen(
                                cropName = selectedCrop,
                                weatherData = "Sunny, 30°C",
                                soilType = selectedSoilType,
                                selectedImageUri = selectedImageUri,
                                onImageSelected = { uri -> selectedImageUri = uri },
                                onUploadImage = {
                                    predictedDiseaseInfo = DiseaseInfo(
                                        diseaseName = "Leaf Blight",
                                        description = "A fungal disease causing dark lesions.",
                                        diseaseImages = listOf()
                                    )
                                    currentScreen = AppScreen.DiseasePrediction
                                },
                                onNavigateToHome = { currentScreen = AppScreen.WeatherAndImageUpload },
                                onNavigateToProfilePage = { currentScreen = AppScreen.ProfilePage },
                                onNavigateToCropDetails = { currentScreen = AppScreen.CropDetails },
                                onNavigateToResources = { currentScreen = AppScreen.Resources },
                                onNavigateToDiseasePrediction = { currentScreen = AppScreen.DiseasePrediction }
                            )

                            AppScreen.ProfilePage -> ProfileScreenWithEdit(
                                user = User(
                                    name = "Ashwini",
                                    email = "ashwini@example.com",
                                    language = selectedLanguage,
                                    phone = "+91 98765 43210",
                                    profilePicture = null
                                ),
                                navController = navController
                            )

                            AppScreen.CropDetails -> CropDetailsScreen(
                                cropName = selectedCrop,
                                onNavigateBack = { currentScreen = AppScreen.WeatherAndImageUpload }
                            )

                            AppScreen.Resources -> ResourcesScreen(
                                onNavigateBack = { currentScreen = AppScreen.WeatherAndImageUpload }
                            )

                            AppScreen.DiseasePrediction -> DiseasePredictionScreen(
                                cropName = selectedCrop,
                                soilType = selectedSoilType,
                                weatherData = "Sunny, 30°C",
                                imageUri = selectedImageUri,
                                diseaseInfo = predictedDiseaseInfo,
                                onNavigateToPesticides = {
                                    currentScreen = AppScreen.PesticideRecommendation
                                }
                            )

                            AppScreen.PesticideRecommendation -> PesticidesRecommendationScreen(
                                diseaseName = predictedDiseaseInfo?.diseaseName ?: "",
                                chemicalPesticideImageUri = null,
                                organicPesticideImageUri = null,
                                onNavigateToPurchase = {
                                    currentScreen = AppScreen.Purchase
                                }
                            )

                            AppScreen.Purchase -> PurchasePage(
                                pesticideName = "Fungicide: X-1000",
                                pesticidePrice = "450.00",
                                onPurchase = { hasPurchased = true },
                                onNavigateBack = {
                                    hasPurchased = false
                                    currentScreen = AppScreen.PesticideRecommendation
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

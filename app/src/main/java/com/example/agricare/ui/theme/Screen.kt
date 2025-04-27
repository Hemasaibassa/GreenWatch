package com.example.agricare.ui.theme.navigation

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object LanguageSelectionScreen : Screen("language_selection_screen")
    object SignUpScreen : Screen("sign_up_screen")
    object LoginScreen : Screen("login_screen")
    object CropSelectionScreen : Screen("crop_selection_screen")
    object SoilSelectionScreen : Screen("soil_selection_screen")
    object WeatherAndImageScreen : Screen("weather_and_image_screen")
    object DiseasePredictionScreen : Screen("disease_prediction_screen")
    object PesticidesRecommendationScreen : Screen("pesticides_recommendation_screen")
    object PurchasePage : Screen("purchase_page")
    object OrderSuccessScreen : Screen("order_success_screen")
    object ProfilePage : Screen("profile_page")
    object CropDetailsScreen : Screen("crop_details_screen")
    object ResourcesScreen : Screen("resources_screen")
}

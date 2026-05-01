package com.bousmah.meteoapp_zayd.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.*

@Composable
fun WeatherAnimation(
    iconCode: String,
    modifier: Modifier = Modifier
) {
    // Mapping OpenWeather codes to Lottie animations
    // In a real app, you would have these JSONs in res/raw
    val composition by rememberLottieComposition(
        LottieCompositionSpec.Url("https://assets9.lottiefiles.com/temp/lf20_dgj83P.json") // Example: Sunny
    )
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever
    )

    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier
    )
}

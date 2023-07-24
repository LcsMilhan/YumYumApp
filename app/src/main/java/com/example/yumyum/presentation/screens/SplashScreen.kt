package com.example.yumyum.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.example.yumyum.R
import com.example.yumyum.presentation.navigation.Screen

@Composable
fun AnimationSplashScreen(
    navController: NavController
) {

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box {
            val composition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(R.raw.splash)
            )
            val splashAnimationState = animateLottieCompositionAsState(
                composition = composition
            )
            LottieConstants.IterateForever
            LottieAnimation(
                modifier = Modifier.width(240.dp),
                composition = composition,
                progress = { splashAnimationState.progress },
                applyOpacityToLayers = true,
                enableMergePaths = true
            )
            NavigateToRegisterScreen(navController, splashAnimationState)
        }
    }

}

@Composable
private fun NavigateToRegisterScreen(
    navController: NavController,
    splashAnimationState: LottieAnimationState
) {

    if (splashAnimationState.isAtEnd && splashAnimationState.isPlaying) {
        navController.popBackStack()
        navController.navigate(Screen.CategoriesScreen.route)
    }

}
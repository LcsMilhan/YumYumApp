package com.example.yumyum.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieAnimationState
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.yumyum.R
import com.example.yumyum.presentation.auth.AuthViewModel
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
            CheckUserBeforeNavigate(navController, splashAnimationState)
        }
    }

}

@Composable
private fun CheckUserBeforeNavigate(
    navController: NavController,
    splashAnimationState: LottieAnimationState
) {
    val viewModel: AuthViewModel = hiltViewModel()

    if (splashAnimationState.isAtEnd && splashAnimationState.isPlaying) {
        if (viewModel.isUserAuthenticated) {
            navController.navigate(Screen.CategoriesScreen.route) {
                navController.popBackStack()
            }
        } else {
            navController.navigate(Screen.AuthScreen.route)
        }

    }

}
package com.example.yumyum.presentation.auth.components

import androidx.compose.runtime.Composable

@Composable
fun AuthContent(
    oneTapSignIn: () -> Unit
) {
    SignInButton(
        onButtonClick = oneTapSignIn
    )
}
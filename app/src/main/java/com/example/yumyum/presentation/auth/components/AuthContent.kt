package com.example.yumyum.presentation.auth.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AuthContent(
    oneTapSignIn: () -> Unit
) {
    SignInButton(
        onButtonClick = oneTapSignIn
    )
}
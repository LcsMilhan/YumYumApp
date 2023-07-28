package com.example.yumyum.presentation.auth.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.yumyum.presentation.HeadingTextComponent
import com.example.yumyum.presentation.ImageLogoComponent

@Composable
fun AuthContent(
    oneTapSignIn: () -> Unit
) {

    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        ImageLogoComponent()
        Spacer(modifier = Modifier.height(100.dp))
        HeadingTextComponent(
            text = "Welcome!",
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(50.dp))
        SignInButton(
            onButtonClick = oneTapSignIn
        )
        Spacer(modifier = Modifier.height(50.dp))
    }

}
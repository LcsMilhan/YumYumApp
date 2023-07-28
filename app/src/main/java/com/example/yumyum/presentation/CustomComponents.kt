package com.example.yumyum.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.yumyum.R

@Composable
fun HeadingTextComponent(
    text: String,
    textAlign: TextAlign,
    modifier: Modifier = Modifier
) {

    Text(
        text = text,
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.headlineLarge,
        textAlign = textAlign,
        modifier = modifier
    )

}

@Composable
fun TextTitleMealInfo(text: String) {

    Text(
        text = text,
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    )

}

@Composable
fun ProgressBar() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator()
    }
}

@Composable
fun ImageLogoComponent() {

    val imageColor = androidx.compose.ui.graphics.ColorFilter.tint(
        MaterialTheme.colorScheme.primary
    )
    Image(
        modifier = Modifier
            .size(150.dp),
        painter = painterResource(R.drawable.logo),
        contentDescription = "app-logo",
        colorFilter = imageColor
    )

}

@Composable
fun TextTitleComponent(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
    )
}


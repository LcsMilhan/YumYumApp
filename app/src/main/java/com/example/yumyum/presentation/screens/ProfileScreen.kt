package com.example.yumyum.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.yumyum.presentation.profile.ProfileViewModel
import com.example.yumyum.presentation.profile.components.ProfileContent
import com.example.yumyum.presentation.profile.components.RevokeAccess
import com.example.yumyum.presentation.profile.components.SignOut

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToAuthScreen: () -> Unit
) {
    Box(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxWidth().padding(15.dp)) {
            ProfileContent(
                photoUrl = viewModel.photoUrl,
                displayName = viewModel.displayName
            )
        }
    }
    SignOut(
        navigateToAuthScreen = { signedOut ->
            if (signedOut) {
                navigateToAuthScreen()
            }
        }
    )
    RevokeAccess(
        navigateToAuthScreen = { accessRevoked ->
            if (accessRevoked) {
                navigateToAuthScreen()
            }
        }
    )
}
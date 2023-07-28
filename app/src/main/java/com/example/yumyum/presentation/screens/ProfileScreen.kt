package com.example.yumyum.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.yumyum.R
import com.example.yumyum.presentation.profile.ProfileViewModel
import com.example.yumyum.presentation.profile.components.ProfileContent
import com.example.yumyum.presentation.profile.components.RevokeAccess
import com.example.yumyum.presentation.profile.components.SignOut

// TODO: CREATE PROFILE SCREEN 
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToAuthScreen: () -> Unit
) {

    Box(
        Modifier
            .fillMaxSize().padding(2.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(15.dp)) {
            ProfileContent()
        }
        Column(
            Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(Modifier.fillMaxWidth()) {
                Icon(
                    painter = painterResource(R.drawable.icon_logout),
                    contentDescription = "sing_out_icon",
                    modifier = Modifier.clickable {
                        viewModel.signOut()
                    }
                )
                Icon(
                    painter = painterResource(R.drawable.visibility_on_icon),
                    contentDescription = "sing_out_icon",
                    modifier = Modifier.clickable {
                        viewModel.revokeAccess()
                    }
                )
            }
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
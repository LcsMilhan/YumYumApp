package com.example.yumyum.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.yumyum.R
import com.example.yumyum.presentation.HeadingTextComponent
import com.example.yumyum.presentation.profile.ProfileViewModel
import com.example.yumyum.presentation.profile.components.ProfileContent
import com.example.yumyum.presentation.profile.components.RevokeAccess
import com.example.yumyum.presentation.profile.components.SignOut


@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navController: NavController,
    navigateToAuthScreen: () -> Unit
) {

    Box(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(Modifier.fillMaxWidth()) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_back),
                    contentDescription = "back_icon",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .padding(start = 5.dp, end = 10.dp, top = 10.dp)
                        .clip(CircleShape)
                        .size(30.dp)
                        .clickable(
                            onClick = {
                                navController.popBackStack()
                            }
                        )
                        .alignByBaseline()
                )
                HeadingTextComponent(
                    text = "My Profile",
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            ProfileContent()
            Spacer(modifier = Modifier.height(100.dp))
            IconButton(
                onClick = { viewModel.signOut() },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.icon_logout),
                        contentDescription = "sign_out_icon",
                        modifier = Modifier.alignByBaseline()
                    )
                    Text(
                        text = "Sign out",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.alignByBaseline()
                    )
                }
            }
            IconButton(
                onClick = { viewModel.revokeAccess() },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.revoke_access_icon),
                        contentDescription = "revoke_access_icon",
                        modifier = Modifier.alignByBaseline()
                    )
                    Text(
                        text = "Revoke access",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.alignByBaseline()
                    )
                }
            }
            Spacer(modifier = Modifier.height(45.dp))
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
package com.example.yumyum.presentation.screens

import android.app.Activity.RESULT_OK
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.yumyum.domain.model.auth.Response
import com.example.yumyum.presentation.HeadingTextComponent
import com.example.yumyum.presentation.ImageLogoComponent
import com.example.yumyum.presentation.ProgressBar
import com.example.yumyum.presentation.TextTitleComponent
import com.example.yumyum.presentation.auth.AuthViewModel
import com.example.yumyum.presentation.auth.components.AuthContent
import com.example.yumyum.presentation.auth.components.OneTapSignIn
import com.example.yumyum.presentation.auth.components.SignInButton
import com.example.yumyum.presentation.auth.components.SignInWithGoogle
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.GoogleAuthProvider.getCredential

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    navigateToProfileScreen: () -> Unit
) {

    Box(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxWidth()) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                ImageLogoComponent()
            }
            Spacer(modifier = Modifier.height(15.dp))
            TextTitleComponent("Welcome!")
            Spacer(modifier = Modifier.height(30.dp))
            AuthContent(
                oneTapSignIn = {
                    viewModel.oneTapSignIn()
                }
            )
        }
    }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            try {
                val credentials =
                    viewModel.oneTapClient.getSignInCredentialFromIntent(result.data)
                val googleIdToken = credentials.googleIdToken
                val googleCredentials = getCredential(googleIdToken, null)
                viewModel.signInWithGoogle(googleCredentials)
            } catch (it: ApiException) {
                print(it)
            }
        }
    }

    fun launch(signInResult: BeginSignInResult) {
        val intent =
            IntentSenderRequest.Builder(signInResult.pendingIntent.intentSender).build()
        launcher.launch(intent)
    }

    OneTapSignIn(
        launch = {
            launch(it)
        }
    )
    SignInWithGoogle(
        navigateToHomeScreen = { signedIn ->
            if (signedIn) {
                navigateToProfileScreen()
            }
        }
    )

//    Box(Modifier.fillMaxSize()) {
//        Column(Modifier.fillMaxWidth()) {
//            Row(
//                Modifier
//                    .fillMaxWidth()
//                    .padding(10.dp),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.Center
//            ) {
//                ImageLogoComponent()
//            }
//            Spacer(modifier = Modifier.height(15.dp))
//            TextTitleComponent("Welcome!")
//            Spacer(modifier = Modifier.height(30.dp))
//            AuthContent(
//                oneTapSignIn = {
//                    viewModel.oneTapSignIn()
//                }
//            )
//        }
//    }
//    OneTapSignIn(
//        launch = {
//            launch(it)
//        }
//    )
//    SignInWithGoogle(
//        navigateToHomeScreen = { signedIn ->
//            if (signedIn) {
//                navigateToProfileScreen()
//            }
//        }
//    )
}

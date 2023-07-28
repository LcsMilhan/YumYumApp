package com.example.yumyum.presentation.auth.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.yumyum.common.Resource
import com.example.yumyum.domain.model.auth.Response
import com.example.yumyum.presentation.ProgressBar
import com.example.yumyum.presentation.auth.AuthViewModel
import com.google.android.gms.auth.api.identity.BeginSignInResult
import kotlinx.coroutines.launch

@Composable
fun OneTapSignIn(
    viewModel: AuthViewModel = hiltViewModel(),
    launch: (result: BeginSignInResult) -> Unit
) {

    when(val oneTapSignInResponse = viewModel.oneTapSignInResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> oneTapSignInResponse.data?.let {
            LaunchedEffect(it) {
                launch(it)
            }
        }
        is Response.Failure -> LaunchedEffect(Unit) {
            print(oneTapSignInResponse.e)
        }
    }

}
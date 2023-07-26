package com.example.yumyum.domain.use_case.auth

import android.content.Intent
import com.example.yumyum.domain.model.auth.SignInResult
import com.example.yumyum.domain.repository.AuthUserRepository
import javax.inject.Inject

class SignInWithIntentUseCase @Inject constructor(
    private val authUserRepository: AuthUserRepository
) {

    suspend operator fun invoke(intent: Intent): SignInResult {
        return authUserRepository.signInWithIntent(intent)
    }
}
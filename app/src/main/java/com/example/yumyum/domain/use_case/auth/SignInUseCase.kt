package com.example.yumyum.domain.use_case.auth

import android.content.IntentSender
import com.example.yumyum.domain.repository.AuthUserRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authUserRepository: AuthUserRepository
) {

    suspend operator fun invoke(): IntentSender? {
        return authUserRepository.signIn()
    }
}
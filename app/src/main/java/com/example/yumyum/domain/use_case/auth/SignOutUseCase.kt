package com.example.yumyum.domain.use_case.auth

import com.example.yumyum.domain.repository.AuthUserRepository
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val authUserRepository: AuthUserRepository
) {

    suspend operator fun invoke() {
        authUserRepository.signOut()
    }
}
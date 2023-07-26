package com.example.yumyum.domain.use_case.auth

import com.example.yumyum.domain.model.auth.UserData
import com.example.yumyum.domain.repository.AuthUserRepository
import javax.inject.Inject

class GetSignedInUserUseCase @Inject constructor(
    private val authUserRepository: AuthUserRepository
) {

    operator fun invoke(): UserData? {
        return authUserRepository.getSignedInUser()
    }
}
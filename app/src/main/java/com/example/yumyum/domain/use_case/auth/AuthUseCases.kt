package com.example.yumyum.domain.use_case.auth

// TODO: CHANGE ALL USE CASES
data class AuthUseCases(
    val signInUseCase: SignInUseCase,
    val signInWithIntentUseCase: SignInWithIntentUseCase,
    val getSignedInUserUseCase: GetSignedInUserUseCase,
    val signOutUseCase: SignOutUseCase
)
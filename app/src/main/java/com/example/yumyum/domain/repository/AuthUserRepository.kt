package com.example.yumyum.domain.repository


import com.example.yumyum.common.Resource
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow


interface AuthUserRepository {

    suspend fun signInWithGoogle(credential: AuthCredential): Flow<Resource<AuthResult>>

    suspend fun signOut()


}
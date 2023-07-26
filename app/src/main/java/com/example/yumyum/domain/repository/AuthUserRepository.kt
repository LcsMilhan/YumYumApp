package com.example.yumyum.domain.repository


import android.content.Intent
import android.content.IntentSender
import com.example.yumyum.domain.model.auth.SignInResult
import com.example.yumyum.domain.model.auth.UserData

// TODO: CHANGE CODE
interface AuthUserRepository {

    suspend fun signIn(): IntentSender?

    suspend fun signInWithIntent(intent: Intent): SignInResult

    fun getSignedInUser(): UserData?

    suspend fun signOut()

}
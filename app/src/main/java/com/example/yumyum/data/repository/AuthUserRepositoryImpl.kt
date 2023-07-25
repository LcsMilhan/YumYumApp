package com.example.yumyum.data.repository

import com.example.yumyum.common.Resource
import com.example.yumyum.domain.repository.AuthUserRepository
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AuthUserRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthUserRepository {

    override suspend fun signInWithGoogle(credential: AuthCredential): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.signInWithCredential(credential).await()
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }

    override suspend fun signOut() {
        firebaseAuth.signOut()
    }

}









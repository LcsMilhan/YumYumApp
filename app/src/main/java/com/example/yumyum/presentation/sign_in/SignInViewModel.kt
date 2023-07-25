package com.example.yumyum.presentation.sign_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yumyum.common.Resource
import com.example.yumyum.domain.repository.AuthUserRepository
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: AuthUserRepository
) : ViewModel() {

    private val _state = MutableStateFlow(GoogleSignInState())
    val state = _state.asStateFlow()

    fun signInWithGoogle(credential: AuthCredential) = viewModelScope.launch {
        repository.signInWithGoogle(credential).collect { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = GoogleSignInState(success = result.data)
                }
                is Resource.Loading -> {
                    _state.value = GoogleSignInState(loading = true)
                }
                is Resource.Error -> {
                    _state.value = GoogleSignInState(error = result.message!!)
                }
            }
        }
    }

}
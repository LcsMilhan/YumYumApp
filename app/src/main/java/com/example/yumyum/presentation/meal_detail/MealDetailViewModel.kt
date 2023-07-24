package com.example.yumyum.presentation.meal_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yumyum.common.Constants.PARAM_ID_MEAL
import com.example.yumyum.common.Resource
import com.example.yumyum.domain.use_case.ApiUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealDetailViewModel @Inject constructor(
    private val apiUseCases: ApiUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = MutableStateFlow(MealDetailState())
    val state: StateFlow<MealDetailState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            savedStateHandle.get<String>(PARAM_ID_MEAL)?.let { idMeal ->
                getMeal(idMeal)
            }
        }
    }

    private suspend fun getMeal(idMeal: String) {
        apiUseCases.getMealUseCase(idMeal).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = MealDetailState(
                        meals = result.data?.meals ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = MealDetailState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = MealDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
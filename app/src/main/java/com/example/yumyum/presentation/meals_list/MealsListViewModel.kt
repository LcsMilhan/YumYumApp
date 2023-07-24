package com.example.yumyum.presentation.meals_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yumyum.common.Constants.PARAM_STR_CATEGORY
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
class MealsListViewModel @Inject constructor(
    private val apiUseCases: ApiUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(MealsListState())
    val state: StateFlow<MealsListState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            savedStateHandle.get<String>(PARAM_STR_CATEGORY)?.let { strCategory ->
                getMeals(strCategory)
            }
        }
    }

    private suspend fun getMeals(strCategory: String)                                         {
        apiUseCases.getMealsUseCase(strCategory).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = MealsListState(
                        meals = result.data?.meals ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = MealsListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = MealsListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
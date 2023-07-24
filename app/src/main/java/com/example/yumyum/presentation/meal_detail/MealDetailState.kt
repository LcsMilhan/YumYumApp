package com.example.yumyum.presentation.meal_detail

import com.example.yumyum.domain.model.MealDetail

data class MealDetailState(
    val isLoading: Boolean = false,
    val meals: List<MealDetail> = emptyList(),
    val error: String = ""
)
package com.example.yumyum.presentation.meals_list

import com.example.yumyum.domain.model.meals.Meals

data class MealsListState(
    val isLoading: Boolean = false,
    val meals: List<Meals> = emptyList(),
    val error: String = ""
)

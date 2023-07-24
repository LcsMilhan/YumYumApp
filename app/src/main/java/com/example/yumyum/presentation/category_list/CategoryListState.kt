package com.example.yumyum.presentation.category_list

import com.example.yumyum.domain.model.Category

data class CategoryListState(
    val isLoading: Boolean = false,
    val categories: List<Category> = emptyList(),
    val error: String = ""
)

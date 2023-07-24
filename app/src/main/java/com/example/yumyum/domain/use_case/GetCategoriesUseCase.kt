package com.example.yumyum.domain.use_case

import com.example.yumyum.common.Resource
import com.example.yumyum.domain.model.CategoryResponse
import com.example.yumyum.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: MealRepository
) {

    suspend operator fun invoke(): Flow<Resource<CategoryResponse>> {
        return repository.getCategories()
    }

}
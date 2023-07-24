package com.example.yumyum.domain.use_case


import com.example.yumyum.common.Resource
import com.example.yumyum.domain.model.MealsResponse
import com.example.yumyum.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMealsUseCase @Inject constructor(
    private val repository: MealRepository
) {

    suspend operator fun invoke(strCategory: String): Flow<Resource<MealsResponse>> {
        return repository.getMealsByCategory(strCategory)
    }
}


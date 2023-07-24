package com.example.yumyum.domain.use_case

import com.example.yumyum.common.Resource
import com.example.yumyum.domain.model.MealDetailResponse
import com.example.yumyum.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMealUseCase @Inject constructor(
    private val repository: MealRepository
) {

    suspend operator fun invoke(idMeal: String): Flow<Resource<MealDetailResponse>> {
        return repository.getMealById(idMeal)
    }

}

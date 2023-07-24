package com.example.yumyum.domain.repository


import com.example.yumyum.common.Resource
import com.example.yumyum.domain.model.CategoryResponse
import com.example.yumyum.domain.model.MealDetailResponse
import com.example.yumyum.domain.model.MealsResponse
import kotlinx.coroutines.flow.Flow

interface MealRepository {

    suspend fun getCategories(): Flow<Resource<CategoryResponse>>

    suspend fun getMealsByCategory(strCategory: String): Flow<Resource<MealsResponse>>

    suspend fun getMealById(idMeal: String): Flow<Resource<MealDetailResponse>>

}
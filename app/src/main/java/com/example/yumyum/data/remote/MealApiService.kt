package com.example.yumyum.data.remote

import com.example.yumyum.domain.model.meals.CategoryResponse
import com.example.yumyum.domain.model.meals.MealDetailResponse
import com.example.yumyum.domain.model.meals.MealsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiService {

    @GET("categories.php")
    suspend fun getCategories(): CategoryResponse

    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") strCategory: String): MealsResponse

    @GET("lookup.php")
    suspend fun getMealById(@Query("i") idMeal: String): MealDetailResponse

}
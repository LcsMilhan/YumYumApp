package com.example.yumyum.presentation.navigation

sealed class Screen(val route: String) {
    object SplashScreen: Screen("splash_screen")
    object CategoriesScreen: Screen("categories_screen")
    object MealsScreen: Screen("meals_screen")
    object MealDetailScreen: Screen("meal_detail_screen")
}

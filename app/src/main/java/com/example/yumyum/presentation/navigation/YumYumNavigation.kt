package com.example.yumyum.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.yumyum.presentation.screens.AnimationSplashScreen
import com.example.yumyum.presentation.screens.AuthScreen
import com.example.yumyum.presentation.screens.CategoriesScreen
import com.example.yumyum.presentation.screens.MealDetailScreen
import com.example.yumyum.presentation.screens.MealsScreen
import com.example.yumyum.presentation.screens.ProfileScreen

@Composable
fun YumYumNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        // Splash Screen
        composable(
            route = Screen.SplashScreen.route
        ) {
            AnimationSplashScreen(navController = navController)
        }

        // Auth Screen
        composable(
            route = Screen.AuthScreen.route
        ) {
            AuthScreen(
                navigateToCategoriesScreen = {
                    navController.navigate(Screen.CategoriesScreen.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // Profile Screen
        composable(
            route = Screen.ProfileScreen.route
        ) {
            ProfileScreen(
                navigateToAuthScreen = {
                    navController.navigate(Screen.AuthScreen.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // Categories Screen
        composable(
            route = Screen.CategoriesScreen.route
        ) {
            CategoriesScreen(
                navController = navController,
                onCategoryClick = { strCategory ->
                    navController.navigate("${Screen.MealsScreen.route}/${strCategory}")
                }
            )
        }

        // Meals Screen
        composable(
            route = "${Screen.MealsScreen.route}/{strCategory}",
            arguments = listOf(navArgument("strCategory") { type = NavType.StringType })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getString("strCategory")?.let {
                MealsScreen(
                    navController = navController,
                    onMealItemClick = { idMeal ->
                        navController.navigate("${Screen.MealDetailScreen.route}/${idMeal}")
                    }
                )
            }
        }

        // Meal Detail Screen
        composable(
            route = "${Screen.MealDetailScreen.route}/{idMeal}",
            arguments = listOf(navArgument("idMeal") { type = NavType.StringType })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getString("idMeal")?.let {
                MealDetailScreen(navController = navController)
            }
        }
    }
}

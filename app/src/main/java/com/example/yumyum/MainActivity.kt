package com.example.yumyum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.yumyum.presentation.auth.AuthViewModel
import com.example.yumyum.presentation.navigation.Screen
import com.example.yumyum.presentation.navigation.YumYumNavigation
import com.example.yumyum.ui.theme.YumYumTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private val viewModel by viewModels<AuthViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YumYumTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    navController = rememberNavController()
                    YumYumNavigation(
                        navController = navController
                    )
                    checkAuthState()
                }
            }
        }
    }
    private fun checkAuthState() {
        if(viewModel.isUserAuthenticated) {
            navigateToProfileScreen()
        }
    }
    private fun navigateToProfileScreen() = navController.navigate(Screen.ProfileScreen.route)
}

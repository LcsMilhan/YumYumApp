package com.example.yumyum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.yumyum.presentation.navigation.YumYumNavigation
import com.example.yumyum.ui.theme.YumYumTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YumYumTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    YumYumNavigation()
                }
            }
        }
    }
}

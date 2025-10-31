package ru.nyxsed.thetome.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import ru.nyxsed.thetome.core.presentation.AppNavHost
import ru.nyxsed.thetome.features.start.presentation.StartScreen
import ru.nyxsed.thetome.ui.theme.TheTomeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheTomeTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}
package ru.nyxsed.thetome.core.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.nyxsed.thetome.features.game.presentation.GameScreen
import ru.nyxsed.thetome.features.settings.presentation.SettingsScreen
import ru.nyxsed.thetome.features.start.presentation.StartScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "start") {
        composable("start") { 
            StartScreen(
                onNewGameClicked = { navController.navigate("settings") },
                onRestoreGameClicked = { navController.navigate("restore_game") },
            )
        }
        composable("settings") {
            SettingsScreen(
                onStartGameClicked = { navController.navigate("restore_game") }
            )
        }
        composable("restore_game") {
            GameScreen()
        }
    }
}
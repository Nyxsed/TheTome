package ru.nyxsed.thetome.core.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.nyxsed.thetome.core.domain.models.Role
import ru.nyxsed.thetome.features.card.presentation.CardScreen
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
            GameScreen(
                onEditGameClicked = { navController.navigate("settings") },
                onCardClicked = { stringResourceId, roles ->
                    val cleanRoles = roles?.filterNotNull()
                    val rolesJson = Json.encodeToString(cleanRoles)
                    navController.navigate("card/$stringResourceId/$rolesJson")
                }
            )
        }
        composable("card/{stringResourceId}/{rolesJson}",
            arguments = listOf(
                navArgument("stringResourceId") { type = NavType.IntType },
                navArgument("rolesJson") { type = NavType.StringType }
            )) { backStackEntry ->
            val stringResourceId = backStackEntry.arguments?.getInt("stringResourceId") ?: 0
            val rolesJson = backStackEntry.arguments?.getString("rolesJson") ?: "[]"
            val roles = Json.decodeFromString<List<Role>>(rolesJson)
            CardScreen(stringResourceId = stringResourceId, roles = roles)
        }
    }
}
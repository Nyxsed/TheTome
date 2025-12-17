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
import ru.nyxsed.thetome.features.pickrole.presentation.PickRoleScreen
import ru.nyxsed.thetome.features.settings.presentation.SettingsScreen
import ru.nyxsed.thetome.features.start.presentation.StartScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "start") {
        composable("start") {
            StartScreen(
                onNewGameClicked = { navController.navigate("settings") },
                onRestoreGameClicked = { navController.navigate("game") },
            )
        }
        composable("settings") {
            SettingsScreen(
                onStartGameClicked = { navController.navigate("pick_role") }
            )
        }
        composable("pick_role") {
            PickRoleScreen(
                onNewGameClicked = { navController.navigate("game") },
                onRoleClicked = { stringResourceId, roles ->
                    val cleanRoles = roles?.filterNotNull()
                    val rolesJson = Json.encodeToString(cleanRoles)
                    navController.navigate("card/$stringResourceId/$rolesJson/true")
                },
                onDoublePressBack = {
                    navController.popBackStack()
                }
            )
        }
        composable("game") {
            GameScreen(
                onEditGameClicked = { navController.navigate("settings") },
                onCardClicked = { stringResourceId, roles ->
                    val cleanRoles = roles?.filterNotNull()
                    val rolesJson = Json.encodeToString(cleanRoles)
                    navController.navigate("card/$stringResourceId/$rolesJson/false")
                },
                onDoublePressBack = {
                    navController.popBackStack()
                }
            )
        }
        composable("card/{stringResourceId}/{rolesJson}/{haveButton}",
            arguments = listOf(
                navArgument("stringResourceId") { type = NavType.IntType },
                navArgument("rolesJson") { type = NavType.StringType },
                navArgument("haveButton") { type = NavType.BoolType }
            )) { backStackEntry ->
            val stringResourceId = backStackEntry.arguments?.getInt("stringResourceId") ?: 0
            val rolesJson = backStackEntry.arguments?.getString("rolesJson") ?: "[]"
            val roles = Json.decodeFromString<List<Role>>(rolesJson)
            val haveButton = backStackEntry.arguments?.getBoolean("haveButton") ?: false
            CardScreen(
                stringResourceId = stringResourceId,
                roles = roles,
                haveButton = haveButton,
                onDoublePressBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
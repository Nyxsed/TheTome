package ru.nyxsed.thetome.features.game.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import ru.nyxsed.thetome.core.domain.models.GameState
import ru.nyxsed.thetome.core.domain.models.Player
import ru.nyxsed.thetome.core.domain.models.Role
import ru.nyxsed.thetome.features.game.presentation.ArcPosition
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

@Composable
fun PlayersWheel(
    state: GameState,
    onUpdateTokens: (Player, List<String>) -> Unit,
    onChangeAliveStatus: (Player) -> Unit,
    onRenamePlayer: (Player, String) -> Unit,
    onChangeRole: (Player, Role) -> Unit,
) {
    var renameTarget by remember { mutableStateOf<Player?>(null) }
    var renameText by remember { mutableStateOf("") }

    if (renameTarget != null) {
        AlertDialog(
            onDismissRequest = { renameTarget = null },
            title = { Text("Переименовать игрока") },
            text = {
                OutlinedTextField(
                    value = renameText,
                    onValueChange = { renameText = it },
                    label = { Text("Name") },
                    singleLine = true
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    val target = renameTarget
                    if (target != null && renameText.isNotBlank()) {
                        onRenamePlayer(target, renameText.trim())
                    }
                    renameTarget = null
                }) {
                    Text("Ok")
                }
            },
            dismissButton = {
                TextButton(onClick = { renameTarget = null }) {
                    Text("Cancel")
                }
            }
        )
    }

    var roleTarget by remember { mutableStateOf<Player?>(null) }
    var selectedRole by remember { mutableStateOf<Role?>(null) }
    if (roleTarget != null && state.chosenRoles?.isNotEmpty() == true) {
        AlertDialog(
            onDismissRequest = { roleTarget = null },
            title = { Text("Pick a role") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    state.chosenRoles?.forEach { role ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }
                                ) {
                                    selectedRole = role
                                    val target = roleTarget
                                    if (target != null) {
                                        onChangeRole(target, role)
                                    }
                                    roleTarget = null
                                }
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = selectedRole == role,
                                onClick = {
                                    selectedRole = role
                                    val target = roleTarget
                                    if (target != null) {
                                        onChangeRole(target, role)
                                    }
                                    roleTarget = null
                                }
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(role.roleId.name)
                        }
                    }
                }
            },
            confirmButton = {},
            dismissButton = {
                TextButton(onClick = { roleTarget = null }) {
                    Text("Отмена")
                }
            }
        )
    }
    BoxWithConstraints(
        contentAlignment = Alignment.Center
    ) {
        val maxRadius = (min(maxWidth, maxHeight) / 2) - 32.dp
        val radiusOuterPx = with(LocalDensity.current) { maxRadius.toPx() }

        // Масштабируем размер кружка в зависимости от количества игроков
        val playerCircleSize = when {
            state.players?.size!! <= 6 -> 84.dp
            state.players.size <= 10 -> 74.dp
            else -> 64.dp
        }

        val angleStep = 360f / state.players.size
        state.players.forEachIndexed { index, player ->
            // Первый игрок будет сверху
            val angle = -90f + angleStep * index
            val angleRad = Math.toRadians(angle.toDouble())

            val xOuter = radiusOuterPx * cos(angleRad)
            val yOuter = radiusOuterPx * sin(angleRad)

            // Локальное состояние — раскрыто ли меню
            var isMenuExpanded by remember { mutableStateOf(false) }

            // Внешний кружок игрока
            Box(
                modifier = Modifier
                    .offset { IntOffset(xOuter.roundToInt(), yOuter.roundToInt()) }
                    .size(playerCircleSize)
                    .background(
                        if (player.isAlive) Color.Gray else Color.Red,
                        CircleShape
                    )
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { isMenuExpanded = true },
                contentAlignment = Alignment.Center
            ) {
                TextArc(text = player.name ?: "", circleSize = playerCircleSize, position = ArcPosition.TOP)
                TextArc(text = player.role?.roleId?.name ?: "", circleSize = playerCircleSize, position = ArcPosition.BOTTOM)

                // Меню действий
                DropdownMenu(
                    expanded = isMenuExpanded,
                    onDismissRequest = { isMenuExpanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Rename") },
                        onClick = {
                            isMenuExpanded = false
                            renameTarget = player
                            renameText = player.name ?: ""
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Change Role") },
                        onClick = {
                            isMenuExpanded = false
                            roleTarget = player
                            selectedRole = null
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Kill player") },
                        onClick = {
                            isMenuExpanded = false
                            onChangeAliveStatus(player)
                        }
                    )
                }
            }

            // Кружки токенов и "+"
            val baseTokenRadius = playerCircleSize / 2 + 10.dp
            val tokenSize = when {
                state.players.size <= 6 -> 30.dp
                state.players.size <= 10 -> 20.dp
                else -> 20.dp
            }
            val tokenSpacingPx = with(LocalDensity.current) { tokenSize.toPx() + 1f }

            player.tokens.forEachIndexed { tokenIndex, token ->
                val tokenRadiusPx = with(LocalDensity.current) { baseTokenRadius.toPx() } + tokenIndex * tokenSpacingPx
                val xToken = xOuter - tokenRadiusPx * cos(angleRad)
                val yToken = yOuter - tokenRadiusPx * sin(angleRad)

                Box(
                    modifier = Modifier
                        .offset { IntOffset(xToken.roundToInt(), yToken.roundToInt()) }
                        .size(tokenSize)
                        .background(Color.DarkGray, CircleShape)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            val newTokens = player.tokens.toMutableList().also { it.removeAt(tokenIndex) }
                            onUpdateTokens(player, newTokens)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text("2", fontSize = 6.sp, color = Color.White, textAlign = TextAlign.Center, lineHeight = 6.sp)
                }
            }

            // Добавочный "+"
            val nextTokenIndex = player.tokens.size
            val plusRadiusPx = with(LocalDensity.current) { baseTokenRadius.toPx() } + nextTokenIndex * tokenSpacingPx
            val xPlus = xOuter - plusRadiusPx * cos(angleRad)
            val yPlus = yOuter - plusRadiusPx * sin(angleRad)

            Box(
                modifier = Modifier
                    .offset { IntOffset(xPlus.roundToInt(), yPlus.roundToInt()) }
                    .size(tokenSize)
                    .background(Color.DarkGray.copy(alpha = 0.3f), CircleShape)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        val newTokens = player.tokens.toMutableList().also { it.addLast("123") }
                        onUpdateTokens(player, newTokens)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text("+", fontSize = 6.sp, color = Color.Black, textAlign = TextAlign.Center, lineHeight = 6.sp)
            }
        }
    }
}
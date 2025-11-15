package ru.nyxsed.thetome.features.game.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import ru.nyxsed.thetome.core.domain.models.GameState
import ru.nyxsed.thetome.core.domain.models.Player
import ru.nyxsed.thetome.core.domain.models.Role
import ru.nyxsed.thetome.core.domain.models.Token
import ru.nyxsed.thetome.core.presentation.components.CircleItem
import ru.nyxsed.thetome.core.presentation.components.CircleMenuItem
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

@Composable
fun PlayersWheel(
    state: GameState,
    onUpdateTokens: (Player, List<Token>) -> Unit,
    onChangeAliveStatus: (Player) -> Unit,
    onRenamePlayer: (Player, String) -> Unit,
    onChangeRole: (Player, Role?) -> Unit,
) {
    var renameTarget by remember { mutableStateOf<Player?>(null) }
    var renameText by remember { mutableStateOf("") }

    RenamePlayerDialog(
        target = renameTarget,
        name = renameText,
        onNameChange = { renameText = it },
        onConfirm = {
            val target = renameTarget
            if (target != null && renameText.isNotBlank()) {
                onRenamePlayer(target, renameText.trim())
            }
            renameTarget = null
        },
        onDismiss = { renameTarget = null }
    )


    var roleTarget by remember { mutableStateOf<Player?>(null) }
    var selectedRole by remember { mutableStateOf<Role?>(null) }

    RolePickerDialog(
        target = roleTarget,
        chosenRoles = state.chosenRoles,
        players = state.players,
        selectedRole = selectedRole,
        onSelectRole = { role ->
            val target = roleTarget
            if (target != null) onChangeRole(target, role)
            roleTarget = null
        },
        onDismiss = { roleTarget = null }
    )

    var tokenTargetPlayer by remember { mutableStateOf<Player?>(null) }
    var selectedToken by remember { mutableStateOf<Token?>(null) }

    TokenPickerDialog(
        target = tokenTargetPlayer,
        chosenRoles = state.chosenRoles,
        players = state.players,
        onPickToken = { token ->
            tokenTargetPlayer?.let { player ->
                val newTokens = player.tokens.toMutableList().also { it.add(token) }
                onUpdateTokens(player, newTokens)
            }
            tokenTargetPlayer = null
        },
        onDismiss = { tokenTargetPlayer = null }
    )

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

            // Внешний кружок игрока
            CircleItem(
                modifier = Modifier.offset { IntOffset(xOuter.roundToInt(), yOuter.roundToInt()) },
                size = playerCircleSize,
                backgroundColor = if (player.isAlive) Color.Gray else Color.Red,
                topText = player.name ?: "",
                bottomText = player.role?.roleId?.name ?: "",
                menuItems = listOf(
                    CircleMenuItem("Rename") {
                        renameTarget = player
                        renameText = player.name ?: ""
                    },
                    CircleMenuItem("Change Role") {
                        roleTarget = player
                        selectedRole = null
                    },
                    CircleMenuItem("Kill player") {
                        onChangeAliveStatus(player)
                    }
                )
            )

            // Кружки токенов
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

                CircleItem(
                    modifier = Modifier.offset { IntOffset(xToken.roundToInt(), yToken.roundToInt()) },
                    size = tokenSize,
                    backgroundColor = Color.DarkGray,
                    bottomText = token.name,
                    onClick = {
                        val newTokens = player.tokens.toMutableList().also { it.removeAt(tokenIndex) }
                        onUpdateTokens(player, newTokens)
                    }
                )
            }

            // Добавочный "+"
            val nextTokenIndex = player.tokens.size
            val plusRadiusPx = with(LocalDensity.current) { baseTokenRadius.toPx() } + nextTokenIndex * tokenSpacingPx
            val xPlus = xOuter - plusRadiusPx * cos(angleRad)
            val yPlus = yOuter - plusRadiusPx * sin(angleRad)

            CircleItem(
                modifier = Modifier.offset { IntOffset(xPlus.roundToInt(), yPlus.roundToInt()) },
                size = tokenSize,
                backgroundColor = Color.DarkGray,
                centerText = "+",
                onClick = {
                    tokenTargetPlayer = player
                }
            )
        }
    }
}

@Composable
fun RenamePlayerDialog(
    target: Player?,
    name: String,
    onNameChange: (String) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    if (target == null) return

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Rename player") },
        text = {
            OutlinedTextField(
                value = name,
                onValueChange = onNameChange,
                label = { Text("Name") },
                singleLine = true
            )
        },
        confirmButton = {
            TextButton(onClick = onConfirm) { Text("Ok") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}


@Composable
fun RolePickerDialog(
    target: Player?,
    chosenRoles: List<Role>?,
    players: List<Player>?,
    selectedRole: Role?,
    onSelectRole: (Role?) -> Unit,
    onDismiss: () -> Unit,
) {
    if (target == null || chosenRoles.isNullOrEmpty()) return

    val availableRoles = remember(chosenRoles, players) {
        chosenRoles.filter { role -> players?.none { it != target && it.role == role } == true }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Pick a role") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

                // "Без роли"
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) { onSelectRole(null) }
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedRole == null,
                        onClick = { onSelectRole(null) }
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("Без роли")
                }

                availableRoles.forEach { role ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) { onSelectRole(role) }
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedRole == role,
                            onClick = { onSelectRole(role) }
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(role.roleId.name)
                    }
                }
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Отмена") }
        }
    )
}

@Composable
fun TokenPickerDialog(
    target: Player?,
    chosenRoles: List<Role>?,
    players: List<Player>?,
    onPickToken: (Token) -> Unit,
    onDismiss: () -> Unit,
) {
    if (target == null) return

    val availableTokens = remember(chosenRoles, players) {
        val all = chosenRoles?.flatMap { it.tokens } ?: emptyList()
        all.filter { token -> players?.none { token in it.tokens } == true }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Choose token") },
        text = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center // центрируем FlowRow
            ) {
                FlowRow(
                    modifier = Modifier.wrapContentWidth(), // FlowRow по ширине содержимого
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    availableTokens.forEach { token ->
                        CircleItem(
                            size = 70.dp,
                            backgroundColor = Color.DarkGray,
                            bottomText = token.name,
                            onClick = { onPickToken(token) }
                        )
                    }
                }
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Отмена") }
        }
    )
}
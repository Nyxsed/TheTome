package ru.nyxsed.thetome.features.game.presentation.components

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import ru.nyxsed.thetome.R
import ru.nyxsed.thetome.core.domain.models.GameState
import ru.nyxsed.thetome.core.domain.models.ItemType
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
    onChangeGhostVoteStatus: (Player) -> Unit,
    onRenamePlayer: (Player, String) -> Unit,
    onChangeRole: (Player, Role?) -> Unit,
    onShowCardClicked: (Role?) -> Unit,
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
    if (roleTarget != null) {
        val takenRoles = state.players?.mapNotNull { it.role } ?: emptyList()
        val availableRoles = state.chosenRoles?.filter { role ->
            role !in takenRoles || roleTarget?.role == role
        } ?: emptyList()
        RolePickerDialog(
            availableRoles = availableRoles,
            onSelectRole = { role ->
                val target = roleTarget
                if (target != null) onChangeRole(target, role)
                roleTarget = null
            },
            onDismiss = { roleTarget = null }
        )
    }

    var tokenTargetPlayer by remember { mutableStateOf<Player?>(null) }
    TokenPickerDialog(
        target = tokenTargetPlayer,
        chosenRoles = state.chosenRoles,
        players = state.players,
        sceneryTokens = state.scenery?.sceneryTokens!!,
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

            val menuItems = buildList {
                add(
                    CircleMenuItem(stringResource(R.string.menu_rename)) {
                        renameTarget = player
                        renameText = player.name ?: ""
                    }
                )
                add(
                    CircleMenuItem(stringResource(R.string.menu_change_role)) {
                        roleTarget = player
                    }
                )

                if (player.role != null) {
                    add(
                        CircleMenuItem(stringResource(R.string.menu_kill_player)) {
                            onChangeAliveStatus(player)
                        }
                    )
                    add(
                        CircleMenuItem(stringResource(R.string.menu_show_card)) {
                            onShowCardClicked(player.role)
                        }
                    )
                }

                if (!player.isAlive) {
                    add(
                        CircleMenuItem(stringResource(R.string.menu_spend_ghost_vote)) {
                            onChangeGhostVoteStatus(player)
                        }
                    )
                }
            }

            // Внешний кружок игрока
            val title = player.role?.roleName?.let { stringResource(it) } ?: ""
            CircleItem(
                itemType = ItemType.PLAYER_CIRCLE,
                modifier = Modifier.offset { IntOffset(xOuter.roundToInt(), yOuter.roundToInt()) },
                size = playerCircleSize,
                centerIcon = player.role?.iconRes,
                topText = player.name ?: "",
                bottomText = title,
                menuItems = menuItems,
                isEnabled = player.isAlive,
                haveGhostVote = player.haveGhostVote,
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
                    itemType = ItemType.TOKEN_CIRCLE,
                    modifier = Modifier.offset { IntOffset(xToken.roundToInt(), yToken.roundToInt()) },
                    size = tokenSize,
                    centerIcon = token.iconRes,
                    bottomText = stringResource(token.nameResId),
                    onClick = {
                        val newTokens = player.tokens.toMutableList().also { it.removeAt(tokenIndex) }
                        onUpdateTokens(player, newTokens)
                    }
                )
            }

            val nextTokenIndex = player.tokens.size
            val plusRadiusPx = with(LocalDensity.current) { baseTokenRadius.toPx() } + nextTokenIndex * tokenSpacingPx
            val xPlus = xOuter - plusRadiusPx * cos(angleRad)
            val yPlus = yOuter - plusRadiusPx * sin(angleRad)

            CircleItem(
                itemType = ItemType.TOKEN_CIRCLE,
                modifier = Modifier.offset { IntOffset(xPlus.roundToInt(), yPlus.roundToInt()) },
                size = tokenSize,
                centerText = "+",
                onClick = {
                    tokenTargetPlayer = player
                }
            )
        }
    }
}
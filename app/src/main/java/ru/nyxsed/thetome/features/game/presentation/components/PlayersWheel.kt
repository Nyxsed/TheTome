package ru.nyxsed.thetome.features.game.presentation.components

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import ru.nyxsed.thetome.R
import ru.nyxsed.thetome.core.domain.models.*
import ru.nyxsed.thetome.core.presentation.components.CircleItem
import ru.nyxsed.thetome.core.presentation.components.CircleMenuItem
import ru.nyxsed.thetome.core.presentation.components.RoleInfoDialog
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

    var isRoleInfoDialogRaised by remember { mutableStateOf(false) }
    var roleInfoTarget by remember { mutableStateOf<Role?>(null) }
    if (isRoleInfoDialogRaised && roleInfoTarget != null) {
        RoleInfoDialog(
            role = roleInfoTarget!!,
            onDismiss = {
                isRoleInfoDialogRaised = false
                roleInfoTarget = null
            }
        )
    }

    BoxWithConstraints(
        modifier = Modifier
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        val playersCount = state.players!!.size

        val maxRadius = when {
            playersCount <= 6 -> (min(maxWidth, maxHeight) / 2) - 38.dp
            playersCount <= 10 -> (min(maxWidth, maxHeight) / 2) - 38.dp
            else -> (min(maxWidth, maxHeight) / 2) - 30.dp
        }

        val radiusOuterPx = with(LocalDensity.current) { maxRadius.toPx() }

        val playerCircleSize = when {
            state.players.size <= 6 -> 100.dp
            state.players.size <= 10 -> 80.dp
            else -> 70.dp
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
                    val titleId = if (player.isAlive) R.string.menu_kill_player else R.string.menu_revive_player
                    add(
                        CircleMenuItem(stringResource(titleId)) {
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
                    val titleId =
                        if (player.haveGhostVote) R.string.menu_spend_ghost_vote else R.string.menu_return_ghost_vote
                    add(
                        CircleMenuItem(stringResource(titleId)) {
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
                onLongClick = {
                    isRoleInfoDialogRaised = true
                    roleInfoTarget = player.role
                }
            )


            // Кружки токенов
            val baseTokenRadius = playerCircleSize / 2 + 10.dp
            val tokenSize = when {
                state.players.size <= 6 -> 40.dp
                state.players.size <= 10 -> 40.dp
                else -> 35.dp
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
                    onLongClick = {
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
                isAddToken = true,
                onClick = {
                    tokenTargetPlayer = player
                }
            )
        }
    }
}
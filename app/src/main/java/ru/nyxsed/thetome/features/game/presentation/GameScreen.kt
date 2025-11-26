package ru.nyxsed.thetome.features.game.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.nyxsed.thetome.R
import ru.nyxsed.thetome.core.domain.models.GameState
import ru.nyxsed.thetome.core.domain.models.Player
import ru.nyxsed.thetome.core.domain.models.Role
import ru.nyxsed.thetome.core.domain.models.Token
import ru.nyxsed.thetome.core.presentation.components.BackHandlerInterceptor
import ru.nyxsed.thetome.core.presentation.components.GameScreenBackground
import ru.nyxsed.thetome.features.game.presentation.components.*

@Composable
fun GameScreen(
    viewModel: GameViewModel = hiltViewModel(),
    onEditGameClicked: () -> Unit,
    onCardClicked: (Int, List<Role?>?) -> Unit,
    onDoublePressBack: () -> Unit,
) {
    val state by viewModel.state.collectAsState()

    BackHandlerInterceptor(
        onDoublePressed = onDoublePressBack
    )

    GameContent(
        state = state,
        onEditGame = onEditGameClicked,
        onCard = onCardClicked,
        onEditNotes = {
            viewModel.editNotes(it)
        },
        onChangeAliveStatus = {
            viewModel.changeAliveStatus(it)
        },
        onChangeGhostVoteStatus = {
            viewModel.onChangeGhostVoteStatus(it)
        },
        onRenamePlayer = { player, name ->
            viewModel.renamePlayer(player, name)
        },
        onChangeRole = { player, role ->
            viewModel.changeRole(player, role)
        },
        onChangeBluffs = {
            viewModel.changeDemonBluffs(it)
        },
        onMoveToPreviousAction = {
            viewModel.moveToPreviousAction()
        },
        onMoveToNextAction = {
            viewModel.moveToNextAction()
        },
        onAddToken = { player, token ->
            viewModel.addToken(player, token)
        },
        onDeleteToken = { player, tokenIndex ->
            viewModel.deleteToken(player, tokenIndex)
        }
    )
}

@Composable
fun GameContent(
    state: GameState,
    onEditGame: () -> Unit,
    onEditNotes: (String) -> Unit,
    onCard: (Int, List<Role?>?) -> Unit,
    onChangeAliveStatus: (Player) -> Unit,
    onChangeGhostVoteStatus: (Player) -> Unit,
    onRenamePlayer: (Player, String) -> Unit,
    onChangeRole: (Player, Role?) -> Unit,
    onChangeBluffs: (List<Role?>) -> Unit,
    onMoveToPreviousAction: () -> Unit,
    onMoveToNextAction: () -> Unit,
    onAddToken: (Player, Token) -> Unit,
    onDeleteToken: (Player, Int) -> Unit,
) {
    var isEditDialogRaised by remember { mutableStateOf(false) }
    var isMemoDialogRaised by remember { mutableStateOf(false) }
    var isPickRoleDialogRaised by remember { mutableStateOf(false) }
    var targetEditPlayer by remember { mutableStateOf<Player?>(null) }
    var targetTokenPlayer by remember { mutableStateOf<Player?>(null) }

    GameScreenBackground(state.currentPhase)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                WindowInsets.safeDrawing
                    .only(WindowInsetsSides.Top)
                    .asPaddingValues()
            )
    ) {
        TopButtonsRow(
            sceneryRoles = state.scenery?.roles,
            onEditClicked = {
                isEditDialogRaised = true
            },
            onMemoClicked = {
                isMemoDialogRaised = true
            },
            menuItems = listOf(
                CardsMenuItem(stringResource(R.string.menu_demon)) {
                    onCard(R.string.menu_demon, emptyList())
                },
                CardsMenuItem(stringResource(R.string.menu_minions)) {
                    onCard(R.string.menu_minions, emptyList())
                },
                CardsMenuItem(stringResource(R.string.menu_not_in_play)) {
                    onCard(R.string.menu_not_in_play, state.demonBluffs)
                },
                CardsMenuItem(stringResource(R.string.menu_use_ability)) {
                    onCard(R.string.menu_use_ability, emptyList())
                },
                CardsMenuItem(stringResource(R.string.menu_make_choice)) {
                    onCard(R.string.menu_make_choice, emptyList())
                },
                CardsMenuItem(stringResource(R.string.menu_nominated_today)) {
                    onCard(R.string.menu_nominated_today, emptyList())
                },
                CardsMenuItem(stringResource(R.string.menu_voted_today)) {
                    onCard(R.string.menu_voted_today, emptyList())
                },
                CardsMenuItem(stringResource(R.string.menu_char_selected_you), showPickerDialog = true) { role ->
                    onCard(R.string.menu_char_selected_you, listOf(role))
                },
                CardsMenuItem(stringResource(R.string.menu_show_role), showPickerDialog = true) { role ->
                    onCard(role?.ability!!, listOf(role))
                },
            )
        )

        Spacer(Modifier.height(20.dp))
        if (!state.players.isNullOrEmpty()) {
            PlayersWheel(
                players = state.players,
                onClick = { player ->
                    targetEditPlayer = player
                },
                onLongClick = {
                    targetTokenPlayer = it
                },
                onTokenLongClick = { player, tokenIndex ->
                    onDeleteToken(player, tokenIndex)
                }
            )
        }

        Spacer(Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            if (state.players?.size!! > 6) {
                DemonBluff(
                    demonBluffRoles = state.demonBluffs,
                    availableRoles = state.availableBluffRoles,
                    onChangeBluffs = {
                        onChangeBluffs(it)
                    },
                )
            }
            KillParticipation(
                roleDistribution = state.roleDistribution,
                players = state.players,
                dayNumber = state.currentDay,
            )
        }

        Spacer(Modifier.height(8.dp))
        Reminder(
            modifier = Modifier.weight(1f),
            action = state.currentAction,
            onBeforeClicked = { onMoveToPreviousAction() },
            onAfterClicked = { onMoveToNextAction() },
        )

        if (targetEditPlayer != null && !isPickRoleDialogRaised) {
            EditPlayerDialog(
                player = targetEditPlayer!!,
                onDismissRequest = {
                    targetEditPlayer = null
                },
                onChangeName = { player, name ->
                    onRenamePlayer(player, name)
                    targetEditPlayer = null
                },
                onChangeAliveStatus = {
                    onChangeAliveStatus(it)
                    targetEditPlayer = null
                },
                onChangeGhostVote = {
                    onChangeGhostVoteStatus(it)
                    targetEditPlayer = null
                },
                onShowRolePicker = {
                    isPickRoleDialogRaised = true
                },
                onShowCardClicked = { role ->
                    onCard(role.ability, listOf(role))
                },
                onChooseTokenClicked = {
                    targetTokenPlayer = it
                    targetEditPlayer = null
                }
            )
        }

        val takenRoles = state.players?.mapNotNull { it.role } ?: emptyList()
        val availableRoles = state.chosenRoles?.filter { role ->
            role !in takenRoles || targetEditPlayer?.role == role
        } ?: emptyList()

        if (targetEditPlayer != null && isPickRoleDialogRaised) {
            RolePickerDialog(
                availableRoles = availableRoles,
                sceneryRoles = state.scenery?.roles,
                onSelectRole = { role ->
                    onChangeRole(targetEditPlayer!!, role)
                    targetEditPlayer = null
                    isPickRoleDialogRaised = false
                },
                onDismiss = {
                    targetEditPlayer = null
                    isPickRoleDialogRaised = false
                }
            )
        }


        if (isEditDialogRaised) {
            EditGameDialog(
                onConfirm = {
                    onEditGame()
                },
                onDismiss = {
                    isEditDialogRaised = false
                }
            )
        }

        if (isMemoDialogRaised) {
            EditNotesDialog(
                onDismiss = {
                    isMemoDialogRaised = false
                },
                notes = state.notes,
                onNotesChange = { onEditNotes(it) }
            )
        }

        if (targetTokenPlayer != null) {
            TokenPickerDialog(
                target = targetTokenPlayer,
                chosenRoles = state.chosenRoles,
                players = state.players,
                sceneryTokens = state.scenery?.sceneryTokens!!,
                onPickToken = { token ->
                    targetTokenPlayer?.let { player ->
                        onAddToken(targetTokenPlayer!!, token)
                    }
                    targetTokenPlayer = null
                },
                onDismiss = { targetTokenPlayer = null }
            )
        }
    }
}
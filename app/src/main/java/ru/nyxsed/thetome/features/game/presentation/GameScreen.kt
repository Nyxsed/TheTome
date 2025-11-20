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
        onUpdateTokensClicked = { player, tokens ->
            viewModel.updateTokens(player = player, tokens = tokens)
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
    )
}

@Composable
fun GameContent(
    state: GameState,
    onEditGame: () -> Unit,
    onCard: (Int, List<Role?>?) -> Unit,
    onUpdateTokensClicked: (Player, List<Token>) -> Unit,
    onChangeAliveStatus: (Player) -> Unit,
    onChangeGhostVoteStatus: (Player) -> Unit,
    onRenamePlayer: (Player, String) -> Unit,
    onChangeRole: (Player, Role?) -> Unit,
    onChangeBluffs: (List<Role?>) -> Unit,
    onMoveToPreviousAction: () -> Unit,
    onMoveToNextAction: () -> Unit,
) {
    var isEditDialogRaised by remember { mutableStateOf(false) }

    GameScreenBackground(state.currentPhase)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                WindowInsets.safeDrawing
                    .only(WindowInsetsSides.Top)
                    .asPaddingValues()
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopButtonsRow(
            sceneryRoles = state.scenery?.roles,
            onEditClicked = {
                isEditDialogRaised = true
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
                }
            ),
            menuItemRole = CardsMenuItem(stringResource(R.string.menu_show_role)) { role ->
                onCard(role?.ability!!, listOf(role))
            }
        )

        Spacer(Modifier.height(150.dp))

        if (!state.players.isNullOrEmpty()) {
            PlayersWheel(
                state = state,
                onUpdateTokens = { player, tokens ->
                    onUpdateTokensClicked(player, tokens)
                },
                onChangeAliveStatus = {
                    onChangeAliveStatus(it)
                },
                onChangeGhostVoteStatus = {
                    onChangeGhostVoteStatus(it)
                },
                onRenamePlayer = { player, name ->
                    onRenamePlayer(player, name)
                },
                onChangeRole = { player, role ->
                    onChangeRole(player, role)
                },
                onShowCardClicked = { role ->
                    onCard(role?.ability!!, listOf(role))
                },
            )
        }

        Spacer(Modifier.height(140.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            DemonBluff(
                demonBluffRoles = state.demonBluffs,
                availableRoles = state.availableBluffRoles,
                onChangeBluffs = {
                    onChangeBluffs(it)
                },
            )
            KillParticipation(
                roleDistribution = state.roleDistribution,
                players = state.players
            )
        }

        Spacer(Modifier.height(8.dp))

        Reminder(
            modifier = Modifier.weight(1f),
            action = state.currentAction,
            onBeforeClicked = { onMoveToPreviousAction() },
            onAfterClicked = { onMoveToNextAction() },
        )

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
    }
}
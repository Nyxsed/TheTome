package ru.nyxsed.thetome.features.game.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.nyxsed.thetome.R
import ru.nyxsed.thetome.core.domain.models.Role
import ru.nyxsed.thetome.features.game.presentation.components.*

@Composable
fun GameScreen(
    viewModel: GameViewModel = hiltViewModel(),
    onEditGameClicked: () -> Unit,
    onCardClicked: (Int, List<Role?>?) -> Unit,
) {
    val state by viewModel.state.collectAsState()

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
                onEditGameClicked()
            },
            menuItems = listOf(
                CardsMenuItem(stringResource(R.string.menu_demon)) {
                    onCardClicked(R.string.menu_demon, emptyList())
                },
                CardsMenuItem(stringResource(R.string.menu_minions)) {
                    onCardClicked(R.string.menu_minions, emptyList())
                },
                CardsMenuItem(stringResource(R.string.menu_not_in_play)) {
                    onCardClicked(R.string.menu_not_in_play, state.demonBluffs)
                },
                CardsMenuItem(stringResource(R.string.menu_use_ability)) {
                    onCardClicked(R.string.menu_use_ability, emptyList())
                },
                CardsMenuItem(stringResource(R.string.menu_make_choice)) {
                    onCardClicked(R.string.menu_make_choice, emptyList())
                },
                CardsMenuItem(stringResource(R.string.menu_nominated_today)) {
                    onCardClicked(R.string.menu_nominated_today, emptyList())
                },
                CardsMenuItem(stringResource(R.string.menu_voted_today)) {
                    onCardClicked(R.string.menu_voted_today, emptyList())
                }
            ),
            menuItemRole = CardsMenuItem(stringResource(R.string.menu_show_role)) { role ->
                onCardClicked(role?.ability!!, listOf(role))
            }
        )

        Spacer(Modifier.height(180.dp))

        if (!state.players.isNullOrEmpty()) {
            PlayersWheel(
                state = state,
                onUpdateTokens = { player, tokens ->
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
                onShowCardClicked = { role ->
                    onCardClicked(role?.ability!!, listOf(role))
                },
            )
        }

        Spacer(Modifier.height(180.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            DemonBluff(
                demonBluffRoles = state.demonBluffs,
                availableRoles = state.availableBluffRoles,
                onChangeBluffs = { viewModel.changeDemonBluffs(it) },
            )
            KillParticipation(
                roleDistribution = state.roleDistribution,
                players = state.players
            )
        }

        Spacer(Modifier.height(20.dp))

        Reminder(
            modifier = Modifier.weight(1f),
            action = state.currentAction,
            onBeforeClicked = { viewModel.moveToPreviousAction() },
            onAfterClicked = { viewModel.moveToNextAction() },
        )
    }
}

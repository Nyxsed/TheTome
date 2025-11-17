package ru.nyxsed.thetome.features.game.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.nyxsed.thetome.core.domain.models.Role
import ru.nyxsed.thetome.core.domain.models.RoleType
import ru.nyxsed.thetome.features.game.presentation.components.DemonBluff
import ru.nyxsed.thetome.features.game.presentation.components.KillParticipation
import ru.nyxsed.thetome.features.game.presentation.components.PlayersWheel
import ru.nyxsed.thetome.features.game.presentation.components.Reminder
import ru.nyxsed.thetome.features.game.presentation.components.TopButtonsRow

@Composable
fun GameScreen(
    viewModel: GameViewModel = hiltViewModel(),
    onEditGameClicked: () -> Unit,
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
            onAdd = { },
            onEdit = {
                onEditGameClicked()
            },
            onSettings = { }
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
                onRenamePlayer = { player, name ->
                    viewModel.renamePlayer(player, name)
                },
                onChangeRole = { player, role ->
                    viewModel.changeRole(player, role)
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

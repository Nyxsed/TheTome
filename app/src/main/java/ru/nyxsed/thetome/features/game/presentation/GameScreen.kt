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
import ru.nyxsed.thetome.features.game.presentation.components.DemonBluff
import ru.nyxsed.thetome.features.game.presentation.components.KillParticipation
import ru.nyxsed.thetome.features.game.presentation.components.PlayersWheel
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

        Spacer(Modifier.height(200.dp))

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

        Spacer(Modifier.height(200.dp))

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
    }
}

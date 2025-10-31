package ru.nyxsed.thetome.features.game.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import ru.nyxsed.thetome.features.game.presentation.components.PlayersWheel

@Composable
fun GameScreen(
    viewModel: GameViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
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
    }
}



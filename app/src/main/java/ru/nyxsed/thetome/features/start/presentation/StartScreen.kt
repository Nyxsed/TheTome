package ru.nyxsed.thetome.features.start.presentation


import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.nyxsed.thetome.R
import ru.nyxsed.thetome.core.domain.models.GameState
import ru.nyxsed.thetome.ui.theme.DarkPurple


@Composable
fun StartScreen(
    viewModel: StartViewModel = hiltViewModel(),
    onNewGameClicked: () -> Unit,
    onRestoreGameClicked: () -> Unit,
) {
    val state by viewModel.state.collectAsState()

    StartScreenContent(
        state = state,
        onNewGameClicked = { onNewGameClicked() },
        onRestoreGameClicked = { onRestoreGameClicked() }
    )
}

@Composable
fun StartScreenContent(
    state: GameState,
    onNewGameClicked: () -> Unit,
    onRestoreGameClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = onNewGameClicked,
            colors = ButtonDefaults.buttonColors()
                .copy(containerColor = DarkPurple, disabledContainerColor = DarkPurple.copy(alpha = 0.5f))
        ) {
            Text(stringResource(R.string.text_new_game))
        }
        Spacer(modifier = Modifier.height(16.dp))

        if (state.scenery != null) {
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = onRestoreGameClicked,
                colors = ButtonDefaults.buttonColors()
                    .copy(containerColor = DarkPurple, disabledContainerColor = DarkPurple.copy(alpha = 0.5f))
            ) {
                Text(stringResource(R.string.text_restore_game))
            }
        }
    }
}

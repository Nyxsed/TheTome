package ru.nyxsed.thetome.features.pickrole.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.nyxsed.thetome.R
import ru.nyxsed.thetome.core.domain.models.GameState
import ru.nyxsed.thetome.core.domain.models.ItemType
import ru.nyxsed.thetome.core.domain.models.Role
import ru.nyxsed.thetome.core.presentation.components.BackHandlerInterceptor
import ru.nyxsed.thetome.core.presentation.components.CircleItem
import ru.nyxsed.thetome.core.presentation.components.GameScreenBackground
import ru.nyxsed.thetome.ui.theme.DarkPurple

@Composable
fun PickRoleScreen(
    viewModel: PickRoleViewModel = hiltViewModel(),
    onRoleClicked: (Int, List<Role?>?) -> Unit,
    onNewGameClicked: () -> Unit,
    onDoublePressBack: () -> Unit,
) {
    val state by viewModel.state.collectAsState()
    val currentPlayerIndex by viewModel.currentPlayerIndex.collectAsState()
    val shuffledRoles by viewModel.shuffledRoles.collectAsState()
    val currentPlayerName by viewModel.currentPlayerName.collectAsState()

    BackHandlerInterceptor(onDoublePressed = onDoublePressBack)

    PickRoleContent(
        state = state,
        shuffledRoles = shuffledRoles,
        currentPlayerIndex = currentPlayerIndex,
        currentPlayerName = currentPlayerName,
        onNewGameClicked = {
            viewModel.saveGameState()
            onNewGameClicked()
        },
        onCurrentPlayerNameChange = { name, playerIndex ->
            viewModel.changeName(name, playerIndex)
        },
        onRoleClicked = { role, playerIndex ->
            viewModel.pickRole(role, playerIndex)
            onRoleClicked(role.ability, listOf(role))
        },
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PickRoleContent(
    state: GameState,
    shuffledRoles: List<Role>?,
    currentPlayerIndex: Int,
    currentPlayerName: String,
    onCurrentPlayerNameChange: (String, Int) -> Unit,
    onNewGameClicked: () -> Unit,
    onRoleClicked: (Role, Int) -> Unit,
) {
    GameScreenBackground(null)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 48.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.text_enter_name),
            fontSize = 20.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = currentPlayerName,
            enabled = currentPlayerIndex != shuffledRoles?.size,
            onValueChange = { onCurrentPlayerNameChange(it, currentPlayerIndex) },
            label = { Text(stringResource(R.string.text_name)) }
        )

        Spacer(modifier = Modifier.height(28.dp))
        Text(
            text = stringResource(R.string.text_choose_role),
            fontSize = 20.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            maxItemsInEachRow = 5
        ) {
            shuffledRoles?.forEach { role ->
                val playerByRole = state.getPlayerByRole(role)
                CircleItem(
                    itemType = ItemType.PLAYER_CIRCLE,
                    size = 70.dp,
                    isEnabled = playerByRole == null,
                    haveGhostVote = false,
                    onClick = {
                        if (playerByRole == null) {
                            onRoleClicked(role, currentPlayerIndex)
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            onClick = onNewGameClicked,
            colors = ButtonDefaults.buttonColors().copy(containerColor = DarkPurple)
        ) {
            Text(
                text = stringResource(R.string.text_start_game),
                color = Color.White,
                fontSize = 18.sp
            )
        }
    }
}
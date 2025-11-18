package ru.nyxsed.thetome.features.game.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.nyxsed.thetome.R
import ru.nyxsed.thetome.core.domain.models.Player
import ru.nyxsed.thetome.core.domain.models.Role
import ru.nyxsed.thetome.core.domain.models.Token
import ru.nyxsed.thetome.core.presentation.components.CircleItem

@Composable
fun TokenPickerDialog(
    target: Player?,
    chosenRoles: List<Role>?,
    players: List<Player>?,
    sceneryTokens: List<Token>,
    onPickToken: (Token) -> Unit,
    onDismiss: () -> Unit,
) {
    if (target == null) return

    val availableTokens = remember(chosenRoles, players) {
        val chosenRolesTokens = chosenRoles?.flatMap { it.tokens } ?: emptyList()
        val filteredChosenRolesTokens = chosenRolesTokens.filter { token -> players?.none { token in it.tokens } == true }
        sceneryTokens + filteredChosenRolesTokens
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.text_choose_token)) },
        text = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center // центрируем FlowRow
            ) {
                FlowRow(
                    modifier = Modifier.wrapContentWidth(), // FlowRow по ширине содержимого
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    availableTokens.forEach { token ->
                        CircleItem(
                            size = 70.dp,
                            backgroundColor = Color.DarkGray,
                            bottomText = stringResource(token.nameResId),
                            onClick = { onPickToken(token) }
                        )
                    }
                }
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismiss) { Text(stringResource(R.string.text_cancel)) }
        }
    )
}
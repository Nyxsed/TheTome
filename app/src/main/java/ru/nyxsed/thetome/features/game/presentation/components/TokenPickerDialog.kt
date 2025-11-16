package ru.nyxsed.thetome.features.game.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.nyxsed.thetome.core.domain.models.Player
import ru.nyxsed.thetome.core.domain.models.Role
import ru.nyxsed.thetome.core.domain.models.Token
import ru.nyxsed.thetome.core.presentation.components.CircleItem

@Composable
fun TokenPickerDialog(
    target: Player?,
    chosenRoles: List<Role>?,
    players: List<Player>?,
    onPickToken: (Token) -> Unit,
    onDismiss: () -> Unit,
) {
    if (target == null) return

    val availableTokens = remember(chosenRoles, players) {
        val all = chosenRoles?.flatMap { it.tokens } ?: emptyList()
        all.filter { token -> players?.none { token in it.tokens } == true }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Choose token") },
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
                            bottomText = token.name,
                            onClick = { onPickToken(token) }
                        )
                    }
                }
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}
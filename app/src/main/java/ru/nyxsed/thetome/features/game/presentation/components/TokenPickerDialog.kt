package ru.nyxsed.thetome.features.game.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.nyxsed.thetome.R
import ru.nyxsed.thetome.core.domain.models.ItemType
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
        // 1. Собираем все токены из выбранных ролей (с повторениями)
        val chosenRolesTokens = chosenRoles?.flatMap { it.tokens } ?: emptyList()

        // 2. Считаем, сколько уже назначено игрокам каждого токена
        val assignedCounts: Map<Token, Int> = players
            ?.flatMap { it.tokens }
            ?.groupingBy { it }
            ?.eachCount()
            ?: emptyMap()

        // 3. Для каждого токена из ролей вычитаем назначенные
        val remainingRoleTokens: List<Token> =
            chosenRolesTokens
                .groupingBy { it }
                .eachCount()
                .flatMap { (token, totalCount) ->
                    val used = assignedCounts[token] ?: 0
                    val remaining = totalCount - used
                    if (remaining > 0) List(remaining) { token } else emptyList()
                }

        // 4. Добавляем sceneryTokens
        (sceneryTokens + remainingRoleTokens).distinct()
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
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    availableTokens.forEach { token ->
                        CircleItem(
                            itemType = ItemType.TOKEN_CIRCLE,
                            size = 70.dp,
                            centerIcon = token.iconRes,
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
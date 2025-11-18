package ru.nyxsed.thetome.features.game.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.nyxsed.thetome.core.domain.models.Role
import ru.nyxsed.thetome.core.presentation.components.CircleItem


@Composable
fun DemonBluff(
    demonBluffRoles: List<Role?>,
    availableRoles: List<Role>,
    onChangeBluffs: (List<Role?>) -> Unit,
) {
    var dialogIndex by remember { mutableStateOf<Int?>(null) }

    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        (0 until 3).forEach { index ->
            val role = demonBluffRoles.getOrNull(index)
            val title = role?.roleName?.let { stringResource(it) } ?: "—"
            CircleItem(
                size = 40.dp,
                backgroundColor = Color.DarkGray,
                centerIcon = role?.iconRes,
                bottomText = title,
                onClick = { dialogIndex = index }
            )
        }
    }

    dialogIndex?.let { index ->
        RolePickerDialog(
            availableRoles = availableRoles,
            onSelectRole = { newRole ->
                val updated = demonBluffRoles.toMutableList()
                while (updated.size <= index) updated.add(null)
                updated[index] = newRole
                onChangeBluffs(updated)
                dialogIndex = null
            },
            onDismiss = { dialogIndex = null }
        )
    }
}
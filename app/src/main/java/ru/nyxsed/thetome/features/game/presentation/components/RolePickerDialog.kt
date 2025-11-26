package ru.nyxsed.thetome.features.game.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.nyxsed.thetome.R
import ru.nyxsed.thetome.core.domain.models.ItemType
import ru.nyxsed.thetome.core.domain.models.Role
import ru.nyxsed.thetome.core.presentation.components.CircleItem

@Composable
fun RolePickerDialog(
    availableRoles: List<Role>?,
    sceneryRoles: List<Role>? = emptyList(),
    onSelectRole: (Role?) -> Unit,
    onDismiss: () -> Unit,
) {
    if (availableRoles.isNullOrEmpty()) return

    var isChecked by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.text_pick_a_role)) },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (!sceneryRoles.isNullOrEmpty()) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(stringResource(R.string.text_all))
                        Spacer(Modifier.width(8.dp))
                        Switch(
                            checked = isChecked,
                            onCheckedChange = { isChecked = it }
                        )

                    }
                }

                FlowRow(
                    modifier = Modifier.wrapContentWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CircleItem(
                        itemType = ItemType.PLAYER_CIRCLE,
                        size = 70.dp,
                        centerText = stringResource(R.string.text_no_role),
                        onClick = { onSelectRole(null) }
                    )

                    val showRoles = if (isChecked) sceneryRoles else availableRoles
                    showRoles?.forEach { role ->
                        CircleItem(
                            itemType = ItemType.PLAYER_CIRCLE,
                            size = 70.dp,
                            centerIcon = role.iconRes,
                            bottomText = stringResource(role.roleName),
                            onClick = { onSelectRole(role) }
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
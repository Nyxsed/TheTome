package ru.nyxsed.thetome.features.game.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.nyxsed.thetome.core.domain.models.Role
import ru.nyxsed.thetome.core.presentation.components.CircleItem
import kotlin.collections.forEach

@Composable
fun RolePickerDialog(
    availableRoles: List<Role>?,
    onSelectRole: (Role?) -> Unit,
    onDismiss: () -> Unit,
) {
    if (availableRoles.isNullOrEmpty()) return

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Pick a role") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FlowRow(
                    modifier = Modifier.wrapContentWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    CircleItem(
                        size = 70.dp,
                        backgroundColor = Color.DarkGray,
                        centerText = "No Role",
                        onClick = { onSelectRole(null) }
                    )

                    availableRoles.forEach { role ->
                        CircleItem(
                            size = 70.dp,
                            backgroundColor = Color.DarkGray,
                            bottomText = stringResource(role.roleName),
                            onClick = { onSelectRole(role) }
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
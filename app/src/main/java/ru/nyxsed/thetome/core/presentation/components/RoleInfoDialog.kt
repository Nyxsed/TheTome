package ru.nyxsed.thetome.core.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.nyxsed.thetome.R
import ru.nyxsed.thetome.core.domain.models.Role

@Composable
fun RoleInfoDialog(
    role: Role,
    onDismiss: () -> Unit,
) {
    val roleName = stringResource(role.roleName)
    val roleAbility = stringResource(role.ability)
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = roleName) },
        text = {
            Text(text = roleAbility)
        },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text(stringResource(R.string.text_ok)) }
        },
    )
}
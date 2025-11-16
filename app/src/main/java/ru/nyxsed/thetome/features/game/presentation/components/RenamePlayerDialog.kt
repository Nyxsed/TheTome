package ru.nyxsed.thetome.features.game.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import ru.nyxsed.thetome.core.domain.models.Player

@Composable
fun RenamePlayerDialog(
    target: Player?,
    name: String,
    onNameChange: (String) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    if (target == null) return

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Rename player") },
        text = {
            OutlinedTextField(
                value = name,
                onValueChange = onNameChange,
                label = { Text("Name") },
                singleLine = true
            )
        },
        confirmButton = {
            TextButton(onClick = onConfirm) { Text("Ok") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}
package ru.nyxsed.thetome.features.game.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.nyxsed.thetome.R

@Composable
fun NotesDialog(
    notes: String,
    onNotesChange: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(stringResource(R.string.text_notes)) },
        text = {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                value = notes,
                onValueChange = { onNotesChange(it) },
                maxLines = 10
            )
        },
        confirmButton = {
            TextButton(onClick = { onDismiss() }) { Text(stringResource(R.string.text_ok)) }
        }
    )
}
package ru.nyxsed.thetome.features.game.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.nyxsed.thetome.R

@Composable
fun EditNotesDialog(
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
                maxLines = 10,
                colors = OutlinedTextFieldDefaults.colors().copy(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )
        },
        confirmButton = {
            TextButton(onClick = { onDismiss() }) { Text(stringResource(R.string.text_ok)) }
        }
    )
}
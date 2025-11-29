package ru.nyxsed.thetome.features.game.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.nyxsed.thetome.R
import ru.nyxsed.thetome.core.domain.models.Player

@Composable
fun DeletePlayerDialog(
    player: Player?,
    onYesClicked: (Player) -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(stringResource(R.string.text_delete_player_qm)) },
        confirmButton = {
            TextButton(onClick = { onYesClicked(player!!) }) { Text(stringResource(R.string.text_yes)) }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) { Text(stringResource(R.string.text_cancel)) }
        },
    )
}
package ru.nyxsed.thetome.features.game.presentation.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import ru.nyxsed.thetome.R

@Composable
fun ShareDialog(
    titleRes: Int?,
    qrBitmap: Bitmap?,
    onUrlClicked: () -> Unit,
    onDismiss: () -> Unit
) {
    val title = titleRes?.let { stringResource(titleRes) } ?: ""
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(title) },
        text = {
            qrBitmap?.let {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            onUrlClicked()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier.fillMaxWidth(),
                        bitmap = it.asImageBitmap(),
                        contentDescription = "QR Code",
                        contentScale = ContentScale.FillWidth
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { onDismiss() }) { Text(stringResource(R.string.text_ok)) }
        }
    )
}

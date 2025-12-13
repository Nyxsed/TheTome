package ru.nyxsed.thetome.features.game.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.nyxsed.thetome.R
import ru.nyxsed.thetome.core.data.RoleProvider.Djinn
import ru.nyxsed.thetome.core.domain.models.ItemType
import ru.nyxsed.thetome.core.domain.models.Jinx
import ru.nyxsed.thetome.core.presentation.components.CircleItem

@Composable
fun JinxDialog(
    jinxes: List<Jinx>,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(stringResource(R.string.text_jinxes)) },
        text = {
            jinxes.forEach { jinx ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CircleItem(
                        itemType = ItemType.PLAYER_CIRCLE,
                        size = 40.dp,
                        bottomText = stringResource(jinx.roles.first().roleName),
                        centerIcon = jinx.roles.first().iconRes,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    CircleItem(
                        itemType = ItemType.PLAYER_CIRCLE,
                        size = 40.dp,
                        bottomText = stringResource(jinx.roles.last().roleName),
                        centerIcon = jinx.roles.last().iconRes,
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = stringResource(jinx.descriptionResId))
                }

            }
        },
        confirmButton = {
            TextButton(onClick = { onDismiss() }) { Text(stringResource(R.string.text_ok)) }
        }
    )
}
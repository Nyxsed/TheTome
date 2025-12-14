package ru.nyxsed.thetome.features.game.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import ru.nyxsed.thetome.R
import ru.nyxsed.thetome.core.domain.models.ItemType
import ru.nyxsed.thetome.core.domain.models.Jinx
import ru.nyxsed.thetome.core.presentation.components.CircleItem
import ru.nyxsed.thetome.core.presentation.components.CustomVerticalScrollbar

@Composable
fun JinxDialog(
    jinxes: List<Jinx>,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = stringResource(R.string.text_jinxes)
            )
        },
        text = {
            val scrollState = rememberScrollState()
            val needsScrollbar = scrollState.maxValue > 0

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = if (jinxes.size <= 2) max(200.dp, 80.dp * jinxes.size) else 600.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(scrollState)
                        .padding(end = if (needsScrollbar) 8.dp else 0.dp) // Отступ только если есть скроллбар
                ) {
                    Spacer(modifier = Modifier.height(4.dp))
                    jinxes.forEachIndexed { index, jinx ->
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
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
                                Text(
                                    text = stringResource(jinx.descriptionResId),
                                    modifier = Modifier.weight(1f),
                                    softWrap = true
                                )
                            }

                            if (index < jinxes.size - 1) {
                                Spacer(modifier = Modifier.height(16.dp))
                                HorizontalDivider(
                                    modifier = Modifier.fillMaxWidth(),
                                    thickness = 2.dp,
                                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                }

                if (needsScrollbar) {
                    CustomVerticalScrollbar(
                        scrollState = scrollState,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .fillMaxHeight()
                            .width(8.dp)
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(stringResource(R.string.text_ok))
            }
        },
        modifier = Modifier.padding(vertical = 24.dp)
    )
}
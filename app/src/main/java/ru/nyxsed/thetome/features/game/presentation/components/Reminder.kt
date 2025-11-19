package ru.nyxsed.thetome.features.game.presentation.components

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.nyxsed.thetome.core.domain.models.Action
import ru.nyxsed.thetome.core.domain.models.ItemType
import ru.nyxsed.thetome.core.presentation.components.CircleItem

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Reminder(
    modifier: Modifier = Modifier,
    action: Action?,
    onBeforeClicked: () -> Unit,
    onAfterClicked: () -> Unit,
) {
    // направление листания
    var direction by remember { mutableStateOf(SlideDirection.RIGHT) }

    Row(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        SmallRoundIconButton(
            onClick = {
                direction = SlideDirection.LEFT
                onBeforeClicked()
            },
            icon = {
                Icon(
                    contentDescription = "before",
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        )

        Column(
            modifier = Modifier
                .padding(8.dp)
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            AnimatedContent(
                targetState = action,
                transitionSpec = {
                    val slideIn = when (direction) {
                        SlideDirection.LEFT -> slideInHorizontally { -it }
                        SlideDirection.RIGHT -> slideInHorizontally { it }
                    }

                    val slideOut = when (direction) {
                        SlideDirection.LEFT -> slideOutHorizontally { it }
                        SlideDirection.RIGHT -> slideOutHorizontally { -it }
                    }

                    (slideIn + fadeIn() togetherWith slideOut + fadeOut())
                        .using(
                            SizeTransform(
                                clip = false,
                                sizeAnimationSpec = { _, _ -> tween(0) } // ключевая строка
                            )
                        )
                },
                label = "action_transition"
            ) { animatedAction ->

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    if (animatedAction?.role != null) {
                        CircleItem(
                            itemType = ItemType.PLAYER_CIRCLE,
                            size = 80.dp,
                            centerIcon = animatedAction.role.iconRes,
                            bottomText = stringResource(animatedAction.role.roleName),
                        )
                    }

                    animatedAction?.let {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = stringResource(it.actionResId),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        SmallRoundIconButton(
            onClick = {
                direction = SlideDirection.RIGHT
                onAfterClicked()
            },
            icon = {
                Icon(
                    contentDescription = "after",
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        )
    }
}

enum class SlideDirection { LEFT, RIGHT }
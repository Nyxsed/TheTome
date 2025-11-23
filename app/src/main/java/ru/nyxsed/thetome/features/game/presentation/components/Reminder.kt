package ru.nyxsed.thetome.features.game.presentation.components

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    tint = Color.White
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
                            size = 60.dp,
                            centerIcon = animatedAction.role.iconRes,
                            bottomText = stringResource(animatedAction.role.roleName),
                        )
                    }

                    animatedAction?.let { action ->
                        val text = stringResource(action.actionResId)

                        val fontSize = when {
                            text.length > 200 -> 12.sp
                            text.length > 25 -> 14.sp
                            else -> 16.sp
                        }

                        val lineHeight = when {
                            text.length > 200 -> 18.sp
                            text.length > 25 -> 22.sp
                            else -> 28.sp
                        }

                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = text,
                            textAlign = TextAlign.Center,
                            fontSize = fontSize,
                            lineHeight = lineHeight
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
                    tint = Color.White
                )
            }
        )
    }
}

enum class SlideDirection { LEFT, RIGHT }
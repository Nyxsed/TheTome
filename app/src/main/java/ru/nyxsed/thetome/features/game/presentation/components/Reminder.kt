package ru.nyxsed.thetome.features.game.presentation.components

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import ru.nyxsed.thetome.core.domain.models.GamePhase
import ru.nyxsed.thetome.core.domain.models.ItemType
import ru.nyxsed.thetome.core.presentation.components.CircleItem

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Reminder(
    modifier: Modifier = Modifier,
    action: Action?,
    actions: List<Action>,
    currentPhase: GamePhase?,
    onBeforeClicked: () -> Unit,
    onAfterClicked: () -> Unit,
) {
    var direction by remember { mutableStateOf(SlideDirection.RIGHT) }

    val filteredActions = remember(actions) {
        actions.filter { it.role != null }
    }

    val currentFilteredIndex = remember(action, filteredActions) {
        if (action != null && action.role != null) {
            filteredActions.indexOfFirst { it == action }.takeIf { it >= 0 } ?: 0
        } else {
            666
        }
    }

    Column(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            contentAlignment = Alignment.Center
        ) {
            if (filteredActions.isNotEmpty()) {
                RoleIconsRow(
                    actions = filteredActions,
                    currentActionIndex = currentFilteredIndex,
                    onRoleClick = { clickedAction ->
                        val clickedIndexInOriginal = actions.indexOf(clickedAction)
                        val currentIndexInOriginal = actions.indexOf(action).takeIf { it >= 0 } ?: 0

                        direction = if (clickedIndexInOriginal > currentIndexInOriginal) {
                            SlideDirection.RIGHT
                        } else {
                            SlideDirection.LEFT
                        }

                        if (clickedIndexInOriginal > currentIndexInOriginal) {
                            repeat(clickedIndexInOriginal - currentIndexInOriginal) { onAfterClicked() }
                        } else if (clickedIndexInOriginal < currentIndexInOriginal) {
                            repeat(currentIndexInOriginal - clickedIndexInOriginal) { onBeforeClicked() }
                        }
                    }
                )
            } else {
                Box(modifier = Modifier.fillMaxSize())
            }
        }

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
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
                        .padding(horizontal = 8.dp)
                        .weight(1f)
                        .wrapContentHeight(),
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
                                        sizeAnimationSpec = { _, _ -> tween(0) }
                                    )
                                )
                        },
                        label = "action_transition"
                    ) { animatedAction ->

                        Column(
                            modifier = Modifier.wrapContentHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            animatedAction?.let { action ->

                                val text = stringResource(action.res)

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

                                val scrollState = rememberScrollState()

                                Column(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .verticalScroll(scrollState),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = text,
                                        color = if (currentPhase in listOf(
                                                GamePhase.FIRST_NIGHT,
                                                GamePhase.SECOND_NIGHT
                                            )
                                        ) Color.White else Color.Black,
                                        textAlign = TextAlign.Center,
                                        fontSize = fontSize,
                                        lineHeight = lineHeight
                                    )
                                }
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
    }
}

@Composable
private fun RoleIconsRow(
    actions: List<Action>,
    currentActionIndex: Int,
    onRoleClick: (Action) -> Unit
) {
    val scrollState = rememberScrollState()

    LaunchedEffect(currentActionIndex) {
        if (actions.isNotEmpty()) {
            val itemWidth = 50.dp.value
            val spacing = 8.dp.value
            val containerWidth = scrollState.maxValue.toFloat()
            val targetPosition = (currentActionIndex * (itemWidth + spacing) - containerWidth / 2 + itemWidth / 2).toInt()
            scrollState.animateScrollTo(targetPosition.coerceIn(0, scrollState.maxValue))
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(8.dp))

        actions.forEachIndexed { index, action ->
            RoleIconItem(
                action = action,
                isSelected = index == currentActionIndex,
                onClick = { onRoleClick(action) }
            )

            if (index < actions.size - 1) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }

        Spacer(modifier = Modifier.width(8.dp))
    }
}

@Composable
private fun RoleIconItem(
    action: Action,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick() }
            .padding(2.dp)
    ) {
        CircleItem(
            modifier = Modifier.align(Alignment.Center),
            itemType = ItemType.PLAYER_CIRCLE,
            size = 48.dp,
            centerIcon = action.role?.iconRes,
            isSelected = isSelected,
            bottomText = action.role?.roleName?.let { stringResource(it) } ?: ""
        )
    }
}

enum class SlideDirection { LEFT, RIGHT }
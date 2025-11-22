package ru.nyxsed.thetome.core.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.nyxsed.thetome.R
import ru.nyxsed.thetome.core.domain.models.ItemType


@Composable
fun CircleItem(
    modifier: Modifier = Modifier,
    size: Dp,
    itemType: ItemType,
    topText: String? = null,
    bottomText: String? = null,
    centerText: String? = null,
    @DrawableRes centerIcon: Int? = null,
    isEnabled: Boolean = true,
    haveGhostVote: Boolean = true,
    isSelected: Boolean = false,
    onClick: (() -> Unit)? = null,
    onLongClick: (() -> Unit)? = null,
) {
    val backgroundImage = when (itemType) {
        ItemType.PLAYER_CIRCLE -> painterResource(id = R.drawable.bg_player)
        ItemType.TOKEN_CIRCLE -> painterResource(id = R.drawable.bg_token)
    }
    val textColor = when (itemType) {
        ItemType.PLAYER_CIRCLE -> Color.Black
        ItemType.TOKEN_CIRCLE -> Color.White
    }

    val animatedBrushColor by animateColorAsState(
        targetValue = if (isSelected) Color.Red else Color.Black,
        animationSpec = tween(durationMillis = 250)
    )

    val density = LocalDensity.current
    val baseRadius = with(density) { size.toPx() / 2f }
    val animatedShadowRadius by animateFloatAsState(
        targetValue = if (isSelected) baseRadius * 1.15f else baseRadius * 1.1f,
        animationSpec = tween(durationMillis = 250)
    )

    val haptic = LocalHapticFeedback.current
    val gestureModifier = if (onClick != null || onLongClick != null) {
        Modifier.pointerInput(onClick, onLongClick) {
            detectTapGestures(
                onTap = {
                    onClick?.invoke()
                },
                onLongPress = {
                    onLongClick?.invoke()
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                }
            )
        }
    } else {
        Modifier
    }

    Box(
        modifier = modifier
            .then(gestureModifier)
            .size(size)
            .drawBehind {
                drawCircle(
                    brush = Brush.radialGradient(
                        Pair(0.93f, animatedBrushColor),
                        Pair(1.00f, Color.Transparent),
                        radius = animatedShadowRadius,
                    ),
                    radius = animatedShadowRadius,
                )

            },
        contentAlignment = Alignment.Center
    ) {
        val colorFilter = if (!isEnabled) ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) }) else null
        Image(
            modifier = Modifier
                .matchParentSize(),
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            colorFilter = colorFilter
        )

        if (centerIcon != null) {
            Image(
                painter = painterResource(centerIcon),
                contentDescription = null,
                modifier = Modifier.size(size),
                colorFilter = colorFilter
            )
        }
        if (!topText.isNullOrEmpty()) {
            TextArc(text = topText, circleSize = size, color = textColor, position = ArcPosition.TOP)
        }
        if (!bottomText.isNullOrEmpty()) {
            TextArc(text = bottomText, circleSize = size, color = textColor, position = ArcPosition.BOTTOM)
        }
        if (!centerText.isNullOrEmpty()) {
            Text(centerText, fontSize = 6.sp, color = textColor, textAlign = TextAlign.Center, lineHeight = 6.sp)
        }

        if (!isEnabled && haveGhostVote) {
            Box(
                modifier = Modifier
                    .size(size / 2)
                    .background(Color.White.copy(alpha = 0.8f), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CircleItemPlayerPreview() {
    val sizes = listOf(200.dp, 100.dp, 90.dp, 80.dp, 70.dp, 60.dp, 50.dp)
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        sizes.forEach { size ->
            Column {
                Text("size = $size", style = MaterialTheme.typography.labelLarge)
                CircleItem(
                    size = size,
                    itemType = ItemType.PLAYER_CIRCLE,
                    topText = "Nikita",
                    bottomText = "Washerwoman",
                    centerIcon = R.drawable.icon_washerwoman
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CircleItemTokenPreview() {
    val sizes = listOf(40.dp, 30.dp, 20.dp)
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        sizes.forEach { size ->
            Column {
                Text("size = $size", style = MaterialTheme.typography.labelLarge)
                CircleItem(
                    size = size,
                    itemType = ItemType.TOKEN_CIRCLE,
                    bottomText = "Washerwoman",
                    centerIcon = R.drawable.icon_washerwoman
                )
            }
        }
    }
}
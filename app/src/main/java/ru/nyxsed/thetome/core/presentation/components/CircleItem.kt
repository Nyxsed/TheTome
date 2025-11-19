package ru.nyxsed.thetome.core.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
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
    menuItems: List<CircleMenuItem> = emptyList(),
    onClick: (() -> Unit)? = null,
    onLongClick: (() -> Unit)? = null,
    isClickableEnabled: Boolean = true,
    isEnabled: Boolean = true,
    haveGhostVote: Boolean = true,
    isSelected: Boolean = false,
    isAddToken: Boolean = false,
) {
    var isMenuExpanded by remember { mutableStateOf(false) }
    val backgroundImage = when (itemType) {
        ItemType.PLAYER_CIRCLE -> painterResource(id = R.drawable.bg_player)
        ItemType.TOKEN_CIRCLE -> painterResource(id = R.drawable.bg_token)
    }
    val textColor = when (itemType) {
        ItemType.PLAYER_CIRCLE -> Color.Black
        ItemType.TOKEN_CIRCLE -> Color.White
    }

    val haptic = LocalHapticFeedback.current

    val gestureModifier = if (onClick != null || onLongClick != null || menuItems.isNotEmpty()) {
        Modifier.pointerInput(onClick, onLongClick, menuItems) {
            detectTapGestures(
                onTap = {
                    if (menuItems.isEmpty()) {
                        onClick?.invoke()
                    } else {
                        isMenuExpanded = true
                    }
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
                if (!isAddToken) {
                    val brushColor = if (isSelected) Color.Red else Color.Black
                    val baseRadius = size.toPx() / 2
                    val radius = if (isSelected) baseRadius * 1.15f else baseRadius * 1.1f
                    drawCircle(
                        brush = Brush.radialGradient(
                            Pair(0.93f, brushColor),
                            Pair(1.00f, Color.Transparent),
                            radius = radius,
                        ),
                        radius = radius,
                    )
                }
            },
        contentAlignment = Alignment.Center
    ) {
        val colorFilter = if (!isEnabled) ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) }) else null
        Image(
            modifier = Modifier
                .matchParentSize()
                .alpha(if (isAddToken) 0.05f else 1f),
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

        if (menuItems.isNotEmpty()) {
            DropdownMenu(
                expanded = isMenuExpanded,
                onDismissRequest = { isMenuExpanded = false }
            ) {
                menuItems.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item.title) },
                        onClick = {
                            isMenuExpanded = false
                            item.onClick()
                        }
                    )
                }
            }
        }
    }
    if (!isEnabled && haveGhostVote) {
        Box(
            modifier = modifier
                .size(size / 2)
                .background(Color.White.copy(alpha = 0.8f), shape = CircleShape)
        )
    }
}

data class CircleMenuItem(
    val title: String,
    val onClick: () -> Unit,
)

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
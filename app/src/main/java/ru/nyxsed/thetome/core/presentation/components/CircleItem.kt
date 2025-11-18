package ru.nyxsed.thetome.core.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
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
    isClickableEnabled: Boolean = true,
    isEnabled: Boolean = true,
    haveGhostVote: Boolean = true,
    isSelected: Boolean = false,
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
    Box(
        modifier = modifier
            .size(size)
            .drawBehind {
                if (isSelected) {
                    val glowColor = Color.Green
                    val radius = size.toPx() / 2
                    drawCircle(
                        color = glowColor,
                        radius = radius + 10f,
                        alpha = 0.8f
                    )
                }
            }
            .then(
                if (onClick != null || menuItems.isNotEmpty()) {
                    Modifier.clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        enabled = isClickableEnabled
                    ) {
                        if (menuItems.isEmpty()) {
                            onClick?.invoke()
                        } else {
                            isMenuExpanded = true
                        }
                    }
                } else Modifier
            ),
        contentAlignment = Alignment.Center
    ) {
        val colorFilter = if (!isEnabled) ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) }) else null
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize(),
            colorFilter = colorFilter
        )

        if (centerIcon != null) {
            Image(
                painter = painterResource(centerIcon),
                contentDescription = null,
                modifier = Modifier.size(size * 0.9f),
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
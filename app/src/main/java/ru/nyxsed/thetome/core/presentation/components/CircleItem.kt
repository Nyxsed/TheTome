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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp

@Composable
fun CircleItem(
    modifier: Modifier = Modifier,
    size: Dp,
    backgroundColor: Color,
    topText: String? = null,
    bottomText: String? = null,
    centerText: String? = null,
    @DrawableRes centerIcon: Int? = null,
    menuItems: List<CircleMenuItem> = emptyList(),
    onClick: (() -> Unit)? = null,
    isClickableEnabled: Boolean = true,
    isAlive: Boolean = true,
    haveGhostVote: Boolean = true,
) {
    var isMenuExpanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .size(size)
            .background(backgroundColor, CircleShape)
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
        if (centerIcon != null) {
            Image(
                painter = painterResource(centerIcon),
                contentDescription = null,
                modifier = Modifier.size(size * 0.9f)
            )
        }
        if (!topText.isNullOrEmpty()) {
            TextArc(text = topText, circleSize = size, position = ArcPosition.TOP)
        }
        if (!bottomText.isNullOrEmpty()) {
            TextArc(text = bottomText, circleSize = size, position = ArcPosition.BOTTOM)
        }
        if (!centerText.isNullOrEmpty()) {
            Text(centerText, fontSize = 6.sp, color = Color.Black, textAlign = TextAlign.Center, lineHeight = 6.sp)
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
    if (!isAlive && haveGhostVote) {
        Box(
            modifier = modifier
                .size(size/2)
                .background(Color.White, CircleShape)
        ) {

        }
    }
}

data class CircleMenuItem(
    val title: String,
    val onClick: () -> Unit,
)
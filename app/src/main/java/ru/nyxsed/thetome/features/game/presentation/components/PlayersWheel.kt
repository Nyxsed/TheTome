package ru.nyxsed.thetome.features.game.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import ru.nyxsed.thetome.core.domain.models.ItemType
import ru.nyxsed.thetome.core.domain.models.Player
import ru.nyxsed.thetome.core.presentation.components.CircleItem
import kotlin.math.*

@Composable
fun PlayersWheel(
    players: List<Player>,
    modifier: Modifier = Modifier,
    minCircleSize: Dp = 60.dp,
    maxCircleSize: Dp = 100.dp,
    onClick: (Player) -> Unit,
    onLongClick: (Player) -> Unit,
) {
    if (players.isEmpty()) return

    val density = LocalDensity.current
    var boxWidthPx by remember { mutableStateOf(0f) }
    var boxHeightPx by remember { mutableStateOf(0f) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .onSizeChanged { size ->
                boxWidthPx = size.width.toFloat()
                boxHeightPx = size.height.toFloat()
            }
    ) {
        if (boxWidthPx == 0f || boxHeightPx == 0f) return@Box

        val centerX = boxWidthPx / 2f
        val centerY = boxHeightPx / 2f

        val maxCirclePx = with(density) { maxCircleSize.toPx() }
        val radiusPx = (min(boxWidthPx, boxHeightPx) - maxCirclePx) / 2f

        // Размер кружка зависит от количества игроков
        val circlePx = (2 * Math.PI * radiusPx / players.size * 0.8).toFloat()
        val circleSize = circlePx.coerceIn(
            with(density) { minCircleSize.toPx() },
            maxCirclePx
        ).let { with(density) { it.toDp() } }

        players.forEachIndexed { index, player ->
            key(player.id) {
                val angleRad = Math.toRadians((-90f + 360f / players.size * index).toDouble())
                val x = centerX + radiusPx * cos(angleRad) - with(density) { circleSize.toPx() } / 2f
                val y = centerY + radiusPx * sin(angleRad) - with(density) { circleSize.toPx() } / 2f

                CircleItem(
                    modifier = Modifier.offset { IntOffset(x.roundToInt(), y.roundToInt()) },
                    size = circleSize,
                    itemType = ItemType.PLAYER_CIRCLE,
                    topText = player.name ?: "",
                    bottomText = player.role?.roleName?.let { stringResource(it) } ?: "",
                    centerIcon = player.role?.iconRes,
                    isEnabled = player.isAlive,
                    haveGhostVote = player.haveGhostVote,
                    onClick = { onClick(player) },
                    onLongClick = { onLongClick(player) }
                )
            }
        }
    }
}
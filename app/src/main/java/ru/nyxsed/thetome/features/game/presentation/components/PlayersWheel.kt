package ru.nyxsed.thetome.features.game.presentation.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import ru.nyxsed.thetome.core.domain.models.ItemType
import ru.nyxsed.thetome.core.domain.models.Player
import ru.nyxsed.thetome.core.presentation.components.CircleItem
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.roundToInt
import kotlin.math.sin

@Composable
fun PlayersWheel(
    players: List<Player>,
    modifier: Modifier = Modifier,
    minCircleSize: Dp = 60.dp,
    maxCircleSize: Dp = 100.dp,
    minTokenSize: Dp = 20.dp,
    maxTokenSize: Dp = 40.dp,
    onClick: (Player) -> Unit,
    onTokenLongClick: (Player, Int) -> Unit,
    onOrderChanged: (List<Player>) -> Unit = {}
) {
    if (players.isEmpty()) return

    var items by remember { mutableStateOf(players.toList()) }

    LaunchedEffect(players) {
        if (players != items) items = players.toList()
    }

    val density = LocalDensity.current
    var boxWidth by remember { mutableStateOf(0f) }
    var boxHeight by remember { mutableStateOf(0f) }

    var draggingId by remember { mutableStateOf<Int?>(null) }
    var dragOffset by remember { mutableStateOf(Offset.Zero) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .onSizeChanged { size ->
                boxWidth = size.width.toFloat()
                boxHeight = size.height.toFloat()
            }
    ) {
        if (boxWidth == 0f || boxHeight == 0f) return@Box

        val cx = boxWidth / 2f
        val cy = boxHeight / 2f

        val maxCirclePx = with(density) { maxCircleSize.toPx() }
        val maxTokenPx = with(density) { maxTokenSize.toPx() }

        val rawCirclePx =
            (2 * Math.PI * ((min(boxWidth, boxHeight) - (maxCirclePx + maxTokenPx)) / 2f)
                    / items.size * 0.8).toFloat()

        val circlePx = rawCirclePx.coerceIn(
            with(density) { minCircleSize.toPx() },
            maxCirclePx
        )
        val circleDp = with(density) { circlePx.toDp() }

        val rawTokenPx = circlePx * 0.4f
        val tokenPx = rawTokenPx.coerceIn(
            with(density) { minTokenSize.toPx() },
            maxTokenPx
        )
        val tokenDp = with(density) { tokenPx.toDp() }

        val radiusPx = (min(boxWidth, boxHeight) - (circlePx + tokenPx)) / 2f

        val slotCenters = List(items.size) { idx ->
            val ang = Math.toRadians((-90 + 360f / items.size * idx).toDouble())
            Offset(
                (cx + radiusPx * cos(ang)).toFloat(),
                (cy + radiusPx * sin(ang)).toFloat()
            )
        }

        val placeholderIndex =
            if (draggingId == null) -1
            else nearestIndexForOffset(dragOffset, slotCenters)

        val arrangement = run {
            val drId = draggingId
            val list = MutableList<Int?>(items.size) { null }
            if (drId == null) {
                items.forEachIndexed { i, p -> list[i] = p.id }
                return@run list
            }
            val ph = placeholderIndex.coerceIn(0, items.lastIndex)
            list[ph] = drId
            val others = items.filter { it.id != drId }
            var slot = 0
            others.forEach { p ->
                while (slot < list.size && list[slot] != null) slot++
                if (slot < list.size) list[slot] = p.id
            }
            list
        }

        val playerSlot = arrangement.mapIndexedNotNull { idx, id -> id?.let { it to idx } }.toMap()

        items.forEach { player ->
            key(player.id) {
                val isDragging = draggingId == player.id

                val scale by animateFloatAsState(if (isDragging) 1.2f else 1f)

                val targetCenter = if (isDragging)
                    dragOffset
                else {
                    val si = playerSlot[player.id] ?: 0
                    slotCenters[si]
                }

                val animatedCenter by animateOffsetAsState(
                    targetValue = targetCenter,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )

                val topLeft = Offset(animatedCenter.x - circlePx / 2f, animatedCenter.y - circlePx / 2f)

                Box(
                    modifier = Modifier
                        .offset { IntOffset(topLeft.x.roundToInt(), topLeft.y.roundToInt()) }
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                        }
                        .zIndex(if (isDragging) 50f else 0f)
                        .pointerInput(player.id) {
                            detectDragGesturesAfterLongPress(
                                onDragStart = {
                                    draggingId = player.id
                                    val center = slotCenters[items.indexOfFirst { it.id == player.id }]
                                    dragOffset = center
                                },
                                onDrag = { change, drag ->
                                    change.consume()
                                    dragOffset += Offset(drag.x, drag.y)
                                },
                                onDragEnd = {
                                    val from = items.indexOfFirst { it.id == player.id }
                                    val to = nearestIndexForOffset(dragOffset, slotCenters)

                                    if (from != -1 && from != to) {
                                        val newList = items.toMutableList()
                                        val p = newList.removeAt(from)
                                        newList.add(to, p)
                                        items = newList
                                        onOrderChanged(newList)
                                    }

                                    draggingId = null
                                    dragOffset = Offset.Zero
                                },
                                onDragCancel = {
                                    draggingId = null
                                    dragOffset = Offset.Zero
                                }
                            )
                        }
                ) {
                    CircleItem(
                        size = circleDp,
                        itemType = ItemType.PLAYER_CIRCLE,
                        topText = player.name.orEmpty(),
                        bottomText = player.role?.roleName?.let { stringResource(it) }.orEmpty(),
                        centerIcon = player.role?.iconRes,
                        isEnabled = player.isAlive,
                        haveGhostVote = player.haveGhostVote,
                        onClick = { onClick(player) },
                        onLongClick = null
                    )

                    val tokens = player.tokens
                    if (tokens.isNotEmpty()) {
                        val inset = tokenPx * 0.3f
                        val tokenRadius = (circlePx + tokenPx) / 2f - inset
                        tokens.forEachIndexed { ti, token ->
                            val a = 2 * Math.PI / tokens.size * ti
                            val tx = circlePx / 2f + tokenRadius * cos(a) - tokenPx / 2f
                            val ty = circlePx / 2f + tokenRadius * sin(a) - tokenPx / 2f

                            Box(
                                modifier = Modifier.offset { IntOffset(tx.roundToInt(), ty.roundToInt()) }
                            ) {
                                CircleItem(
                                    size = tokenDp,
                                    itemType = ItemType.TOKEN_CIRCLE,
                                    bottomText = stringResource(token.nameResId),
                                    centerIcon = token.iconRes,
                                    onClick = null,
                                    onLongClick = { onTokenLongClick(player, ti) },
                                    isEnabled = true,
                                    haveGhostVote = false
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun nearestIndexForOffset(cur: Offset, centers: List<Offset>): Int {
    var best = 0
    var bestDist = Float.MAX_VALUE
    centers.forEachIndexed { i, c ->
        val dx = cur.x - c.x
        val dy = cur.y - c.y
        val d = dx * dx + dy * dy
        if (d < bestDist) {
            bestDist = d
            best = i
        }
    }
    return best
}

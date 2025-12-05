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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.zIndex
import ru.nyxsed.thetome.core.domain.models.ItemType
import ru.nyxsed.thetome.core.domain.models.Player
import ru.nyxsed.thetome.core.presentation.components.CircleItem
import ru.nyxsed.thetome.ui.theme.TheTomeTheme
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.roundToInt
import kotlin.math.sin

@Composable
fun PlayersWheel(
    modifier: Modifier = Modifier,
    players: List<Player>,
    fabledEnabled: Boolean,
    fabled: Player?,
    onClick: (Player) -> Unit,
    onFabledClick: (Player?) -> Unit,
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

    // Определяем ориентацию экрана
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.screenWidthDp > configuration.screenHeightDp

    // Определяем тип устройства
    val isTablet = configuration.smallestScreenWidthDp >= 600
    val isLargeTablet = configuration.smallestScreenWidthDp >= 720

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

        // Определяем размер круга на основе доступного пространства
        val availableSize = min(boxWidth, boxHeight)

        val circleSizeFactor = when (items.size) {
            5 -> 0.25f
            6 -> 0.22f
            7 -> 0.2f
            8 -> 0.19f
            9 -> 0.18f
            10 -> 0.17f
            11 -> 0.16f
            12 -> 0.155f
            13 -> 0.15f
            14 -> 0.145f
            15 -> 0.14f
            16 -> 0.135f
            17 -> 0.13f
            18 -> 0.125f
            19 -> 0.12f
            20 -> 0.115f
            else -> 0.11f
        }

        val deviceCorrection = when {
            isLargeTablet -> 0.9f
            isTablet -> 0.9f
            isLandscape -> 0.9f
            else -> 1.0f
        }

        val circlePx = availableSize * circleSizeFactor * deviceCorrection
        val circleDp = with(density) { circlePx.toDp() }

        // Размер токена как процент от размера круга
        val tokenPx = circlePx * 0.4f
        val tokenDp = with(density) { tokenPx.toDp() }

        val maxItemSize = circlePx + tokenPx
        val maxPossibleRadius = (availableSize - maxItemSize) / 2f

        val radiusPx = maxPossibleRadius * 0.95f

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

                // fabled
                if (fabledEnabled) {
                    val fabledCirclePx = circlePx * when {
                        isLandscape -> 0.75f
                        else -> 0.9f
                    }
                    val fabledCircleDp = with(density) { fabledCirclePx.toDp() }

                    Box(
                        modifier = Modifier
                            .offset {
                                IntOffset(
                                    (cx - fabledCirclePx / 2f).roundToInt(),
                                    (cy - fabledCirclePx / 2f).roundToInt()
                                )
                            }
                    ) {
                        CircleItem(
                            size = fabledCircleDp,
                            itemType = ItemType.PLAYER_CIRCLE,
                            bottomText = fabled?.role?.roleName?.let { stringResource(it) }.orEmpty(),
                            centerIcon = fabled?.role?.iconRes,
                            onClick = { onFabledClick(fabled) },
                        )

                        val tokens = fabled?.tokens
                        if (tokens?.isNotEmpty() == true) {
                            val inset = tokenPx * 0.3f
                            val tokenRadius = (fabledCirclePx + tokenPx) / 2f - inset
                            tokens.forEachIndexed { ti, token ->
                                val a = 2 * Math.PI / tokens.size * ti
                                val tx = fabledCirclePx / 2f + tokenRadius * cos(a) - tokenPx / 2f
                                val ty = fabledCirclePx / 2f + tokenRadius * sin(a) - tokenPx / 2f

                                Box(
                                    modifier = Modifier.offset { IntOffset(tx.roundToInt(), ty.roundToInt()) }
                                ) {
                                    CircleItem(
                                        size = tokenDp,
                                        itemType = ItemType.TOKEN_CIRCLE,
                                        bottomText = stringResource(token.nameResId),
                                        centerIcon = token.iconRes,
                                        onClick = null,
                                        onLongClick = {
                                            onTokenLongClick(fabled, ti)
                                        },
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

@Preview(
    name = "Phone Portrait 4 players",
    widthDp = 360,
    heightDp = 360,
    showBackground = true
)
@Preview(
    name = "Phone Portrait 8 players",
    widthDp = 360,
    heightDp = 360,
    showBackground = true
)
@Preview(
    name = "Phone Portrait 12 players",
    widthDp = 360,
    heightDp = 360,
    showBackground = true
)
@Preview(
    name = "Phone Landscape 8 players",
    widthDp = 640,
    heightDp = 320,
    showBackground = true
)
@Composable
fun PlayersWheelPreview() {
    val testPlayers4 = (1..4).map {
        Player(
            id = it,
            name = "Player $it",
            isAlive = true,
            role = null,
            tokens = emptyList(),
            haveGhostVote = false
        )
    }

    val testPlayers8 = (1..8).map {
        Player(
            id = it,
            name = "Player $it",
            isAlive = true,
            role = null,
            tokens = emptyList(),
            haveGhostVote = false
        )
    }

    val testPlayers12 = (1..12).map {
        Player(
            id = it,
            name = "Player $it",
            isAlive = true,
            role = null,
            tokens = emptyList(),
            haveGhostVote = false
        )
    }

    TheTomeTheme {
        PlayersWheel(
            players = testPlayers12,
            fabledEnabled = true,
            fabled = null,
            onClick = {},
            onFabledClick = {},
            onTokenLongClick = { _, _ -> },
            onOrderChanged = {}
        )
    }
}
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
import androidx.compose.ui.unit.IntOffset
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
    modifier: Modifier = Modifier,
    players: List<Player>,
    fabledEnabled: Boolean,
    fabled: Player?,
    onClick: (Player) -> Unit,
    onFabledClick: (Player?) -> Unit,
    onTokenLongClick: (Player, Int) -> Unit,
    onOrderChanged: (List<Player>) -> Unit,
    freePositionMode: Boolean = false,
    onPositionChanged: (Player, Float, Float) -> Unit,
) {
    if (players.isEmpty()) return

    // Локальное состояние для порядка игроков (для кругового режима)
    var items by remember { mutableStateOf(players.toList()) }

    // Локальное состояние для позиций игроков (для свободного режима)
    var localPositions by remember { mutableStateOf<Map<Int, Offset>>(emptyMap()) }

    // Синхронизация при изменении внешних данных
    LaunchedEffect(players, freePositionMode) {
        items = players.toList()

        // Инициализируем локальные позиции для свободного режима
        if (freePositionMode) {
            val newPositions = mutableMapOf<Int, Offset>()
            players.forEach { player ->
                if (player.x != null && player.y != null) {
                    newPositions[player.id] = Offset(player.x, player.y)
                }
            }
            if (newPositions != localPositions) {
                localPositions = newPositions
            }
        }
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
        val availableSize = min(boxWidth, boxHeight)

        // Определяем размер элементов
        val circleSizeFactor = when (items.size) {
            in 5..20 -> 0.25f - (items.size - 5) * 0.01f
            else -> 0.11f
        }.coerceAtLeast(0.11f)

        val deviceCorrection = when {
            isLargeTablet -> 0.9f
            isTablet -> 0.9f
            isLandscape -> 0.9f
            else -> 1.0f
        }

        val circlePx = availableSize * circleSizeFactor * deviceCorrection
        val circleDp = with(density) { circlePx.toDp() }

        val tokenPx = circlePx * 0.4f
        val tokenDp = with(density) { tokenPx.toDp() }

        // Рассчитываем круговые позиции для кругового режима
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

        // Логика для кругового режима: определяем порядок при перетаскивании
        val placeholderIndex = if (draggingId == null) -1
        else nearestIndexForOffset(dragOffset, slotCenters)

        val arrangement = if (!freePositionMode && draggingId != null) {
            run {
                val drId = draggingId
                val list = MutableList<Int?>(items.size) { null }
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
        } else {
            null
        }

        val playerSlot = arrangement?.mapIndexedNotNull { idx, id -> id?.let { it to idx } }?.toMap()

        items.forEach { player ->
            key(player.id) {
                val isDragging = draggingId == player.id

                val scale by animateFloatAsState(if (isDragging) 1.2f else 1f)

                // Определяем целевую позицию в зависимости от режима
                val targetCenter = when {
                    isDragging -> dragOffset
                    freePositionMode -> {
                        // СВОБОДНЫЙ РЕЖИМ: используем сохраненные координаты
                        localPositions[player.id]?.let { savedPos ->
                            // Преобразуем нормализованные координаты в пиксели
                            val centerX = savedPos.x * boxWidth
                            val centerY = savedPos.y * boxHeight

                            // Ограничиваем, чтобы круг не выходил за границы
                            val clampedX = centerX.coerceIn(circlePx / 2f, boxWidth - circlePx / 2f)
                            val clampedY = centerY.coerceIn(circlePx / 2f, boxHeight - circlePx / 2f)

                            Offset(clampedX, clampedY)
                        } ?: run {
                            // Если позиция не сохранена, используем круговую позицию
                            val index = items.indexOfFirst { it.id == player.id }
                            if (index != -1 && index < slotCenters.size) {
                                slotCenters[index]
                            } else {
                                Offset(cx - circlePx / 2f, cy - circlePx / 2f)
                            }
                        }
                    }

                    else -> {
                        // КРУГОВОЙ РЕЖИМ: используем позицию из слота
                        val si = playerSlot?.get(player.id) ?: items.indexOfFirst { it.id == player.id }
                        slotCenters[si.coerceIn(0, slotCenters.lastIndex)]
                    }
                }

                val animatedCenter by animateOffsetAsState(
                    targetValue = targetCenter,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )

                val topLeft = Offset(
                    animatedCenter.x - circlePx / 2f,
                    animatedCenter.y - circlePx / 2f
                )

                Box(
                    modifier = Modifier
                        .offset { IntOffset(topLeft.x.roundToInt(), topLeft.y.roundToInt()) }
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                        }
                        .zIndex(if (isDragging) 50f else 0f)
                        .pointerInput(player.id, freePositionMode, boxWidth, boxHeight, circlePx, localPositions) {
                            detectDragGesturesAfterLongPress(
                                onDragStart = {
                                    draggingId = player.id
                                    val currentPos = if (freePositionMode) {
                                        // СВОБОДНЫЙ РЕЖИМ: начинаем с сохраненной позиции
                                        localPositions[player.id]?.let { savedPos ->
                                            val centerX = savedPos.x * boxWidth
                                            val centerY = savedPos.y * boxHeight
                                            Offset(
                                                centerX.coerceIn(circlePx / 2f, boxWidth - circlePx / 2f),
                                                centerY.coerceIn(circlePx / 2f, boxHeight - circlePx / 2f)
                                            )
                                        } ?: run {
                                            // Если нет сохраненной позиции, используем круговую
                                            val index = items.indexOfFirst { it.id == player.id }
                                            if (index != -1 && index < slotCenters.size) {
                                                slotCenters[index]
                                            } else {
                                                Offset(cx, cy)
                                            }
                                        }
                                    } else {
                                        // КРУГОВОЙ РЕЖИМ: начинаем с текущей круговой позиции
                                        val index = items.indexOfFirst { it.id == player.id }
                                        slotCenters.getOrElse(index) { Offset(cx, cy) }
                                    }
                                    dragOffset = currentPos
                                },
                                onDrag = { change, drag ->
                                    change.consume()
                                    dragOffset += Offset(drag.x, drag.y)
                                },
                                onDragEnd = {
                                    if (freePositionMode) {
                                        // СВОБОДНЫЙ РЕЖИМ: сохраняем новую позицию
                                        val centerX = dragOffset.x.coerceIn(circlePx / 2f, boxWidth - circlePx / 2f)
                                        val centerY = dragOffset.y.coerceIn(circlePx / 2f, boxHeight - circlePx / 2f)

                                        // Нормализуем координаты
                                        val normalizedX = centerX / boxWidth
                                        val normalizedY = centerY / boxHeight

                                        // Обновляем локальные позиции
                                        localPositions = localPositions.toMutableMap().apply {
                                            put(player.id, Offset(normalizedX, normalizedY))
                                        }

                                        // Вызываем коллбек для сохранения позиции
                                        onPositionChanged(player, normalizedX, normalizedY)
                                    } else {
                                        // КРУГОВОЙ РЕЖИМ: меняем порядок
                                        val from = items.indexOfFirst { it.id == player.id }
                                        val to = nearestIndexForOffset(dragOffset, slotCenters)

                                        if (from != -1 && from != to) {
                                            val newList = items.toMutableList()
                                            val p = newList.removeAt(from)
                                            newList.add(to, p)
                                            items = newList
                                            onOrderChanged(newList)
                                        }
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

                    // Отображение токенов
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

        // Отображение сказочника
        if (fabledEnabled) {
            Box(
                modifier = Modifier
                    .offset {
                        IntOffset(
                            (cx - circlePx / 2f).roundToInt(),
                            (cy - circlePx / 2f).roundToInt()
                        )
                    }
            ) {
                CircleItem(
                    size = circleDp,
                    itemType = ItemType.PLAYER_CIRCLE,
                    bottomText = fabled?.role?.roleName?.let { stringResource(it) }.orEmpty(),
                    centerIcon = fabled?.role?.iconRes,
                    onClick = { onFabledClick(fabled) },
                )

                val tokens = fabled?.tokens
                if (tokens?.isNotEmpty() == true) {
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
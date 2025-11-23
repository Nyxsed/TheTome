package ru.nyxsed.thetome.features.game.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
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
    onLongClick: (Player) -> Unit,
    onTokenLongClick: (Player, Int) -> Unit,
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

        // Максимальные размеры
        val maxCirclePx = with(density) { maxCircleSize.toPx() }
        val maxTokenPx = with(density) { maxTokenSize.toPx() }

        // Вычисление размера игроков (px)
        val circlePxRaw =
            (2 * Math.PI * ((min(boxWidthPx, boxHeightPx) - (maxCirclePx + maxTokenPx)) / 2f)
                    / players.size * 0.8).toFloat()

        val circlePx = circlePxRaw.coerceIn(
            with(density) { minCircleSize.toPx() },
            maxCirclePx
        )
        val circleSize = with(density) { circlePx.toDp() }

        // Размер токена масштабируется примерно так же
        val tokenPxRaw = circlePx * 0.4f
        val tokenPx = tokenPxRaw.coerceIn(
            with(density) { minTokenSize.toPx() },
            maxTokenPx
        )
        val tokenSize = with(density) { tokenPx.toDp() }

        // Основной радиус — учитывает диаметр игрока + диаметр токена
        val radiusPx =
            (min(boxWidthPx, boxHeightPx) - (circlePx + tokenPx)) / 2f

        players.forEachIndexed { index, player ->
            key(player.id) {
                // Позиция игрока
                val angleRad = Math.toRadians(
                    (-90f + 360f / players.size * index).toDouble()
                )

                val playerX =
                    centerX + radiusPx * cos(angleRad) - circlePx / 2f
                val playerY =
                    centerY + radiusPx * sin(angleRad) - circlePx / 2f

                // ---------- Игрок ----------
                CircleItem(
                    modifier = Modifier
                        .offset {
                            IntOffset(playerX.roundToInt(), playerY.roundToInt())
                        }.zIndex(0f),
                    size = circleSize,
                    itemType = ItemType.PLAYER_CIRCLE,
                    topText = player.name.orEmpty(),
                    bottomText = player.role?.roleName?.let { stringResource(it) }.orEmpty(),
                    centerIcon = player.role?.iconRes,
                    isEnabled = player.isAlive,
                    haveGhostVote = player.haveGhostVote,
                    onClick = { onClick(player) },
                    onLongClick = { onLongClick(player) }
                )

                // ---------- Токены игрока ----------
                val tokens = player.tokens
                if (tokens.isNotEmpty()) {
                    val inset = tokenPx * 0.3f
                    val tokenRadiusPx = (circlePx + tokenPx) / 2f - inset

                    tokens.forEachIndexed { ti, token ->
                        val tAngle = angleRad +
                                (2 * Math.PI / tokens.size) * ti

                        val tx =
                            (playerX + circlePx / 2f) +
                                    tokenRadiusPx * cos(tAngle) - tokenPx / 2f
                        val ty =
                            (playerY + circlePx / 2f) +
                                    tokenRadiusPx * sin(tAngle) - tokenPx / 2f

                        CircleItem(
                            modifier = Modifier
                                .offset {
                                    IntOffset(tx.roundToInt(), ty.roundToInt())
                                }
                                .zIndex(1f),
                            size = tokenSize,
                            itemType = ItemType.TOKEN_CIRCLE,
                            bottomText = stringResource(token.nameResId),
                            centerIcon = token.iconRes,
                            isEnabled = true,
                            haveGhostVote = false,
                            onClick = { /* нет */ },
                            onLongClick = { onTokenLongClick(player, ti) },
                        )
                    }
                }
            }
        }
    }
}

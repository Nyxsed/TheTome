package ru.nyxsed.thetome.core.presentation.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import ru.nyxsed.thetome.R
import ru.nyxsed.thetome.core.domain.models.GamePhase

@Composable
fun GameScreenBackground(currentPhase: GamePhase?) {
    val isNight = currentPhase == GamePhase.FIRST_NIGHT || currentPhase == GamePhase.SECOND_NIGHT
    val backgroundPainter = painterResource(R.drawable.bg_day)
    val moon = painterResource(R.drawable.moon)

    val targetAlpha = if (isNight) 0.5f else 0f

    val animatedAlpha by animateFloatAsState(
        targetValue = targetAlpha,
        animationSpec = tween(durationMillis = 1000) // 1 секунда, можно менять
    )

    val infiniteTransition = rememberInfiniteTransition(label = "clouds")
    val cloudOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(60000, easing = LinearEasing), // 25 секунд, регулируется
            repeatMode = RepeatMode.Restart
        ),
        label = "cloudOffset"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // Основной фон
        Image(
            painter = backgroundPainter,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        if (isNight) {
            Image(
                painter = moon,
                contentDescription = null,
                modifier = Modifier.graphicsLayer {
                    translationX = 300f
                    translationY = -200f
                    scaleX = 0.3f
                    scaleY = 0.3f
                },
                alpha = 0.7f,
                contentScale = ContentScale.FillWidth
            )

            // Слой 1 — высокие мелкие облака
            CloudsLayer(
                progress = cloudOffset,
                clouds = listOf(
                    CloudItem(-0.4f, -0.05f, 0.6f, 0.3f),
                    CloudItem(-0.4f, -0.1f, 0.1f, 0.3f),
                    CloudItem(-0.4f, 0.03f, 0.3f, 0.3f),
                    CloudItem(-0.2f, -0.18f, 0.9f, 0.3f),
                    CloudItem(0.4f, 0.12f, 1.1f, 0.3f)
                )
            )

            // Слой 2 — средние облака
            CloudsLayer(
                progress = cloudOffset,
                clouds = listOf(
                    CloudItem(-0.5f, 0.42f, 1.3f, 0.3f),
                    CloudItem(0.1f, 0.35f, 1.0f, 0.3f),
                    CloudItem(0.7f, 0.48f, 1.2f, 0.3f)
                )
            )

            // Слой 3 — крупные низкие облака
            CloudsLayer(
                progress = cloudOffset,
                clouds = listOf(
                    CloudItem(-0.3f, 0.72f, 1.5f, 0.3f),
                    CloudItem(0.6f, 0.80f, 1.7f, 0.3f)
                )
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = animatedAlpha))
        )
    }
}

@Composable
private fun CloudsLayer(
    progress: Float,
    clouds: List<CloudItem>,
) {
    val cloudPainter = painterResource(R.drawable.clouds)

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val widthPx = constraints.maxWidth.toFloat()
        val heightPx = constraints.maxHeight.toFloat()

        clouds.forEach { cloud ->

            // progress: 0→1 даёт движение от -1×width→+1×width
            val globalShiftX = (progress * 2f - 1f) * widthPx

            val initialShiftX = cloud.offsetXFraction * widthPx
            val posY = cloud.offsetYFraction * heightPx

            Image(
                painter = cloudPainter,
                contentDescription = null,
                modifier = Modifier.graphicsLayer {
                    translationX = initialShiftX + globalShiftX
                    translationY = posY
                    scaleX = cloud.scale
                    scaleY = cloud.scale
                },
                alpha = cloud.alpha,
                contentScale = ContentScale.FillWidth
            )
        }
    }
}

data class CloudItem(
    val offsetXFraction: Float,   // начальная позиция (-1..1)
    val offsetYFraction: Float,   // вертикаль (0..1)
    val scale: Float,
    val alpha: Float,
)
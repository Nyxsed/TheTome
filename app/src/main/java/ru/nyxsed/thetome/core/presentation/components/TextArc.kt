package ru.nyxsed.thetome.core.presentation.components

import android.graphics.Paint
import android.graphics.Typeface
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun TextArc(
    text: String,
    circleSize: Dp,
    color: Color = Color.Black,
    position: ArcPosition,
) {
    if (text.isEmpty()) return

    val fontSizePx = remember(circleSize) {
        when {
            circleSize.value <= 20f -> circleSize.value * 0.5f
            circleSize.value <= 30f -> circleSize.value * 0.5f
            else -> circleSize.value * 0.3f
        }
    }

    val letterSpacing = remember(circleSize) {
        when {
            circleSize.value <= 20f -> 1.2f
            circleSize.value <= 30f -> 1.6f
            else -> 2.2f
        }
    }

    // Paint для текста, кэшируемый по цвету
    val paint = remember(color) {
        Paint().apply {
            this.color = color.toArgb()
            this.isAntiAlias = true
            this.textAlign = Paint.Align.CENTER
            this.typeface = Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL)
        }
    }

    // Кэшируем ширину текста
    val textWidthPx = remember(text, fontSizePx) {
        text.map { paint.measureText(it.toString()) }.sum()
    }

    Canvas(modifier = Modifier.size(circleSize)) {
        val canvasRadius = size.minDimension / 2f
        val centerX = size.width / 2f
        val centerY = size.height / 2f

        paint.textSize = fontSizePx

        // Увеличиваем общий угол для регулировки расстояния между буквами
        val totalAngleRadAdjusted = (textWidthPx / canvasRadius) * letterSpacing
        val charAngleRad = if (text.length > 1) totalAngleRadAdjusted / (text.length - 1) else 0.0

        text.forEachIndexed { index, char ->
            val angleRad = when (position) {
                ArcPosition.TOP -> -Math.PI / 2 - totalAngleRadAdjusted / 2 + index.toFloat() * charAngleRad.toFloat()
                ArcPosition.BOTTOM -> Math.PI / 2 + totalAngleRadAdjusted / 2 - index.toFloat() * charAngleRad.toFloat()
            }

            val metrics = paint.fontMetrics
            val textHeight = metrics.descent - metrics.ascent
            val adjustedRadius = when (position) {
                ArcPosition.TOP -> canvasRadius - textHeight
                ArcPosition.BOTTOM -> canvasRadius - textHeight / 2f
            }

            val x = centerX + adjustedRadius * cos(angleRad).toFloat()
            val y = centerY + adjustedRadius * sin(angleRad).toFloat()

            drawContext.canvas.nativeCanvas.apply {
                save()
                val rotation = if (position == ArcPosition.TOP) Math.toDegrees(angleRad) + 90.0
                else Math.toDegrees(angleRad) - 90.0
                rotate(rotation.toFloat(), x, y)
                drawText(char.toString(), x, y, paint)
                restore()
            }
        }
    }
}

enum class ArcPosition { TOP, BOTTOM }

@Preview
@Composable
fun TextArcPreview84() {
    TextArc(
        text = "Никита",
        circleSize = 84.dp, // 84 74 64 30 20
        color = Color.White,
        position = ArcPosition.TOP
    )
}
@Preview
@Composable
fun TextArcPreview74() {
    TextArc(
        text = "Андрей",
        circleSize = 74.dp, // 84 74 64 30 20
        color = Color.White,
        position = ArcPosition.TOP
    )
}
@Preview
@Composable
fun TextArcPreview64() {
    TextArc(
        text = "Андрей",
        circleSize = 64.dp, // 84 74 64 30 20
        color = Color.White,
        position = ArcPosition.TOP
    )
}
@Preview
@Composable
fun TextArcPreview30() {
    TextArc(
        text = "Андрей",
        circleSize = 30.dp, // 84 74 64 30 20
        color = Color.White,
        position = ArcPosition.TOP
    )
}
@Preview
@Composable
fun TextArcPreview20() {
    TextArc(
        text = "Андрей",
        circleSize = 20.dp, // 84 74 64 30 20
        color = Color.White,
        position = ArcPosition.TOP
    )
}
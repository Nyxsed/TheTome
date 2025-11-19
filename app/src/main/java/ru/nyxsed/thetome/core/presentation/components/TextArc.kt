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
            circleSize.value <= 40f -> circleSize.value * 0.5f
            circleSize.value <= 50f -> circleSize.value * 0.4f
            circleSize.value <= 60f -> circleSize.value * 0.4f
            circleSize.value <= 70f -> circleSize.value * 0.4f
            circleSize.value <= 80f -> circleSize.value * 0.4f
            circleSize.value <= 90f -> circleSize.value * 0.35f
            else -> circleSize.value * 0.31f
        }
    }

    val letterSpacing = remember(circleSize) {
        when {
            circleSize.value <= 20f -> 1.1f
            circleSize.value <= 30f -> 1.6f
            circleSize.value <= 40f -> 2.1f
            circleSize.value <= 50f -> 2.1f
            circleSize.value <= 60f -> 2.7f
            circleSize.value <= 70f -> 2.7f
            circleSize.value <= 80f -> 2.9f
            circleSize.value <= 90f -> 2.9f
            else -> 6f
        }
    }

    val paint = remember(color) {
        Paint().apply {
            this.color = color.toArgb()
            this.isAntiAlias = true
            this.textAlign = Paint.Align.CENTER
            this.typeface = Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL)
        }
    }

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


@Preview(showBackground = true)
@Composable
fun TextArcPlayerPreview() {
    CircleItemPlayerPreview()
}

@Preview(showBackground = true)
@Composable
fun TextArcTokenPreview() {
    CircleItemTokenPreview()
}
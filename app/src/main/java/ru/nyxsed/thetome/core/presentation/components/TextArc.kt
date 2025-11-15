package ru.nyxsed.thetome.core.presentation.components

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
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
    Canvas(modifier = Modifier.size(circleSize)) {
        if (text.isEmpty()) return@Canvas

        val canvasRadius = size.minDimension / 2f
        val centerX = size.width / 2f
        val centerY = size.height / 2f

        // Динамический коэффициент для шрифта в зависимости от размера круга
        val sizeFactor = when {
            canvasRadius < 50f -> 0.3f // маленький круг — увеличиваем шрифт
            canvasRadius < 100f -> 0.32f
            else -> 0.2f // большой круг — немного меньше
        }

        // Авто-подбор размера шрифта по радиусу круга
        val fontSizePx = canvasRadius * sizeFactor
        val paint = Paint().apply {
            this.color = color.toArgb()
            this.textSize = fontSizePx
            this.isAntiAlias = true
            this.textAlign = Paint.Align.CENTER
        }

        // Расчет sweepAngle исходя из ширины текста и радиуса
        val textWidthPx = text.map { paint.measureText(it.toString()).toDouble() }.sum()
        // Общий угол для текста в радианах: длина дуги = radius * angle
        val totalAngleRad = textWidthPx / canvasRadius
        val charAngleRad = if (text.length > 1) totalAngleRad / (text.length - 1) else 0.0

        text.forEachIndexed { index, char ->
            val angleRad = when (position) {
                ArcPosition.TOP -> -Math.PI / 2 - totalAngleRad / 2 + index * charAngleRad
                ArcPosition.BOTTOM -> Math.PI / 2 + totalAngleRad / 2 - index * charAngleRad
            }


            // Радиус для верхней дуги учитывает высоту шрифта
            val radiusOffset = fontSizePx / 2f
            val metrics = paint.fontMetrics
            val textHeight = metrics.descent - metrics.ascent

            val adjustedRadius = when (position) {
                ArcPosition.TOP -> canvasRadius - textHeight // прижимаем текст к верхнему краю
                ArcPosition.BOTTOM -> canvasRadius - textHeight / 2f // чуть выше нижнего края
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
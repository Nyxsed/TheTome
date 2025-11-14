package ru.nyxsed.thetome.features.game.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.nyxsed.thetome.features.game.presentation.components.ArcPosition
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin


@Composable
fun TextArc(
    text: String,
    circleSize: Dp,
    fontSize: TextUnit = 12.sp,
    color: Color = Color.Black,
    position: ArcPosition,
    maxSweep: Float = 120f,
) {
    Canvas(modifier = Modifier.size(circleSize)) {
        val radius = size.minDimension / 2 - 10.dp.toPx()
        val centerX = size.width / 2
        val centerY = size.height / 2

        if (text.isEmpty()) return@Canvas

        val paint = android.graphics.Paint().apply {
            this.color = color.toArgb()
            this.textSize = fontSize.toPx()
            this.isAntiAlias = true
            this.textAlign = android.graphics.Paint.Align.CENTER
        }

        val sweepAngle = min(maxSweep, 360f)
        val charAngle = if (text.length > 1) sweepAngle / (text.length - 1) else 0f

        text.forEachIndexed { index, char ->
            val angleDeg = when (position) {
                ArcPosition.TOP -> -90f - sweepAngle / 2 + index * charAngle
                ArcPosition.BOTTOM -> 90f + sweepAngle / 2 - index * charAngle
            }
            val angleRad = Math.toRadians(angleDeg.toDouble())

            val adjustedRadius = if (position == ArcPosition.BOTTOM) radius + fontSize.toPx() / 2 else radius

            val x = centerX + adjustedRadius * cos(angleRad)
            val y = centerY + adjustedRadius * sin(angleRad)

            drawContext.canvas.nativeCanvas.apply {
                save()
                val rotation = if (position == ArcPosition.TOP) angleDeg + 90f else angleDeg - 90f
                rotate(rotation.toFloat(), x.toFloat(), y.toFloat())
                drawText(char.toString(), x.toFloat(), y.toFloat(), paint)
                restore()
            }
        }
    }
}
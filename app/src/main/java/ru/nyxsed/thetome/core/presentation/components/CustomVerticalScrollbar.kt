package ru.nyxsed.thetome.core.presentation.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import kotlin.math.max

@Composable
fun CustomVerticalScrollbar(
    scrollState: ScrollState,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
    activeColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
) {
    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawWithContent {
                    drawContent()

                    if (scrollState.maxValue > 0) {
                        val containerHeight = size.height
                        val contentHeight = containerHeight + scrollState.maxValue
                        val visibleRatio = containerHeight / contentHeight
                        val indicatorHeight = max(containerHeight * visibleRatio, 20f)
                        val scrollableHeight = containerHeight - indicatorHeight
                        val scrollRatio = scrollState.value.toFloat() / scrollState.maxValue.toFloat()
                        val indicatorOffset = scrollRatio * scrollableHeight

                        // Фон скроллбара
                        drawRoundRect(
                            color = color,
                            topLeft = Offset(size.width / 2 - 1.5f, 0f),
                            size = Size(3f, containerHeight),
                            cornerRadius = CornerRadius(1.5f, 1.5f)
                        )

                        // Подвижный индикатор
                        drawRoundRect(
                            color = activeColor,
                            topLeft = Offset(size.width / 2 - 2f, indicatorOffset),
                            size = Size(4f, indicatorHeight),
                            cornerRadius = CornerRadius(2f, 2f)
                        )
                    }
                }
        )
    }
}
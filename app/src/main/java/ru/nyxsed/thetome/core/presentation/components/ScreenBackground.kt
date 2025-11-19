package ru.nyxsed.thetome.core.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import ru.nyxsed.thetome.R
import ru.nyxsed.thetome.core.domain.models.GamePhase

@Composable
fun GameScreenBackground(currentPhase: GamePhase?) {
    val backgroundPainter = painterResource(R.drawable.bg_day)

    val targetAlpha = if (currentPhase == GamePhase.FIRST_NIGHT || currentPhase == GamePhase.SECOND_NIGHT) 0.5f else 0f

    val animatedAlpha by animateFloatAsState(
        targetValue = targetAlpha,
        animationSpec = tween(durationMillis = 1000) // 1 секунда, можно менять
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // Основной фон
        Image(
            painter = backgroundPainter,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Накладываем затемнение с анимацией
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = animatedAlpha))
        )
    }
}
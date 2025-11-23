package ru.nyxsed.thetome.features.game.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import ru.nyxsed.thetome.core.domain.models.Player
import ru.nyxsed.thetome.core.domain.models.RoleType
import kotlin.math.ceil

@Composable
fun KillParticipation(
    roleDistribution: Map<RoleType, Int>?,
    players: List<Player>?,
    dayNumber: Int,
) {
    val neededToKill = ceil(players?.filter { it.isAlive }?.size?.div(2.0) ?: 1.0).toInt()
    Text(
        text =  "☀️:" + dayNumber.toString() +
                " ⚖️: " + neededToKill + "\n" +
                " \uD83E\uDDD1\u200D\uD83C\uDF3E:" + roleDistribution?.get(RoleType.TOWNSFOLK) +
                " \uD83D\uDE36\u200D\uD83C\uDF2B\uFE0F:" + roleDistribution?.get(RoleType.OUTSIDER) +
                " 🦹:" + roleDistribution?.get(RoleType.MINION) +
                " \uD83D\uDC79:" + roleDistribution?.get(RoleType.DEMON),
        textAlign = TextAlign.Center
    )
}
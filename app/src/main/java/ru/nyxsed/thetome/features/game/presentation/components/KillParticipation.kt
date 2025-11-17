package ru.nyxsed.thetome.features.game.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ru.nyxsed.thetome.core.domain.models.Player
import ru.nyxsed.thetome.core.domain.models.RoleType
import kotlin.math.ceil

@Composable
fun KillParticipation(
    roleDistribution: Map<RoleType, Int>?,
    players: List<Player>?,
) {
    val neededToKill = ceil(players?.filter { it.isAlive }?.size?.div(2.0) ?: 1.0).toInt()
    Text(
        text = "👩‍🌾:" + roleDistribution?.get(RoleType.TOWNSFOLK) +
                "👩‍🦽:" + roleDistribution?.get(RoleType.OUTSIDER) +
                "🤵‍♂️:" + roleDistribution?.get(RoleType.MINION) +
                " 😈:" + roleDistribution?.get(RoleType.DEMON) +
                " ⚖️: " + neededToKill
    )
}
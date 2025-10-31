package ru.nyxsed.thetome.core.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class GameState(
    val scenery: Scenery?,
    val players: List<Player>? = emptyList(),
    val chosenRoles: List<Role>? = emptyList(),
    val currentPhase: GamePhase = GamePhase.SETUP,
    val dayNumber: Int = 1,
    val nightNumber: Int = 1
)

package ru.nyxsed.thetome.core.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class GameState(
    val scenery: Scenery?,
    val players: List<Player>? = emptyList(),
    val chosenRoles: List<Role>? = emptyList(),
    val demonBluffs: List<Role?> = emptyList(),
    val roleDistribution: Map<RoleType, Int>? = emptyMap(),
    val currentPhase: GamePhase = GamePhase.SETUP,
    val dayNumber: Int = 1,
    val nightNumber: Int = 1
) {
    private val allScenarioRoles = scenery?.roles?.filter { role ->
        role.type !in listOf(RoleType.DEMON, RoleType.MINION)
    } ?: emptyList()
    val availableBluffRoles = allScenarioRoles.filter { role ->
        chosenRoles?.contains(role) != true && role !in demonBluffs
    }
}

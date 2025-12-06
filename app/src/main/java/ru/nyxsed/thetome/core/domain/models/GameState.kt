package ru.nyxsed.thetome.core.domain.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameState(
    @SerialName("scenery")
    @Contextual
    val scenery: Scenery? = null,
    val players: List<Player>? = emptyList(),
    val chosenRoles: List<Role>? = emptyList(),
    val demonBluffs: List<Role?> = emptyList(),
    val roleDistribution: Map<RoleType, Int>? = emptyMap(),
    val fabled: Player? = null,
    val currentPhase: GamePhase = GamePhase.PREPARE,
    val actions: List<Action> = emptyList(),
    val actionIndex: Int = 0,
    val currentAction: Action? = null,
    val currentDay: Int = 0,
    val notes: String = "",
) {
    private val allScenarioRoles = scenery?.roles?.filter { role ->
        role.type !in listOf(RoleType.DEMON, RoleType.MINION)
    } ?: emptyList()
    val availableBluffRoles = allScenarioRoles.filter { role ->
        chosenRoles?.contains(role) != true && role !in demonBluffs
    }
}

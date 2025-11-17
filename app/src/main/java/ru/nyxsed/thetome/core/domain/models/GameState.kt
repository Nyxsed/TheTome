package ru.nyxsed.thetome.core.domain.models

import kotlinx.serialization.Serializable
import ru.nyxsed.thetome.R

@Serializable
data class GameState(
    val scenery: Scenery?,
    val players: List<Player>? = emptyList(),
    val chosenRoles: List<Role>? = emptyList(),
    val demonBluffs: List<Role?> = emptyList(),
    val roleDistribution: Map<RoleType, Int>? = emptyMap(),
    val currentPhase: GamePhase = GamePhase.PREPARE,
    val dayNumber: Int = 1,
    val nightNumber: Int = 1,
    val actionIndex: Int = 0,
    val currentAction: Action = Action(
        actionType = ActionType.DAY,
        role = null,
        actionResId = R.string.action_start_day_phase
    ),
) {
    private val allScenarioRoles = scenery?.roles?.filter { role ->
        role.type !in listOf(RoleType.DEMON, RoleType.MINION)
    } ?: emptyList()
    val availableBluffRoles = allScenarioRoles.filter { role ->
        chosenRoles?.contains(role) != true && role !in demonBluffs
    }
}

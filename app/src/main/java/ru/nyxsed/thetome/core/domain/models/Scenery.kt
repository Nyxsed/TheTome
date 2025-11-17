package ru.nyxsed.thetome.core.domain.models

import kotlinx.serialization.Serializable
import ru.nyxsed.thetome.R

@Serializable
sealed class Scenery(
    val sceneryId: SceneryId,
    val roles: List<Role>,
    val prepareActions: List<Action>,
    val firstNightActions: List<Action>,
    val secondNightActions: List<Action>,
    val dayActions: List<Action>,
    val firstNightOrder: List<Role>,
    val secondNightOrder: List<Role>,
) {
    @Serializable
    data object TroubleBrewing : Scenery(
        sceneryId = SceneryId.TROUBLE_BREWING,
        roles = listOf(
            Role.Washerwoman,
            Role.Librarian,
            Role.Investigator,
            Role.Chef,
            Role.Empath,
            Role.FortuneTeller,
            Role.Undertaker,
            Role.Monk,
            Role.Ravenkeeper,
            Role.Virgin,
            Role.Slayer,
            Role.Soldier,
            Role.Mayor,
            Role.Butler,
            Role.Saint,
            Role.Recluse,
            Role.Drunk,
            Role.Poisoner,
            Role.Spy,
            Role.Baron,
            Role.ScarletWoman,
            Role.Imp,
        ),
        prepareActions = listOf(
            Action(actionType = ActionType.PLAYERS_7, role = null, actionResId = R.string.action_prepare_3_demon_bluffs),
            Action(actionType = ActionType.DAY, role = null, actionResId = R.string.action_prepare_bag),
            Action(actionType = ActionType.PLAYER, role = Role.Washerwoman, actionResId = Role.Washerwoman.prepareActionId),
            Action(actionType = ActionType.DAY, role = null, actionResId = R.string.action_start_first_night_phase),
        ),
        firstNightActions = listOf(
            Action(actionType = ActionType.PLAYERS_7, role = null, actionResId = R.string.action_show_demon),
            Action(actionType = ActionType.PLAYERS_7, role = null, actionResId = R.string.action_show_minions_and_bluffs),
            Action(actionType = ActionType.PLAYER, role = Role.Poisoner, actionResId = Role.Poisoner.secondNightAction),
            Action(actionType = ActionType.NIGHT, role = null, actionResId = R.string.action_start_day_phase),
        ),
        secondNightActions = listOf(
            Action(actionType = ActionType.PLAYER, role = Role.Poisoner, actionResId = Role.Poisoner.secondNightAction),
            Action(actionType = ActionType.PLAYER, role = Role.Imp, actionResId = Role.Imp.secondNightAction),
            Action(actionType = ActionType.NIGHT, role = null, actionResId = R.string.action_start_day_phase),
        ),
        dayActions = listOf(
            Action(actionType = ActionType.DAY, role = null, actionResId = R.string.action_voting),
            Action(actionType = ActionType.DAY, role = null, actionResId = R.string.action_start_night_phase)
        ),
        firstNightOrder = listOf(
            Role.Poisoner,
            Role.Spy,
            Role.Washerwoman,
            Role.Librarian,
            Role.Investigator,
            Role.Chef,
            Role.Empath,
            Role.FortuneTeller,
            Role.Butler,
        ),
        secondNightOrder = listOf(
            Role.Poisoner,
            Role.Monk,
            Role.Spy,
            Role.ScarletWoman,
            Role.Imp,
            Role.Ravenkeeper,
            Role.Undertaker,
            Role.Empath,
            Role.FortuneTeller,
            Role.Butler,
        )
    )

    companion object {
        val all: List<Scenery> = listOf(
            TroubleBrewing,
        )
    }
}
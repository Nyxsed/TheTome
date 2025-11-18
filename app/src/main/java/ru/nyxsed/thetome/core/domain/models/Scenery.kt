package ru.nyxsed.thetome.core.domain.models

import kotlinx.serialization.Serializable
import ru.nyxsed.thetome.R

@Serializable
sealed class Scenery(
    val sceneryNameRes: Int,
    val roles: List<Role>,
    val prepareActions: List<Action>,
    val firstNightActions: List<Action>,
    val secondNightActions: List<Action>,
    val dayActions: List<Action>,
    val sceneryTokens: List<Token>,
) {
    @Serializable
    data object TroubleBrewing : Scenery(
        sceneryNameRes = R.string.scenery_name_trouble_brewing,
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
            Action(actionType = ActionType.PLAYER, role = Role.Baron, actionResId = Role.Baron.prepareActionId),
            Action(actionType = ActionType.PLAYER, role = Role.Drunk, actionResId = Role.Drunk.prepareActionId),
            Action(actionType = ActionType.PLAYERS_7, role = null, actionResId = R.string.action_prepare_3_demon_bluffs),
            Action(actionType = ActionType.DAY, role = null, actionResId = R.string.action_prepare_bag),
            Action(actionType = ActionType.PLAYER, role = Role.Washerwoman, actionResId = Role.Washerwoman.prepareActionId),
            Action(actionType = ActionType.PLAYER, role = Role.Librarian, actionResId = Role.Librarian.prepareActionId),
            Action(actionType = ActionType.PLAYER, role = Role.FortuneTeller, actionResId = Role.FortuneTeller.prepareActionId),
            Action(actionType = ActionType.PLAYER, role = Role.Recluse, actionResId = Role.Recluse.prepareActionId),
            Action(actionType = ActionType.DAY, role = null, actionResId = R.string.action_start_first_night_phase),
        ),
        firstNightActions = listOf(
            Action(actionType = ActionType.PLAYERS_7, role = null, actionResId = R.string.action_show_demon),
            Action(actionType = ActionType.PLAYERS_7, role = null, actionResId = R.string.action_show_minions_and_bluffs),
            Action(actionType = ActionType.PLAYER, role = Role.Poisoner, actionResId = Role.Poisoner.firstNightAction),
            Action(actionType = ActionType.PLAYER, role = Role.Spy, actionResId = Role.Spy.firstNightAction),
            Action(actionType = ActionType.PLAYER, role = Role.Washerwoman, actionResId = Role.Washerwoman.firstNightAction),
            Action(actionType = ActionType.PLAYER, role = Role.Librarian, actionResId = Role.Librarian.firstNightAction),
            Action(actionType = ActionType.PLAYER, role = Role.Investigator, actionResId = Role.Investigator.firstNightAction),
            Action(actionType = ActionType.PLAYER, role = Role.Chef, actionResId = Role.Chef.firstNightAction),
            Action(actionType = ActionType.PLAYER, role = Role.Empath, actionResId = Role.Empath.firstNightAction),
            Action(actionType = ActionType.PLAYER, role = Role.FortuneTeller, actionResId = Role.FortuneTeller.firstNightAction),
            Action(actionType = ActionType.PLAYER, role = Role.Butler, actionResId = Role.Butler.firstNightAction),
            Action(actionType = ActionType.NIGHT, role = null, actionResId = R.string.action_start_day_phase),
        ),
        secondNightActions = listOf(
            Action(actionType = ActionType.PLAYER, role = Role.Poisoner, actionResId = Role.Poisoner.secondNightAction),
            Action(actionType = ActionType.PLAYER, role = Role.Monk, actionResId = Role.Monk.secondNightAction),
            Action(actionType = ActionType.PLAYER, role = Role.Spy, actionResId = Role.Spy.secondNightAction),
            Action(actionType = ActionType.PLAYER, role = Role.ScarletWoman, actionResId = Role.ScarletWoman.secondNightAction),
            Action(actionType = ActionType.PLAYER, role = Role.Imp, actionResId = Role.Imp.secondNightAction),
            Action(actionType = ActionType.PLAYER, role = Role.Ravenkeeper, actionResId = Role.Ravenkeeper.secondNightAction),
            Action(actionType = ActionType.PLAYER, role = Role.Undertaker, actionResId = Role.Undertaker.secondNightAction),
            Action(actionType = ActionType.PLAYER, role = Role.Empath, actionResId = Role.Empath.secondNightAction),
            Action(actionType = ActionType.PLAYER, role = Role.FortuneTeller, actionResId = Role.FortuneTeller.secondNightAction),
            Action(actionType = ActionType.PLAYER, role = Role.Butler, actionResId = Role.Butler.secondNightAction),
            Action(actionType = ActionType.NIGHT, role = null, actionResId = R.string.action_start_day_phase),
        ),
        dayActions = listOf(
            Action(actionType = ActionType.DAY, role = null, actionResId = R.string.action_voting),
            Action(actionType = ActionType.PLAYER, role = Role.Butler, actionResId = Role.Butler.dayAction),
            Action(actionType = ActionType.PLAYER, role = Role.Undertaker, actionResId = Role.Undertaker.dayAction),
            Action(actionType = ActionType.DAY, role = null, actionResId = R.string.action_start_night_phase)
        ),
        sceneryTokens = listOf(Token.Good, Token.Evil, Token.DrunkDrunk)
    )

    companion object {
        val all: List<Scenery> = listOf(
            TroubleBrewing,
        )
    }
}
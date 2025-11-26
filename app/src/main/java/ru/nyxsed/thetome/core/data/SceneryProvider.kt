package ru.nyxsed.thetome.core.data

import ru.nyxsed.thetome.R
import ru.nyxsed.thetome.core.data.TokenProvider.DrunkDrunk
import ru.nyxsed.thetome.core.data.TokenProvider.Evil
import ru.nyxsed.thetome.core.data.TokenProvider.Good
import ru.nyxsed.thetome.core.data.TokenProvider.MarionetteMarionette
import ru.nyxsed.thetome.core.domain.models.Action
import ru.nyxsed.thetome.core.domain.models.ActionType
import ru.nyxsed.thetome.core.domain.models.Scenery

object SceneryProvider {

    val TroubleBrewing = Scenery(
        sceneryNameRes = R.string.scenery_name_trouble_brewing,
        iconRes = R.drawable.icon_trouble_brewing,
        roles = listOf(
            RoleProvider.Washerwoman,
            RoleProvider.Librarian,
            RoleProvider.Investigator,
            RoleProvider.Chef,
            RoleProvider.Empath,
            RoleProvider.FortuneTeller,
            RoleProvider.Undertaker,
            RoleProvider.Monk,
            RoleProvider.Ravenkeeper,
            RoleProvider.Virgin,
            RoleProvider.Slayer,
            RoleProvider.Soldier,
            RoleProvider.Mayor,
            RoleProvider.Butler,
            RoleProvider.Saint,
            RoleProvider.Recluse,
            RoleProvider.Drunk,
            RoleProvider.Poisoner,
            RoleProvider.Spy,
            RoleProvider.Baron,
            RoleProvider.ScarletWoman,
            RoleProvider.Imp,
        ),
        prepareActions = listOf(
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Baron,
                actionResId = RoleProvider.Baron.prepareActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Drunk,
                actionResId = RoleProvider.Drunk.prepareActionId
            ),
            Action(
                actionType = ActionType.PLAYERS_7,
                role = null,
                actionResId = R.string.action_prepare_3_demon_bluffs
            ),
            Action(actionType = ActionType.DAY, role = null, actionResId = R.string.action_prepare_bag),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Washerwoman,
                actionResId = RoleProvider.Washerwoman.prepareActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Librarian,
                actionResId = RoleProvider.Librarian.prepareActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.FortuneTeller,
                actionResId = RoleProvider.FortuneTeller.prepareActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Investigator,
                actionResId = RoleProvider.Investigator.prepareActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Recluse,
                actionResId = RoleProvider.Recluse.prepareActionId
            ),
            Action(actionType = ActionType.DAY, role = null, actionResId = R.string.action_start_first_night_phase),
        ),
        firstNightActions = listOf(
            Action(actionType = ActionType.PLAYERS_7, role = null, actionResId = R.string.action_show_demon),
            Action(
                actionType = ActionType.PLAYERS_7,
                role = null,
                actionResId = R.string.action_show_minions_and_bluffs
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Poisoner,
                actionResId = RoleProvider.Poisoner.firstNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Spy,
                actionResId = RoleProvider.Spy.firstNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Washerwoman,
                actionResId = RoleProvider.Washerwoman.firstNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Librarian,
                actionResId = RoleProvider.Librarian.firstNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Investigator,
                actionResId = RoleProvider.Investigator.firstNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Chef,
                actionResId = RoleProvider.Chef.firstNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Empath,
                actionResId = RoleProvider.Empath.firstNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.FortuneTeller,
                actionResId = RoleProvider.FortuneTeller.firstNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Butler,
                actionResId = RoleProvider.Butler.firstNightActionId
            ),
            Action(actionType = ActionType.NIGHT, role = null, actionResId = R.string.action_start_day_phase),
        ),
        secondNightActions = listOf(
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Poisoner,
                actionResId = RoleProvider.Poisoner.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Monk,
                actionResId = RoleProvider.Monk.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Spy,
                actionResId = RoleProvider.Spy.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.ScarletWoman,
                actionResId = RoleProvider.ScarletWoman.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Imp,
                actionResId = RoleProvider.Imp.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Ravenkeeper,
                actionResId = RoleProvider.Ravenkeeper.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Undertaker,
                actionResId = RoleProvider.Undertaker.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Empath,
                actionResId = RoleProvider.Empath.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.FortuneTeller,
                actionResId = RoleProvider.FortuneTeller.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Butler,
                actionResId = RoleProvider.Butler.secondNightActionId
            ),
            Action(actionType = ActionType.NIGHT, role = null, actionResId = R.string.action_start_day_phase),
        ),
        dayActions = listOf(
            Action(actionType = ActionType.DAY, role = null, actionResId = R.string.action_voting),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Butler,
                actionResId = RoleProvider.Butler.dayActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Undertaker,
                actionResId = RoleProvider.Undertaker.dayActionId
            ),
            Action(actionType = ActionType.DAY, role = null, actionResId = R.string.action_start_night_phase)
        ),
        sceneryTokens = listOf(Good, Evil, DrunkDrunk)
    )


    val UncertainDeath = Scenery(
        sceneryNameRes = R.string.scenery_name_uncertain_death,
        iconRes = R.drawable.icon_uncertain_death,
        roles = listOf(
            RoleProvider.Clockmaker,
            RoleProvider.Grandmother,
            RoleProvider.Librarian,
            RoleProvider.Empath,
            RoleProvider.FortuneTeller,
            RoleProvider.Exorcist,
            RoleProvider.Flowergirl,
            RoleProvider.Oracle,
            RoleProvider.Undertaker,
            RoleProvider.Artist,
            RoleProvider.Slayer,
            RoleProvider.Seamstress,
            RoleProvider.Monk,
            RoleProvider.Lunatic,
            RoleProvider.Mutant,
            RoleProvider.Sweetheart,
            RoleProvider.Recluse,
            RoleProvider.Godfather,
            RoleProvider.Assassin,
            RoleProvider.ScarletWoman,
            RoleProvider.Marionette,
            RoleProvider.NoDashii,
            RoleProvider.Pukka,
        ),
        prepareActions = listOf(
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Godfather,
                actionResId = RoleProvider.Godfather.prepareActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Marionette,
                actionResId = RoleProvider.Marionette.prepareActionId
            ),
            Action(actionType = ActionType.PLAYERS_7, role = null, actionResId = R.string.action_prepare_3_demon_bluffs),
            Action(actionType = ActionType.DAY, role = null, actionResId = R.string.action_prepare_bag),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Librarian,
                actionResId = RoleProvider.Librarian.prepareActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.FortuneTeller,
                actionResId = RoleProvider.FortuneTeller.prepareActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Recluse,
                actionResId = RoleProvider.Recluse.prepareActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Grandmother,
                actionResId = RoleProvider.Grandmother.prepareActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Lunatic,
                actionResId = RoleProvider.Lunatic.prepareActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.NoDashii,
                actionResId = RoleProvider.NoDashii.prepareActionId
            ),
            Action(actionType = ActionType.DAY, role = null, actionResId = R.string.action_start_first_night_phase),
        ),
        firstNightActions = listOf(
            Action(actionType = ActionType.PLAYERS_7, role = null, actionResId = R.string.action_show_demon),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Lunatic,
                actionResId = RoleProvider.Lunatic.firstNightActionId
            ),
            Action(
                actionType = ActionType.PLAYERS_7,
                role = null,
                actionResId = R.string.action_show_minions_and_bluffs
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Godfather,
                actionResId = RoleProvider.Godfather.firstNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Pukka,
                actionResId = RoleProvider.Pukka.firstNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Librarian,
                actionResId = RoleProvider.Librarian.firstNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Empath,
                actionResId = RoleProvider.Empath.firstNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.FortuneTeller,
                actionResId = RoleProvider.FortuneTeller.firstNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Grandmother,
                actionResId = RoleProvider.Grandmother.firstNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Clockmaker,
                actionResId = RoleProvider.Clockmaker.firstNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Seamstress,
                actionResId = RoleProvider.Seamstress.firstNightActionId
            ),
            Action(actionType = ActionType.NIGHT, role = null, actionResId = R.string.action_start_day_phase),
        ),
        secondNightActions = listOf(
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Monk,
                actionResId = RoleProvider.Monk.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.ScarletWoman,
                actionResId = RoleProvider.ScarletWoman.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Lunatic,
                actionResId = RoleProvider.Lunatic.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Exorcist,
                actionResId = RoleProvider.Exorcist.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Pukka,
                actionResId = RoleProvider.Pukka.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.NoDashii,
                actionResId = RoleProvider.NoDashii.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Assassin,
                actionResId = RoleProvider.Assassin.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Godfather,
                actionResId = RoleProvider.Godfather.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Sweetheart,
                actionResId = RoleProvider.Sweetheart.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Grandmother,
                actionResId = RoleProvider.Grandmother.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Empath,
                actionResId = RoleProvider.Empath.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.FortuneTeller,
                actionResId = RoleProvider.FortuneTeller.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Undertaker,
                actionResId = RoleProvider.Undertaker.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Flowergirl,
                actionResId = RoleProvider.Flowergirl.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Oracle,
                actionResId = RoleProvider.Oracle.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Seamstress,
                actionResId = RoleProvider.Seamstress.secondNightActionId
            ),
            Action(actionType = ActionType.NIGHT, role = null, actionResId = R.string.action_start_day_phase),
        ),
        dayActions = listOf(
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Flowergirl,
                actionResId = RoleProvider.Flowergirl.dayActionId
            ),
            Action(actionType = ActionType.DAY, role = null, actionResId = R.string.action_voting),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Undertaker,
                actionResId = RoleProvider.Undertaker.dayActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Sweetheart,
                actionResId = RoleProvider.Sweetheart.dayActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Godfather,
                actionResId = RoleProvider.Godfather.dayActionId
            ),
            Action(actionType = ActionType.DAY, role = null, actionResId = R.string.action_start_night_phase)
        ),
        sceneryTokens = listOf(Good, Evil, MarionetteMarionette),
    )

    val BadMoonRising = Scenery(
        sceneryNameRes = R.string.scenery_name_bad_moon_rising,
        iconRes = R.drawable.icon_bad_moon_rising,
        roles = listOf(
            RoleProvider.Grandmother,
            RoleProvider.Sailor,
            RoleProvider.Chambermaid,
            RoleProvider.Exorcist,
            RoleProvider.Innkeeper,
            RoleProvider.Gambler,
            RoleProvider.Gossip,
            RoleProvider.Courtier,
            RoleProvider.Professor,
            RoleProvider.Minstrel,
            RoleProvider.TeaLady,
            RoleProvider.Pacifist,
            RoleProvider.Fool,
            RoleProvider.Goon,
            RoleProvider.Lunatic,
            RoleProvider.Tinker,
            RoleProvider.Moonchild,
            RoleProvider.Godfather,
            RoleProvider.DevilsAdvocate,
            RoleProvider.Assassin,
            RoleProvider.Mastermind,
            RoleProvider.Zombuul,
            RoleProvider.Pukka,
            RoleProvider.Shabaloth,
            RoleProvider.Po,
        ),
        prepareActions = listOf(
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Godfather,
                actionResId = RoleProvider.Godfather.prepareActionId
            ),
            Action(actionType = ActionType.PLAYERS_7, role = null, actionResId = R.string.action_prepare_3_demon_bluffs),
            Action(actionType = ActionType.DAY, role = null, actionResId = R.string.action_prepare_bag),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Grandmother,
                actionResId = RoleProvider.Grandmother.prepareActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Lunatic,
                actionResId = RoleProvider.Lunatic.prepareActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.TeaLady,
                actionResId = RoleProvider.TeaLady.prepareActionId
            ),
            Action(actionType = ActionType.DAY, role = null, actionResId = R.string.action_start_first_night_phase),
        ),
        firstNightActions = listOf(
            Action(actionType = ActionType.PLAYERS_7, role = null, actionResId = R.string.action_show_demon),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Lunatic,
                actionResId = RoleProvider.Lunatic.firstNightActionId
            ),
            Action(actionType = ActionType.PLAYERS_7, role = null, actionResId = R.string.action_show_minions_and_bluffs),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Goon,
                actionResId = RoleProvider.Goon.firstNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Sailor,
                actionResId = RoleProvider.Sailor.firstNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Courtier,
                actionResId = RoleProvider.Courtier.firstNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Godfather,
                actionResId = RoleProvider.Godfather.firstNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.DevilsAdvocate,
                actionResId = RoleProvider.DevilsAdvocate.firstNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Pukka,
                actionResId = RoleProvider.Pukka.firstNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Fool,
                actionResId = RoleProvider.Fool.firstNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Grandmother,
                actionResId = RoleProvider.Grandmother.firstNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Chambermaid,
                actionResId = RoleProvider.Chambermaid.firstNightActionId
            ),
            Action(actionType = ActionType.NIGHT, role = null, actionResId = R.string.action_start_day_phase),
        ),
        secondNightActions = listOf(
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Goon,
                actionResId = RoleProvider.Goon.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Sailor,
                actionResId = RoleProvider.Sailor.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Innkeeper,
                actionResId = RoleProvider.Innkeeper.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Courtier,
                actionResId = RoleProvider.Courtier.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Gambler,
                actionResId = RoleProvider.Gambler.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.DevilsAdvocate,
                actionResId = RoleProvider.DevilsAdvocate.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Lunatic,
                actionResId = RoleProvider.Lunatic.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Exorcist,
                actionResId = RoleProvider.Exorcist.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Zombuul,
                actionResId = RoleProvider.Zombuul.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Pukka,
                actionResId = RoleProvider.Pukka.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Shabaloth,
                actionResId = RoleProvider.Shabaloth.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Po,
                actionResId = RoleProvider.Po.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Fool,
                actionResId = RoleProvider.Fool.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Assassin,
                actionResId = RoleProvider.Assassin.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Godfather,
                actionResId = RoleProvider.Godfather.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Professor,
                actionResId = RoleProvider.Professor.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Gossip,
                actionResId = RoleProvider.Gossip.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Moonchild,
                actionResId = RoleProvider.Moonchild.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Grandmother,
                actionResId = RoleProvider.Grandmother.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Chambermaid,
                actionResId = RoleProvider.Chambermaid.secondNightActionId
            ),
            Action(actionType = ActionType.NIGHT, role = null, actionResId = R.string.action_start_day_phase),
        ),
        dayActions = listOf(
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Innkeeper,
                actionResId = RoleProvider.Innkeeper.dayActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Moonchild,
                actionResId = RoleProvider.Sailor.dayActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Gossip,
                actionResId = RoleProvider.Gossip.dayActionId
            ),
            Action(actionType = ActionType.DAY, role = null, actionResId = R.string.action_voting),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.DevilsAdvocate,
                actionResId = RoleProvider.DevilsAdvocate.dayActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.TeaLady,
                actionResId = RoleProvider.TeaLady.dayActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Pacifist,
                actionResId = RoleProvider.Pacifist.dayActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Godfather,
                actionResId = RoleProvider.Godfather.dayActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Minstrel,
                actionResId = RoleProvider.Minstrel.dayActionId
            ),
            Action(actionType = ActionType.PLAYER, role = RoleProvider.Fool, actionResId = RoleProvider.Fool.dayActionId),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Mastermind,
                actionResId = RoleProvider.Mastermind.dayActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Zombuul,
                actionResId = RoleProvider.Zombuul.dayActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Sailor,
                actionResId = RoleProvider.Sailor.dayActionId
            ),
            Action(actionType = ActionType.DAY, role = null, actionResId = R.string.action_start_night_phase)
        ),
        sceneryTokens = listOf(Good, Evil),
    )

    val SectsNViolets = Scenery(
        sceneryNameRes = R.string.scenery_name_sects_and_violets,
        iconRes = R.drawable.icon_sects_and_violets,
        roles = listOf(
            RoleProvider.Clockmaker,
            RoleProvider.Dreamer,
            RoleProvider.SnakeCharmer,
            RoleProvider.Mathematician,
            RoleProvider.Flowergirl,
            RoleProvider.TownCrier,
            RoleProvider.Oracle,
            RoleProvider.Savant,
            RoleProvider.Seamstress,
            RoleProvider.Philosopher,
            RoleProvider.Artist,
            RoleProvider.Juggler,
            RoleProvider.Sage,
            RoleProvider.Mutant,
            RoleProvider.Sweetheart,
            RoleProvider.Barber,
            RoleProvider.Klutz,
            RoleProvider.EvilTwin,
            RoleProvider.Witch,
            RoleProvider.Cerenovus,
            RoleProvider.PitHag,
            RoleProvider.FangGu,
            RoleProvider.Vigormortis,
            RoleProvider.NoDashii,
            RoleProvider.Vortox,
        ),
        prepareActions = listOf(
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.FangGu,
                actionResId = RoleProvider.FangGu.prepareActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Vigormortis,
                actionResId = RoleProvider.Vigormortis.prepareActionId
            ),
            Action(actionType = ActionType.PLAYERS_7, role = null, actionResId = R.string.action_prepare_3_demon_bluffs),
            Action(actionType = ActionType.DAY, role = null, actionResId = R.string.action_prepare_bag),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Vortox,
                actionResId = RoleProvider.Vortox.prepareActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.NoDashii,
                actionResId = RoleProvider.NoDashii.prepareActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.EvilTwin,
                actionResId = RoleProvider.EvilTwin.prepareActionId
            ),
            Action(actionType = ActionType.DAY, role = null, actionResId = R.string.action_start_first_night_phase),
        ),
        firstNightActions = listOf(
            Action(actionType = ActionType.PLAYERS_7, role = null, actionResId = R.string.action_show_demon),
            Action(actionType = ActionType.PLAYERS_7, role = null, actionResId = R.string.action_show_minions_and_bluffs),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Philosopher,
                actionResId = RoleProvider.Philosopher.firstNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.SnakeCharmer,
                actionResId = RoleProvider.SnakeCharmer.firstNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.EvilTwin,
                actionResId = RoleProvider.EvilTwin.firstNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Witch,
                actionResId = RoleProvider.Witch.firstNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Cerenovus,
                actionResId = RoleProvider.Cerenovus.firstNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Clockmaker,
                actionResId = RoleProvider.Clockmaker.firstNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Dreamer,
                actionResId = RoleProvider.Dreamer.firstNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Seamstress,
                actionResId = RoleProvider.Seamstress.firstNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Mathematician,
                actionResId = RoleProvider.Mathematician.firstNightActionId
            ),
            Action(actionType = ActionType.NIGHT, role = null, actionResId = R.string.action_start_day_phase),
        ),
        secondNightActions = listOf(
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Philosopher,
                actionResId = RoleProvider.Philosopher.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.SnakeCharmer,
                actionResId = RoleProvider.SnakeCharmer.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Witch,
                actionResId = RoleProvider.Witch.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Cerenovus,
                actionResId = RoleProvider.Cerenovus.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.PitHag,
                actionResId = RoleProvider.PitHag.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.FangGu,
                actionResId = RoleProvider.FangGu.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Vigormortis,
                actionResId = RoleProvider.Vigormortis.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.NoDashii,
                actionResId = RoleProvider.NoDashii.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Vortox,
                actionResId = RoleProvider.Vortox.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Barber,
                actionResId = RoleProvider.Barber.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Sweetheart,
                actionResId = RoleProvider.Sweetheart.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Sage,
                actionResId = RoleProvider.Sage.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Dreamer,
                actionResId = RoleProvider.Dreamer.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Flowergirl,
                actionResId = RoleProvider.Flowergirl.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.TownCrier,
                actionResId = RoleProvider.TownCrier.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Oracle,
                actionResId = RoleProvider.Oracle.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Seamstress,
                actionResId = RoleProvider.Seamstress.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Juggler,
                actionResId = RoleProvider.Juggler.secondNightActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Mathematician,
                actionResId = RoleProvider.Mathematician.secondNightActionId
            ),
            Action(actionType = ActionType.NIGHT, role = null, actionResId = R.string.action_start_day_phase),
        ),
        dayActions = listOf(
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Vortox,
                actionResId = RoleProvider.Vortox.dayActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Flowergirl,
                actionResId = RoleProvider.Flowergirl.dayActionId
            ),
            Action(actionType = ActionType.DAY, role = null, actionResId = R.string.action_voting),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Juggler,
                actionResId = RoleProvider.Juggler.dayActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.TownCrier,
                actionResId = RoleProvider.TownCrier.dayActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Sweetheart,
                actionResId = RoleProvider.Sweetheart.dayActionId
            ),
            Action(
                actionType = ActionType.PLAYER,
                role = RoleProvider.Barber,
                actionResId = RoleProvider.Barber.dayActionId
            ),
            Action(actionType = ActionType.DAY, role = null, actionResId = R.string.action_start_night_phase)
        ),
        sceneryTokens = listOf(Good, Evil),
    )

    val all = listOf(
        TroubleBrewing,
        BadMoonRising,
        SectsNViolets,
        UncertainDeath
    )
}
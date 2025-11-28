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
                type = ActionType.PLAYER,
                role = RoleProvider.Baron,
                res = RoleProvider.Baron.prepareActionId
            ),
            Action(type = ActionType.PLAYER, role = RoleProvider.Drunk, res = RoleProvider.Drunk.prepareActionId),
            Action(
                type = ActionType.PLAYERS_7,
                role = null,
                res = R.string.action_prepare_3_demon_bluffs
            ),
            Action(type = ActionType.DAY, role = null, res = R.string.action_prepare_bag),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Washerwoman,
                res = RoleProvider.Washerwoman.prepareActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Librarian,
                res = RoleProvider.Librarian.prepareActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.FortuneTeller,
                res = RoleProvider.FortuneTeller.prepareActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Investigator,
                res = RoleProvider.Investigator.prepareActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Recluse,
                res = RoleProvider.Recluse.prepareActionId
            ),
            Action(type = ActionType.DAY, role = null, res = R.string.action_start_first_night_phase),
        ),
        firstNightActions = listOf(
            Action(type = ActionType.PLAYERS_7, role = null, res = R.string.action_show_demon),
            Action(
                type = ActionType.PLAYERS_7,
                role = null,
                res = R.string.action_show_minions_and_bluffs
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Poisoner,
                res = RoleProvider.Poisoner.firstNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Spy,
                res = RoleProvider.Spy.firstNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Washerwoman,
                res = RoleProvider.Washerwoman.firstNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Librarian,
                res = RoleProvider.Librarian.firstNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Investigator,
                res = RoleProvider.Investigator.firstNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Chef,
                res = RoleProvider.Chef.firstNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Empath,
                res = RoleProvider.Empath.firstNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.FortuneTeller,
                res = RoleProvider.FortuneTeller.firstNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Butler,
                res = RoleProvider.Butler.firstNightActionId
            ),
            Action(type = ActionType.NIGHT, role = null, res = R.string.action_start_day_phase),
        ),
        secondNightActions = listOf(
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Poisoner,
                res = RoleProvider.Poisoner.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Monk,
                res = RoleProvider.Monk.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Spy,
                res = RoleProvider.Spy.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.ScarletWoman,
                res = RoleProvider.ScarletWoman.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Imp,
                res = RoleProvider.Imp.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Ravenkeeper,
                res = RoleProvider.Ravenkeeper.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Undertaker,
                res = RoleProvider.Undertaker.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Empath,
                res = RoleProvider.Empath.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.FortuneTeller,
                res = RoleProvider.FortuneTeller.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Butler,
                res = RoleProvider.Butler.secondNightActionId
            ),
            Action(type = ActionType.NIGHT, role = null, res = R.string.action_start_day_phase),
        ),
        dayActions = listOf(
            Action(type = ActionType.DAY, role = null, res = R.string.action_voting),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Butler,
                res = RoleProvider.Butler.dayActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Undertaker,
                res = RoleProvider.Undertaker.dayActionId
            ),
            Action(type = ActionType.DAY, role = null, res = R.string.action_start_night_phase)
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
                type = ActionType.PLAYER,
                role = RoleProvider.Godfather,
                res = RoleProvider.Godfather.prepareActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Marionette,
                res = RoleProvider.Marionette.prepareActionId
            ),
            Action(type = ActionType.PLAYERS_7, role = null, res = R.string.action_prepare_3_demon_bluffs),
            Action(type = ActionType.DAY, role = null, res = R.string.action_prepare_bag),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Librarian,
                res = RoleProvider.Librarian.prepareActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.FortuneTeller,
                res = RoleProvider.FortuneTeller.prepareActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Recluse,
                res = RoleProvider.Recluse.prepareActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Grandmother,
                res = RoleProvider.Grandmother.prepareActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Lunatic,
                res = RoleProvider.Lunatic.prepareActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.NoDashii,
                res = RoleProvider.NoDashii.prepareActionId
            ),
            Action(type = ActionType.DAY, role = null, res = R.string.action_start_first_night_phase),
        ),
        firstNightActions = listOf(
            Action(type = ActionType.PLAYERS_7, role = null, res = R.string.action_show_demon),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Lunatic,
                res = RoleProvider.Lunatic.firstNightActionId
            ),
            Action(
                type = ActionType.PLAYERS_7,
                role = null,
                res = R.string.action_show_minions_and_bluffs
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Godfather,
                res = RoleProvider.Godfather.firstNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Pukka,
                res = RoleProvider.Pukka.firstNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Librarian,
                res = RoleProvider.Librarian.firstNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Empath,
                res = RoleProvider.Empath.firstNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.FortuneTeller,
                res = RoleProvider.FortuneTeller.firstNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Grandmother,
                res = RoleProvider.Grandmother.firstNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Clockmaker,
                res = RoleProvider.Clockmaker.firstNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Seamstress,
                res = RoleProvider.Seamstress.firstNightActionId
            ),
            Action(type = ActionType.NIGHT, role = null, res = R.string.action_start_day_phase),
        ),
        secondNightActions = listOf(
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Monk,
                res = RoleProvider.Monk.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.ScarletWoman,
                res = RoleProvider.ScarletWoman.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Lunatic,
                res = RoleProvider.Lunatic.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Exorcist,
                res = RoleProvider.Exorcist.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Pukka,
                res = RoleProvider.Pukka.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.NoDashii,
                res = RoleProvider.NoDashii.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Assassin,
                res = RoleProvider.Assassin.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Godfather,
                res = RoleProvider.Godfather.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Sweetheart,
                res = RoleProvider.Sweetheart.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Grandmother,
                res = RoleProvider.Grandmother.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Empath,
                res = RoleProvider.Empath.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.FortuneTeller,
                res = RoleProvider.FortuneTeller.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Undertaker,
                res = RoleProvider.Undertaker.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Flowergirl,
                res = RoleProvider.Flowergirl.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Oracle,
                res = RoleProvider.Oracle.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Seamstress,
                res = RoleProvider.Seamstress.secondNightActionId
            ),
            Action(type = ActionType.NIGHT, role = null, res = R.string.action_start_day_phase),
        ),
        dayActions = listOf(
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Flowergirl,
                res = RoleProvider.Flowergirl.dayActionId
            ),
            Action(type = ActionType.DAY, role = null, res = R.string.action_voting),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Undertaker,
                res = RoleProvider.Undertaker.dayActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Sweetheart,
                res = RoleProvider.Sweetheart.dayActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Godfather,
                res = RoleProvider.Godfather.dayActionId
            ),
            Action(type = ActionType.DAY, role = null, res = R.string.action_start_night_phase)
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
                type = ActionType.PLAYER,
                role = RoleProvider.Godfather,
                res = RoleProvider.Godfather.prepareActionId
            ),
            Action(type = ActionType.PLAYERS_7, role = null, res = R.string.action_prepare_3_demon_bluffs),
            Action(type = ActionType.DAY, role = null, res = R.string.action_prepare_bag),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Grandmother,
                res = RoleProvider.Grandmother.prepareActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Lunatic,
                res = RoleProvider.Lunatic.prepareActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.TeaLady,
                res = RoleProvider.TeaLady.prepareActionId
            ),
            Action(type = ActionType.DAY, role = null, res = R.string.action_start_first_night_phase),
        ),
        firstNightActions = listOf(
            Action(type = ActionType.PLAYERS_7, role = null, res = R.string.action_show_demon),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Lunatic,
                res = RoleProvider.Lunatic.firstNightActionId
            ),
            Action(type = ActionType.PLAYERS_7, role = null, res = R.string.action_show_minions_and_bluffs),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Goon,
                res = RoleProvider.Goon.firstNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Sailor,
                res = RoleProvider.Sailor.firstNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Courtier,
                res = RoleProvider.Courtier.firstNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Godfather,
                res = RoleProvider.Godfather.firstNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.DevilsAdvocate,
                res = RoleProvider.DevilsAdvocate.firstNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Pukka,
                res = RoleProvider.Pukka.firstNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Fool,
                res = RoleProvider.Fool.firstNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Grandmother,
                res = RoleProvider.Grandmother.firstNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Chambermaid,
                res = RoleProvider.Chambermaid.firstNightActionId
            ),
            Action(type = ActionType.NIGHT, role = null, res = R.string.action_start_day_phase),
        ),
        secondNightActions = listOf(
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Goon,
                res = RoleProvider.Goon.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Sailor,
                res = RoleProvider.Sailor.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Innkeeper,
                res = RoleProvider.Innkeeper.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Courtier,
                res = RoleProvider.Courtier.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Gambler,
                res = RoleProvider.Gambler.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.DevilsAdvocate,
                res = RoleProvider.DevilsAdvocate.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Lunatic,
                res = RoleProvider.Lunatic.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Exorcist,
                res = RoleProvider.Exorcist.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Zombuul,
                res = RoleProvider.Zombuul.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Pukka,
                res = RoleProvider.Pukka.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Shabaloth,
                res = RoleProvider.Shabaloth.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Po,
                res = RoleProvider.Po.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Fool,
                res = RoleProvider.Fool.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Assassin,
                res = RoleProvider.Assassin.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Godfather,
                res = RoleProvider.Godfather.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Professor,
                res = RoleProvider.Professor.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Gossip,
                res = RoleProvider.Gossip.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Moonchild,
                res = RoleProvider.Moonchild.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Grandmother,
                res = RoleProvider.Grandmother.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Chambermaid,
                res = RoleProvider.Chambermaid.secondNightActionId
            ),
            Action(type = ActionType.NIGHT, role = null, res = R.string.action_start_day_phase),
        ),
        dayActions = listOf(
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Innkeeper,
                res = RoleProvider.Innkeeper.dayActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Moonchild,
                res = RoleProvider.Sailor.dayActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Gossip,
                res = RoleProvider.Gossip.dayActionId
            ),
            Action(type = ActionType.DAY, role = null, res = R.string.action_voting),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.DevilsAdvocate,
                res = RoleProvider.DevilsAdvocate.dayActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.TeaLady,
                res = RoleProvider.TeaLady.dayActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Pacifist,
                res = RoleProvider.Pacifist.dayActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Godfather,
                res = RoleProvider.Godfather.dayActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Minstrel,
                res = RoleProvider.Minstrel.dayActionId
            ),
            Action(type = ActionType.PLAYER, role = RoleProvider.Fool, res = RoleProvider.Fool.dayActionId),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Mastermind,
                res = RoleProvider.Mastermind.dayActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Zombuul,
                res = RoleProvider.Zombuul.dayActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Sailor,
                res = RoleProvider.Sailor.dayActionId
            ),
            Action(type = ActionType.DAY, role = null, res = R.string.action_start_night_phase)
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
                type = ActionType.PLAYER,
                role = RoleProvider.FangGu,
                res = RoleProvider.FangGu.prepareActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Vigormortis,
                res = RoleProvider.Vigormortis.prepareActionId
            ),
            Action(type = ActionType.PLAYERS_7, role = null, res = R.string.action_prepare_3_demon_bluffs),
            Action(type = ActionType.DAY, role = null, res = R.string.action_prepare_bag),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Vortox,
                res = RoleProvider.Vortox.prepareActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.NoDashii,
                res = RoleProvider.NoDashii.prepareActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.EvilTwin,
                res = RoleProvider.EvilTwin.prepareActionId
            ),
            Action(type = ActionType.DAY, role = null, res = R.string.action_start_first_night_phase),
        ),
        firstNightActions = listOf(
            Action(type = ActionType.PLAYERS_7, role = null, res = R.string.action_show_demon),
            Action(type = ActionType.PLAYERS_7, role = null, res = R.string.action_show_minions_and_bluffs),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Philosopher,
                res = RoleProvider.Philosopher.firstNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.SnakeCharmer,
                res = RoleProvider.SnakeCharmer.firstNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.EvilTwin,
                res = RoleProvider.EvilTwin.firstNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Witch,
                res = RoleProvider.Witch.firstNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Cerenovus,
                res = RoleProvider.Cerenovus.firstNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Clockmaker,
                res = RoleProvider.Clockmaker.firstNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Dreamer,
                res = RoleProvider.Dreamer.firstNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Seamstress,
                res = RoleProvider.Seamstress.firstNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Mathematician,
                res = RoleProvider.Mathematician.firstNightActionId
            ),
            Action(type = ActionType.NIGHT, role = null, res = R.string.action_start_day_phase),
        ),
        secondNightActions = listOf(
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Philosopher,
                res = RoleProvider.Philosopher.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.SnakeCharmer,
                res = RoleProvider.SnakeCharmer.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Witch,
                res = RoleProvider.Witch.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Cerenovus,
                res = RoleProvider.Cerenovus.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.PitHag,
                res = RoleProvider.PitHag.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.FangGu,
                res = RoleProvider.FangGu.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Vigormortis,
                res = RoleProvider.Vigormortis.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.NoDashii,
                res = RoleProvider.NoDashii.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Vortox,
                res = RoleProvider.Vortox.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Barber,
                res = RoleProvider.Barber.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Sweetheart,
                res = RoleProvider.Sweetheart.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Sage,
                res = RoleProvider.Sage.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Dreamer,
                res = RoleProvider.Dreamer.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Flowergirl,
                res = RoleProvider.Flowergirl.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.TownCrier,
                res = RoleProvider.TownCrier.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Oracle,
                res = RoleProvider.Oracle.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Seamstress,
                res = RoleProvider.Seamstress.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Juggler,
                res = RoleProvider.Juggler.secondNightActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Mathematician,
                res = RoleProvider.Mathematician.secondNightActionId
            ),
            Action(type = ActionType.NIGHT, role = null, res = R.string.action_start_day_phase),
        ),
        dayActions = listOf(
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Vortox,
                res = RoleProvider.Vortox.dayActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Flowergirl,
                res = RoleProvider.Flowergirl.dayActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Juggler,
                res = RoleProvider.Juggler.dayActionId
            ),
            Action(type = ActionType.DAY, role = null, res = R.string.action_voting),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Witch,
                res = RoleProvider.Witch.dayActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.TownCrier,
                res = RoleProvider.TownCrier.dayActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Sweetheart,
                res = RoleProvider.Sweetheart.dayActionId
            ),
            Action(
                type = ActionType.PLAYER,
                role = RoleProvider.Barber,
                res = RoleProvider.Barber.dayActionId
            ),
            Action(type = ActionType.DAY, role = null, res = R.string.action_start_night_phase)
        ),
        sceneryTokens = listOf(Good, Evil),
    )

    val ALleechOfDistrust = Scenery(
        sceneryNameRes = R.string.scenery_name_a_lleech_of_distrust,
        iconRes = R.drawable.lleech_of_distrust,
        maxPlayers = 6,
        roles = listOf(
            RoleProvider.Pixie,
            RoleProvider.Undertaker,
            RoleProvider.Exorcist,
            RoleProvider.Fisherman,
            RoleProvider.Slayer,
            RoleProvider.Juggler,
            RoleProvider.Drunk,
            RoleProvider.Lunatic,
            RoleProvider.DevilsAdvocate,
            RoleProvider.Marionette,
            RoleProvider.Lleech,
        ),
        prepareActions = listOf(
            Action(type = ActionType.PLAYER, role = RoleProvider.Drunk, res = RoleProvider.Drunk.prepareActionId),
            Action(type = ActionType.PLAYER, role = RoleProvider.Marionette, res = RoleProvider.Marionette.prepareActionId),
            Action(type = ActionType.DAY, role = null, res = R.string.action_prepare_bag),
            Action(type = ActionType.PLAYER, role = RoleProvider.Lunatic, res = RoleProvider.Lunatic.prepareActionId),
            Action(type = ActionType.PLAYER, role = RoleProvider.Pixie, res = RoleProvider.Pixie.prepareActionId),
            Action(type = ActionType.DAY, role = null, res = R.string.action_start_first_night_phase),
        ),
        firstNightActions = listOf(
            Action(type = ActionType.PLAYER, role = RoleProvider.Lunatic, res = RoleProvider.Lunatic.firstNightActionId),
            Action(type = ActionType.PLAYER, role = RoleProvider.Lleech, res = RoleProvider.Lleech.firstNightActionId),
            Action(type = ActionType.PLAYER, role = RoleProvider.DevilsAdvocate, res = RoleProvider.DevilsAdvocate.firstNightActionId),
            Action(type = ActionType.PLAYER, role = RoleProvider.Pixie, res = RoleProvider.Pixie.firstNightActionId),
            Action(type = ActionType.NIGHT, role = null, res = R.string.action_start_day_phase),
        ),
        secondNightActions = listOf(
            Action(type = ActionType.PLAYER, role = RoleProvider.DevilsAdvocate, res = RoleProvider.DevilsAdvocate.secondNightActionId),
            Action(type = ActionType.PLAYER, role = RoleProvider.Lunatic, res = RoleProvider.Lunatic.secondNightActionId),
            Action(type = ActionType.PLAYER, role = RoleProvider.Exorcist, res = RoleProvider.Exorcist.secondNightActionId),
            Action(type = ActionType.PLAYER, role = RoleProvider.Lleech, res = RoleProvider.Lleech.secondNightActionId),
            Action(type = ActionType.PLAYER, role = RoleProvider.Undertaker, res = RoleProvider.Undertaker.secondNightActionId),
            Action(type = ActionType.PLAYER, role = RoleProvider.Juggler, res = RoleProvider.Juggler.secondNightActionId),
            Action(type = ActionType.NIGHT, role = null, res = R.string.action_start_day_phase),
        ),
        dayActions = listOf(
            Action(type = ActionType.PLAYER, role = RoleProvider.Juggler, res = RoleProvider.Juggler.dayActionId),
            Action(type = ActionType.DAY, role = null, res = R.string.action_voting),
            Action(type = ActionType.PLAYER, role = RoleProvider.Undertaker, res = RoleProvider.Undertaker.dayActionId),
            Action(type = ActionType.PLAYER, role = RoleProvider.DevilsAdvocate, res = RoleProvider.DevilsAdvocate.dayActionId),
            Action(type = ActionType.DAY, role = null, res = R.string.action_start_night_phase)
        ),
        sceneryTokens = listOf(Good, Evil, DrunkDrunk, MarionetteMarionette)
    )

    val NoGreaterJoy = Scenery(
        sceneryNameRes = R.string.scenery_name_no_greater_joy,
        iconRes = R.drawable.icon_no_greater_joy,
        maxPlayers = 6,
        roles = listOf(
            RoleProvider.Investigator,
            RoleProvider.Clockmaker,
            RoleProvider.Chambermaid,
            RoleProvider.Empath,
            RoleProvider.Artist,
            RoleProvider.Sage,
            RoleProvider.Klutz,
            RoleProvider.Drunk,
            RoleProvider.ScarletWoman,
            RoleProvider.Baron,
            RoleProvider.Imp,
        ),
        prepareActions = listOf(
            Action(type = ActionType.PLAYER, role = RoleProvider.Drunk, res = RoleProvider.Drunk.prepareActionId),
            Action(type = ActionType.PLAYER, role = RoleProvider.Baron, res = RoleProvider.Baron.prepareActionId),
            Action(type = ActionType.DAY, role = null, res = R.string.action_prepare_bag),
            Action(type = ActionType.PLAYER, role = RoleProvider.Investigator, res = RoleProvider.Investigator.prepareActionId),
            Action(type = ActionType.DAY, role = null, res = R.string.action_start_first_night_phase),
        ),
        firstNightActions = listOf(
            Action(type = ActionType.PLAYER, role = RoleProvider.Investigator, res = RoleProvider.Investigator.firstNightActionId),
            Action(type = ActionType.PLAYER, role = RoleProvider.Empath, res = RoleProvider.Empath.firstNightActionId),
            Action(type = ActionType.PLAYER, role = RoleProvider.Clockmaker, res = RoleProvider.Clockmaker.firstNightActionId),
            Action(type = ActionType.PLAYER, role = RoleProvider.Chambermaid, res = RoleProvider.Chambermaid.firstNightActionId),
            Action(type = ActionType.NIGHT, role = null, res = R.string.action_start_day_phase),
        ),
        secondNightActions = listOf(
            Action(type = ActionType.PLAYER, role = RoleProvider.ScarletWoman, res = RoleProvider.ScarletWoman.secondNightActionId),
            Action(type = ActionType.PLAYER, role = RoleProvider.Imp, res = RoleProvider.Imp.secondNightActionId),
            Action(type = ActionType.PLAYER, role = RoleProvider.Sage, res = RoleProvider.Sage.secondNightActionId),
            Action(type = ActionType.PLAYER, role = RoleProvider.Empath, res = RoleProvider.Empath.secondNightActionId),
            Action(type = ActionType.PLAYER, role = RoleProvider.Chambermaid, res = RoleProvider.Chambermaid.secondNightActionId),
            Action(type = ActionType.NIGHT, role = null, res = R.string.action_start_day_phase),
        ),
        dayActions = listOf(
            Action(type = ActionType.DAY, role = null, res = R.string.action_voting),
            Action(type = ActionType.DAY, role = null, res = R.string.action_start_night_phase)
        ),
        sceneryTokens = listOf(Good, Evil, DrunkDrunk)
    )

    val LaissezUnFaire = Scenery(
        sceneryNameRes = R.string.scenery_name_laissez_un_faire,
        iconRes = R.drawable.icon_laissez_un_faire,
        maxPlayers = 6,
        roles = listOf(
            RoleProvider.Balloonist,
            RoleProvider.Savant,
            RoleProvider.Amnesiac,
            RoleProvider.Fisherman,
            RoleProvider.Artist,
            RoleProvider.Cannibal,
            RoleProvider.Mutant,
            RoleProvider.Lunatic,
            RoleProvider.Widow,
            RoleProvider.Goblin,
            RoleProvider.Leviathan,
        ),
        prepareActions = listOf(
            Action(type = ActionType.PLAYER, role = RoleProvider.Balloonist, res = RoleProvider.Balloonist.prepareActionId),
            Action(type = ActionType.DAY, role = null, res = R.string.action_prepare_bag),
            Action(type = ActionType.PLAYER, role = RoleProvider.Lunatic, res = RoleProvider.Lunatic.prepareActionId),
            Action(type = ActionType.PLAYER, role = RoleProvider.Amnesiac, res = RoleProvider.Amnesiac.prepareActionId),
            Action(type = ActionType.PLAYER, role = RoleProvider.Widow, res = RoleProvider.Widow.prepareActionId),
            Action(type = ActionType.DAY, role = null, res = R.string.action_start_first_night_phase),
        ),
        firstNightActions = listOf(
            Action(type = ActionType.PLAYER, role = RoleProvider.Lunatic, res = RoleProvider.Lunatic.firstNightActionId),
            Action(type = ActionType.PLAYER, role = RoleProvider.Widow, res = RoleProvider.Widow.firstNightActionId),
            Action(type = ActionType.PLAYER, role = RoleProvider.Amnesiac, res = RoleProvider.Amnesiac.firstNightActionId),
            Action(type = ActionType.PLAYER, role = RoleProvider.Balloonist, res = RoleProvider.Balloonist.firstNightActionId),
            Action(type = ActionType.NIGHT, role = null, res = R.string.action_start_day_phase),
        ),
        secondNightActions = listOf(
            Action(type = ActionType.PLAYER, role = RoleProvider.Amnesiac, res = RoleProvider.Amnesiac.secondNightActionId),
            Action(type = ActionType.PLAYER, role = RoleProvider.Balloonist, res = RoleProvider.Balloonist.secondNightActionId),
            Action(type = ActionType.NIGHT, role = null, res = R.string.action_start_day_phase),
        ),
        dayActions = listOf(
            Action(type = ActionType.PLAYER, role = RoleProvider.Leviathan, res = RoleProvider.Leviathan.dayActionId),
            Action(type = ActionType.DAY, role = null, res = R.string.action_voting),
            Action(type = ActionType.PLAYER, role = RoleProvider.Goblin, res = RoleProvider.Goblin.dayActionId),
            Action(type = ActionType.PLAYER, role = RoleProvider.Cannibal, res = RoleProvider.Cannibal.dayActionId),
            Action(type = ActionType.DAY, role = null, res = R.string.action_start_night_phase)
        ),
        sceneryTokens = listOf(Good, Evil)
    )

    val all = listOf(
        TroubleBrewing,
        BadMoonRising,
        SectsNViolets,
        UncertainDeath,
        NoGreaterJoy,
        ALleechOfDistrust,
        LaissezUnFaire,
    )
}
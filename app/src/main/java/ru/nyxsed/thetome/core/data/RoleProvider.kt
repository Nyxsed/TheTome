package ru.nyxsed.thetome.core.data

import ru.nyxsed.thetome.R
import ru.nyxsed.thetome.core.data.TokenProvider.AngelProtected
import ru.nyxsed.thetome.core.data.TokenProvider.AngelSomethingBad
import ru.nyxsed.thetome.core.data.TokenProvider.ArtistNoAbility
import ru.nyxsed.thetome.core.data.TokenProvider.AssassinNoAbility
import ru.nyxsed.thetome.core.data.TokenProvider.BalloonistKnow
import ru.nyxsed.thetome.core.data.TokenProvider.BarberHaircut
import ru.nyxsed.thetome.core.data.TokenProvider.BaristaActsTwice
import ru.nyxsed.thetome.core.data.TokenProvider.BaristaSober
import ru.nyxsed.thetome.core.data.TokenProvider.BishopNominateEvil
import ru.nyxsed.thetome.core.data.TokenProvider.BishopNominateGood
import ru.nyxsed.thetome.core.data.TokenProvider.BoneCollectorHasAbility
import ru.nyxsed.thetome.core.data.TokenProvider.BoneCollectorNoAbility
import ru.nyxsed.thetome.core.data.TokenProvider.Bureaucrat3Votes
import ru.nyxsed.thetome.core.data.TokenProvider.ButlerMaster
import ru.nyxsed.thetome.core.data.TokenProvider.CacklejackNotMe
import ru.nyxsed.thetome.core.data.TokenProvider.CannibalLunch
import ru.nyxsed.thetome.core.data.TokenProvider.CannibalPoison
import ru.nyxsed.thetome.core.data.TokenProvider.CerenovusMad
import ru.nyxsed.thetome.core.data.TokenProvider.CourtierDrunk
import ru.nyxsed.thetome.core.data.TokenProvider.CourtierNoAbility
import ru.nyxsed.thetome.core.data.TokenProvider.DeusExFiascoWhoopsie
import ru.nyxsed.thetome.core.data.TokenProvider.DevilsAdvocateSurvivesExecution
import ru.nyxsed.thetome.core.data.TokenProvider.DrunkDrunk
import ru.nyxsed.thetome.core.data.TokenProvider.DuchessFalseInfo
import ru.nyxsed.thetome.core.data.TokenProvider.DuchessVisitor
import ru.nyxsed.thetome.core.data.TokenProvider.EvilTwinTwin
import ru.nyxsed.thetome.core.data.TokenProvider.ExorcistChosen
import ru.nyxsed.thetome.core.data.TokenProvider.FangGuKill
import ru.nyxsed.thetome.core.data.TokenProvider.FibbinNoAbility
import ru.nyxsed.thetome.core.data.TokenProvider.FishermanNoAbility
import ru.nyxsed.thetome.core.data.TokenProvider.FlowergirlNotVoted
import ru.nyxsed.thetome.core.data.TokenProvider.FlowergirlVoted
import ru.nyxsed.thetome.core.data.TokenProvider.FoolNoAbility
import ru.nyxsed.thetome.core.data.TokenProvider.FortuneTellerRedHerring
import ru.nyxsed.thetome.core.data.TokenProvider.GamblerKill
import ru.nyxsed.thetome.core.data.TokenProvider.GnomeAmigo
import ru.nyxsed.thetome.core.data.TokenProvider.GoblinClaimed
import ru.nyxsed.thetome.core.data.TokenProvider.GodfatherDiedToday
import ru.nyxsed.thetome.core.data.TokenProvider.GossipKill
import ru.nyxsed.thetome.core.data.TokenProvider.GrandmotherGrandchild
import ru.nyxsed.thetome.core.data.TokenProvider.HellsLibrarianSomethingBad
import ru.nyxsed.thetome.core.data.TokenProvider.ImpKill
import ru.nyxsed.thetome.core.data.TokenProvider.InnkeeperDrunk
import ru.nyxsed.thetome.core.data.TokenProvider.InnkeeperSafe
import ru.nyxsed.thetome.core.data.TokenProvider.InvestigatorMinion
import ru.nyxsed.thetome.core.data.TokenProvider.InvestigatorWrong
import ru.nyxsed.thetome.core.data.TokenProvider.JudgeNoAbility
import ru.nyxsed.thetome.core.data.TokenProvider.JugglerCorrect
import ru.nyxsed.thetome.core.data.TokenProvider.LeviathanDay1
import ru.nyxsed.thetome.core.data.TokenProvider.LeviathanDay2
import ru.nyxsed.thetome.core.data.TokenProvider.LeviathanDay3
import ru.nyxsed.thetome.core.data.TokenProvider.LeviathanDay4
import ru.nyxsed.thetome.core.data.TokenProvider.LeviathanDay5
import ru.nyxsed.thetome.core.data.TokenProvider.LeviathanGoodPlayerExecuted
import ru.nyxsed.thetome.core.data.TokenProvider.LibrarianOutsider
import ru.nyxsed.thetome.core.data.TokenProvider.LibrarianWrong
import ru.nyxsed.thetome.core.data.TokenProvider.LleechKill
import ru.nyxsed.thetome.core.data.TokenProvider.LleechPoison
import ru.nyxsed.thetome.core.data.TokenProvider.LunaticChosen
import ru.nyxsed.thetome.core.data.TokenProvider.MarionetteMarionette
import ru.nyxsed.thetome.core.data.TokenProvider.MathematicianAbnormal
import ru.nyxsed.thetome.core.data.TokenProvider.MinstrelEveryoneIsDrunk
import ru.nyxsed.thetome.core.data.TokenProvider.MonkSafe
import ru.nyxsed.thetome.core.data.TokenProvider.MoonchildKill
import ru.nyxsed.thetome.core.data.TokenProvider.NoDashiiKill
import ru.nyxsed.thetome.core.data.TokenProvider.NoDashiiPoison
import ru.nyxsed.thetome.core.data.TokenProvider.PhilosopherDrunk
import ru.nyxsed.thetome.core.data.TokenProvider.PhilosopherIs
import ru.nyxsed.thetome.core.data.TokenProvider.PixieMad
import ru.nyxsed.thetome.core.data.TokenProvider.Po3Attacks
import ru.nyxsed.thetome.core.data.TokenProvider.PoKill
import ru.nyxsed.thetome.core.data.TokenProvider.PoisonerPoison
import ru.nyxsed.thetome.core.data.TokenProvider.ProfessorNoAbility
import ru.nyxsed.thetome.core.data.TokenProvider.PukkaKill
import ru.nyxsed.thetome.core.data.TokenProvider.PukkaPoison
import ru.nyxsed.thetome.core.data.TokenProvider.SailorDrunk
import ru.nyxsed.thetome.core.data.TokenProvider.SeamstressNoAbility
import ru.nyxsed.thetome.core.data.TokenProvider.ShabalothAlive
import ru.nyxsed.thetome.core.data.TokenProvider.ShabalothKill
import ru.nyxsed.thetome.core.data.TokenProvider.SlayerNoAbility
import ru.nyxsed.thetome.core.data.TokenProvider.SnakeCharmerPoison
import ru.nyxsed.thetome.core.data.TokenProvider.SpiritOfIvoryNoMoreEvil
import ru.nyxsed.thetome.core.data.TokenProvider.StormCatcherSafe
import ru.nyxsed.thetome.core.data.TokenProvider.SweetheartDrunk
import ru.nyxsed.thetome.core.data.TokenProvider.TeaLadyCannotDie
import ru.nyxsed.thetome.core.data.TokenProvider.ThiefNegativeVote
import ru.nyxsed.thetome.core.data.TokenProvider.TowncrierMinionNominated
import ru.nyxsed.thetome.core.data.TokenProvider.ToymakerNoAttack
import ru.nyxsed.thetome.core.data.TokenProvider.UndertakerDiedToday
import ru.nyxsed.thetome.core.data.TokenProvider.VigormortisHasAbility
import ru.nyxsed.thetome.core.data.TokenProvider.VigormortisKill
import ru.nyxsed.thetome.core.data.TokenProvider.VigormortisPoison
import ru.nyxsed.thetome.core.data.TokenProvider.VirginNoAbility
import ru.nyxsed.thetome.core.data.TokenProvider.VortoxKill
import ru.nyxsed.thetome.core.data.TokenProvider.WasherwomanTownsfolk
import ru.nyxsed.thetome.core.data.TokenProvider.WasherwomanWrong
import ru.nyxsed.thetome.core.data.TokenProvider.WidowKnow
import ru.nyxsed.thetome.core.data.TokenProvider.WidowPoison
import ru.nyxsed.thetome.core.data.TokenProvider.WitchCursed
import ru.nyxsed.thetome.core.data.TokenProvider.ZenomancerGoal
import ru.nyxsed.thetome.core.data.TokenProvider.ZombuulDiedToday
import ru.nyxsed.thetome.core.data.TokenProvider.ZombuulKill
import ru.nyxsed.thetome.core.domain.models.Role
import ru.nyxsed.thetome.core.domain.models.RoleType

object RoleProvider {
    //Townsfolks
    val Washerwoman = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(WasherwomanTownsfolk, WasherwomanWrong),
        roleName = R.string.role_name_washerwoman,
        ability = R.string.role_ability_washerwoman,
        prepareActionId = R.string.action_prepare_washerwoman,
        firstNightActionId = R.string.action_first_night_washerwoman,
        iconRes = R.drawable.icon_washerwoman,
        nightPriority = 95,
    )

    val Librarian = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(LibrarianOutsider, LibrarianWrong),
        roleName = R.string.role_name_librarian,
        ability = R.string.role_ability_librarian,
        prepareActionId = R.string.action_prepare_librarian,
        firstNightActionId = R.string.action_first_night_librarian,
        iconRes = R.drawable.icon_librarian,
        nightPriority = 96,
    )

    val Investigator = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(InvestigatorMinion, InvestigatorWrong),
        roleName = R.string.role_name_investigator,
        ability = R.string.role_ability_investigator,
        prepareActionId = R.string.action_prepare_investigator,
        firstNightActionId = R.string.action_first_night_investigator,
        iconRes = R.drawable.icon_investigator,
        nightPriority = 97,
    )

    val Chef = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_chef,
        ability = R.string.role_ability_chef,
        firstNightActionId = R.string.action_first_night_chef,
        iconRes = R.drawable.icon_chef,
        nightPriority = 98,
    )

    val FortuneTeller = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(FortuneTellerRedHerring),
        roleName = R.string.role_name_fortune_teller,
        ability = R.string.role_ability_fortune_teller,
        prepareActionId = R.string.action_prepare_fortune_teller,
        firstNightActionId = R.string.action_second_night_fortune_teller,
        secondNightActionId = R.string.action_second_night_fortune_teller,
        iconRes = R.drawable.icon_fortuneteller,
        nightPriority = 88,
    )

    val Empath = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_empath,
        ability = R.string.role_ability_empath,
        firstNightActionId = R.string.action_second_night_empath,
        secondNightActionId = R.string.action_second_night_empath,
        iconRes = R.drawable.icon_empath,
        nightPriority = 87,
    )

    val Undertaker = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(UndertakerDiedToday),
        roleName = R.string.role_name_undertaker,
        ability = R.string.role_ability_undertaker,
        secondNightActionId = R.string.action_second_night_undertaker,
        dayActionId = R.string.action_day_undertaker,
        iconRes = R.drawable.icon_undertaker,
        nightPriority = 89,
    )

    val Monk = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(MonkSafe),
        roleName = R.string.role_name_monk,
        ability = R.string.role_ability_monk,
        secondNightActionId = R.string.action_second_night_monk,
        iconRes = R.drawable.icon_monk,
        nightPriority = 34,
    )

    val Ravenkeeper = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_ravenkeeper,
        ability = R.string.role_ability_ravenkeeper,
        secondNightActionId = R.string.action_second_night_ravenkeeper,
        iconRes = R.drawable.icon_ravenkeeper,
        nightPriority = 86,
    )

    val Virgin = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(VirginNoAbility),
        roleName = R.string.role_name_virgin,
        ability = R.string.role_ability_virgin,
        iconRes = R.drawable.icon_virgin,
        nightPriority = 999,
    )

    val Slayer = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(SlayerNoAbility),
        roleName = R.string.role_name_slayer,
        ability = R.string.role_ability_slayer,
        iconRes = R.drawable.icon_slayer,
        nightPriority = 999,
    )

    val Soldier = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_soldier,
        ability = R.string.role_ability_soldier,
        iconRes = R.drawable.icon_soldier,
        nightPriority = 999,
    )

    val Mayor = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_mayor,
        ability = R.string.role_ability_mayor,
        iconRes = R.drawable.icon_mayor,
        nightPriority = 999,
    )

    val Clockmaker = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_clockmaker,
        ability = R.string.role_ability_clockmaker,
        firstNightActionId = R.string.action_first_night_clockmaker,
        iconRes = R.drawable.icon_clockmaker,
        nightPriority = 61,
    )

    val Grandmother = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_grandmother,
        ability = R.string.role_ability_grandmother,
        iconRes = R.drawable.icon_grandmother,
        prepareActionId = R.string.action_prepare_grandmother,
        firstNightActionId = R.string.action_first_night_grandmother,
        secondNightActionId = R.string.action_second_night_grandmother,
        tokens = listOf(GrandmotherGrandchild),
        nightPriority = 84,
    )

    val Exorcist = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_exorcist,
        ability = R.string.role_ability_exorcist,
        iconRes = R.drawable.icon_exorcist,
        secondNightActionId = R.string.action_second_night_exorcist,
        tokens = listOf(ExorcistChosen),
        nightPriority = 47,
    )

    val Flowergirl = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_flowergirl,
        ability = R.string.role_ability_flowergirl,
        iconRes = R.drawable.icon_flowergirl,
        dayActionId = R.string.action_day_flowergirl,
        secondNightActionId = R.string.action_second_night_flowergirl,
        tokens = listOf(FlowergirlNotVoted, FlowergirlVoted),
        nightPriority = 91,
    )

    val Oracle = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_oracle,
        ability = R.string.role_ability_oracle,
        iconRes = R.drawable.icon_oracle,
        secondNightActionId = R.string.action_second_night_oracle,
        nightPriority = 93,
    )

    val Artist = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_artist,
        ability = R.string.role_ability_artist,
        iconRes = R.drawable.icon_artist,
        tokens = listOf(ArtistNoAbility),
        nightPriority = 999,
    )

    val Seamstress = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_seamstress,
        ability = R.string.role_ability_seamstress,
        iconRes = R.drawable.icon_seamstress,
        firstNightActionId = R.string.action_second_night_seamstress,
        secondNightActionId = R.string.action_second_night_seamstress,
        tokens = listOf(SeamstressNoAbility),
        nightPriority = 94,
    )

    val Sailor = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(SailorDrunk),
        roleName = R.string.role_name_sailor,
        ability = R.string.role_ability_sailor,
        firstNightActionId = R.string.action_second_night_sailor,
        secondNightActionId = R.string.action_second_night_sailor,
        dayActionId = R.string.action_day_sailor,
        iconRes = R.drawable.icon_sailor,
        nightPriority = 22,
    )

    val Chambermaid = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_chambermaid,
        ability = R.string.role_ability_chambermaid,
        firstNightActionId = R.string.action_second_night_chambermaid,
        secondNightActionId = R.string.action_second_night_chambermaid,
        iconRes = R.drawable.icon_chambermaid,
        nightPriority = 115,
    )

    val Innkeeper = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(InnkeeperSafe, InnkeeperSafe, InnkeeperDrunk),
        roleName = R.string.role_name_innkeeper,
        ability = R.string.role_ability_innkeeper,
        secondNightActionId = R.string.action_second_night_innkeeper,
        dayActionId = R.string.action_day_innkeeper,
        iconRes = R.drawable.icon_innkeeper,
        nightPriority = 29,
    )

    val Gambler = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(GamblerKill),
        roleName = R.string.role_name_gambler,
        ability = R.string.role_ability_gambler,
        secondNightActionId = R.string.action_second_night_gambler,
        iconRes = R.drawable.icon_gambler,
        nightPriority = 31,
    )

    val Gossip = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(GossipKill),
        roleName = R.string.role_name_gossip,
        ability = R.string.role_ability_gossip,
        secondNightActionId = R.string.action_second_night_gossip,
        dayActionId = R.string.action_day_gossip,
        iconRes = R.drawable.icon_gossip,
        nightPriority = 69,
    )

    val Courtier = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(CourtierDrunk, CourtierDrunk, CourtierDrunk, CourtierNoAbility),
        roleName = R.string.role_name_courtier,
        ability = R.string.role_ability_courtier,
        firstNightActionId = R.string.action_second_night_courtier,
        secondNightActionId = R.string.action_second_night_courtier,
        iconRes = R.drawable.icon_courtier,
        nightPriority = 28,
    )

    val Professor = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(ProfessorNoAbility),
        roleName = R.string.role_name_professor,
        ability = R.string.role_ability_professor,
        secondNightActionId = R.string.action_second_night_professor,
        iconRes = R.drawable.icon_professor,
        nightPriority = 76,
    )

    val Minstrel = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(MinstrelEveryoneIsDrunk),
        roleName = R.string.role_name_minstrel,
        ability = R.string.role_ability_minstrel,
        dayActionId = R.string.action_day_minstrel,
        iconRes = R.drawable.icon_minstrel,
        nightPriority = 10,
    )

    val TeaLady = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(TeaLadyCannotDie, TeaLadyCannotDie),
        roleName = R.string.role_name_tea_lady,
        ability = R.string.role_ability_tea_lady,
        prepareActionId = R.string.action_prepare_tea_lady,
        dayActionId = R.string.action_day_tea_lady,
        iconRes = R.drawable.icon_tealady,
        nightPriority = 999,
    )

    val Pacifist = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_pacifist,
        ability = R.string.role_ability_pacifist,
        dayActionId = R.string.action_day_pacifist,
        iconRes = R.drawable.icon_pacifist,
        nightPriority = 999,
    )

    val Fool = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(FoolNoAbility),
        roleName = R.string.role_name_fool,
        ability = R.string.role_ability_fool,
        firstNightActionId = R.string.action_first_night_fool,
        secondNightActionId = R.string.action_second_night_fool,
        dayActionId = R.string.action_day_fool,
        iconRes = R.drawable.icon_fool,
        nightPriority = 999,
    )

    val Dreamer = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_dreamer,
        ability = R.string.role_ability_dreamer,
        firstNightActionId = R.string.action_second_night_dreamer,
        secondNightActionId = R.string.action_second_night_dreamer,
        iconRes = R.drawable.icon_dreamer,
        nightPriority = 90,
    )

    val SnakeCharmer = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(SnakeCharmerPoison),
        roleName = R.string.role_name_snake_charmer,
        ability = R.string.role_ability_snake_charmer,
        firstNightActionId = R.string.action_second_night_snake_charmer,
        secondNightActionId = R.string.action_second_night_snake_charmer,
        iconRes = R.drawable.icon_snakecharmer,
        nightPriority = 33,
    )

    val Mathematician = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(MathematicianAbnormal),
        roleName = R.string.role_name_mathematician,
        ability = R.string.role_ability_mathematician,
        firstNightActionId = R.string.action_second_night_mathematician,
        secondNightActionId = R.string.action_second_night_mathematician,
        iconRes = R.drawable.icon_mathematician,
        nightPriority = 116,
    )

    val TownCrier = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(TowncrierMinionNominated),
        roleName = R.string.role_name_town_crier,
        ability = R.string.role_ability_town_crier,
        secondNightActionId = R.string.action_second_night_town_crier,
        dayActionId = R.string.action_day_town_crier,
        iconRes = R.drawable.icon_towncrier,
        nightPriority = 92,
    )

    val Savant = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_savant,
        ability = R.string.role_ability_savant,
        iconRes = R.drawable.icon_savant,
        nightPriority = 999,
    )

    val Philosopher = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_philosopher,
        ability = R.string.role_ability_philosopher,
        tokens = listOf(PhilosopherDrunk, PhilosopherIs),
        firstNightActionId = R.string.action_second_night_philosopher,
        secondNightActionId = R.string.action_second_night_philosopher,
        iconRes = R.drawable.icon_philosopher,
        nightPriority = 17,
    )

    val Juggler = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(
            JugglerCorrect,
            JugglerCorrect,
            JugglerCorrect,
            JugglerCorrect,
            JugglerCorrect,
        ),
        roleName = R.string.role_name_juggler,
        ability = R.string.role_ability_juggler,
        secondNightActionId = R.string.action_second_night_juggler,
        dayActionId = R.string.action_day_juggler,
        iconRes = R.drawable.icon_juggler,
        nightPriority = 103,
    )

    val Sage = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_sage,
        ability = R.string.role_ability_sage,
        secondNightActionId = R.string.action_second_night_sage,
        iconRes = R.drawable.icon_sage,
        nightPriority = 74,
    )

    val Pixie = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_pixie,
        ability = R.string.role_ability_pixie,
        tokens = listOf(PixieMad),
        prepareActionId = R.string.action_prepare_pixie,
        firstNightActionId = R.string.action_first_night_pixie,
        iconRes = R.drawable.icon_pixie,
        nightPriority = 68,
    )

    val Fisherman = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_fisherman,
        ability = R.string.role_ability_fisherman,
        tokens = listOf(FishermanNoAbility),
        iconRes = R.drawable.icon_fisherman,
        nightPriority = 999,
    )

    val Balloonist = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_balloonist,
        ability = R.string.role_ability_balloonist,
        tokens = listOf(BalloonistKnow, BalloonistKnow, BalloonistKnow, BalloonistKnow),
        prepareActionId = R.string.action_prepare_balloonist,
        firstNightActionId = R.string.action_second_night_balloonist,
        secondNightActionId = R.string.action_second_night_balloonist,
        iconRes = R.drawable.icon_balloonist,
        nightPriority = 104,
    )

    val Amnesiac = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_amnesiac,
        ability = R.string.role_ability_amnesiac,
        prepareActionId = R.string.action_prepare_amnesiac,
        firstNightActionId = R.string.action_second_night_amnesiac,
        secondNightActionId = R.string.action_second_night_amnesiac,
        iconRes = R.drawable.icon_amnesiac,
        nightPriority = 80,
    )

    val Cannibal = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_cannibal,
        ability = R.string.role_ability_cannibal,
        tokens = listOf(CannibalLunch, CannibalPoison),
        secondNightActionId = R.string.action_second_night_cannibal,
        dayActionId = R.string.action_day_cannibal,
        iconRes = R.drawable.icon_cannibal,
        nightPriority = 999,
    )


    //Outsiders
    val Butler = Role(
        type = RoleType.OUTSIDER,
        tokens = listOf(ButlerMaster),
        roleName = R.string.role_name_butler,
        ability = R.string.role_ability_butler,
        firstNightActionId = R.string.action_second_night_butler,
        secondNightActionId = R.string.action_second_night_butler,
        dayActionId = R.string.action_day_butler,
        iconRes = R.drawable.icon_butler,
        nightPriority = 110,
    )

    val Saint = Role(
        type = RoleType.OUTSIDER,
        roleName = R.string.role_name_saint,
        ability = R.string.role_ability_saint,
        iconRes = R.drawable.icon_saint,
        nightPriority = 999,
    )

    val Recluse = Role(
        type = RoleType.OUTSIDER,
        roleName = R.string.role_name_recluse,
        ability = R.string.role_ability_recluse,
        prepareActionId = R.string.action_prepare_recluse,
        iconRes = R.drawable.icon_recluse,
        nightPriority = 999,
    )

    val Drunk = Role(
        type = RoleType.OUTSIDER,
        roleName = R.string.role_name_drunk,
        ability = R.string.role_ability_Drunk,
        prepareActionId = R.string.action_prepare_Drunk,
        iconRes = R.drawable.icon_drunk,
        tokens = listOf(DrunkDrunk),
        nightPriority = 1,
    )

    val Lunatic = Role(
        type = RoleType.OUTSIDER,
        roleName = R.string.role_name_lunatic,
        ability = R.string.role_ability_lunatic,
        iconRes = R.drawable.icon_lunatic,
        prepareActionId = R.string.action_prepare_lunatic,
        firstNightActionId = R.string.action_first_night_lunatic,
        secondNightActionId = R.string.action_second_night_lunatic,
        tokens = listOf(LunaticChosen, LunaticChosen, LunaticChosen, LunaticChosen),
        nightPriority = 46,
    )

    val Mutant = Role(
        type = RoleType.OUTSIDER,
        roleName = R.string.role_name_mutant,
        ability = R.string.role_ability_mutant,
        iconRes = R.drawable.icon_mutant,
        nightPriority = 999,
    )

    val Sweetheart = Role(
        type = RoleType.OUTSIDER,
        roleName = R.string.role_name_sweetheart,
        ability = R.string.role_ability_sweetheart,
        iconRes = R.drawable.icon_sweetheart,
        dayActionId = R.string.action_day_sweetheart,
        secondNightActionId = R.string.action_day_sweetheart,
        tokens = listOf(SweetheartDrunk),
        nightPriority = 72,
    )

    val Goon = Role(
        type = RoleType.OUTSIDER,
        roleName = R.string.role_name_goon,
        ability = R.string.role_ability_goon,
        firstNightActionId = R.string.action_second_night_goon,
        secondNightActionId = R.string.action_second_night_goon,
        iconRes = R.drawable.icon_goon,
        nightPriority = 10,
    )

    val Tinker = Role(
        type = RoleType.OUTSIDER,
        roleName = R.string.role_name_tinker,
        ability = R.string.role_ability_tinker,
        iconRes = R.drawable.icon_tinker,
        nightPriority = 999,
    )

    val Moonchild = Role(
        type = RoleType.OUTSIDER,
        tokens = listOf(MoonchildKill),
        roleName = R.string.role_name_moonchild,
        ability = R.string.role_ability_moonchild,
        secondNightActionId = R.string.action_second_night_moonchild,
        dayActionId = R.string.action_day_moonchild,
        iconRes = R.drawable.icon_moonchild,
        nightPriority = 83,
    )

    val Barber = Role(
        type = RoleType.OUTSIDER,
        tokens = listOf(BarberHaircut),
        roleName = R.string.role_name_barber,
        ability = R.string.role_ability_barber,
        secondNightActionId = R.string.action_second_night_barber,
        dayActionId = R.string.action_day_barber,
        iconRes = R.drawable.icon_barber,
        nightPriority = 71,
    )

    val Klutz = Role(
        type = RoleType.OUTSIDER,
        roleName = R.string.role_name_klutz,
        ability = R.string.role_ability_klutz,
        iconRes = R.drawable.icon_klutz,
        nightPriority = 999,
    )

    //Minions
    val Poisoner = Role(
        type = RoleType.MINION,
        tokens = listOf(PoisonerPoison),
        roleName = R.string.role_name_poisoner,
        ability = R.string.role_ability_poisoner,
        firstNightActionId = R.string.action_second_night_poisoner,
        secondNightActionId = R.string.action_second_night_poisoner,
        iconRes = R.drawable.icon_poisoner,
        nightPriority = 26,
    )

    val Spy = Role(
        type = RoleType.MINION,
        roleName = R.string.role_name_spy,
        ability = R.string.role_ability_spy,
        firstNightActionId = R.string.action_second_night_spy,
        secondNightActionId = R.string.action_second_night_spy,
        iconRes = R.drawable.icon_spy,
        nightPriority = 111,
    )

    val Baron = Role(
        type = RoleType.MINION,
        roleName = R.string.role_name_baron,
        ability = R.string.role_ability_baron,
        prepareActionId = R.string.action_prepare_baron,
        iconRes = R.drawable.icon_baron,
        nightPriority = 1,
    )

    val ScarletWoman = Role(
        type = RoleType.MINION,
        roleName = R.string.role_name_scarlet_woman,
        ability = R.string.role_ability_scarlet_woman,
        secondNightActionId = R.string.action_second_night_scarlet_woman,
        iconRes = R.drawable.icon_scarletwoman,
        nightPriority = 44,
    )

    val Godfather = Role(
        type = RoleType.MINION,
        roleName = R.string.role_name_godfather,
        ability = R.string.role_ability_godfather,
        iconRes = R.drawable.icon_godfather,
        prepareActionId = R.string.action_prepare_godfather,
        firstNightActionId = R.string.action_first_night_godfather,
        secondNightActionId = R.string.action_second_night_godfather,
        dayActionId = R.string.action_day_godfather,
        tokens = listOf(GodfatherDiedToday),
        nightPriority = 67,
    )

    val Assassin = Role(
        type = RoleType.MINION,
        roleName = R.string.role_name_assassin,
        ability = R.string.role_ability_assassin,
        iconRes = R.drawable.icon_assassin,
        secondNightActionId = R.string.action_second_night_assassin,
        tokens = listOf(AssassinNoAbility),
        nightPriority = 66,
    )

    val Marionette = Role(
        type = RoleType.MINION,
        roleName = R.string.role_name_marionette,
        ability = R.string.role_ability_marionette,
        prepareActionId = R.string.action_prepare_marionette,
        iconRes = R.drawable.icon_marionette,
        tokens = listOf(MarionetteMarionette),
        nightPriority = 1,
    )

    val DevilsAdvocate = Role(
        type = RoleType.MINION,
        tokens = listOf(DevilsAdvocateSurvivesExecution),
        roleName = R.string.role_name_devils_advocate,
        ability = R.string.role_ability_devils_advocate,
        firstNightActionId = R.string.action_second_night_devils_advocate,
        secondNightActionId = R.string.action_second_night_devils_advocate,
        dayActionId = R.string.action_day_devils_advocate,
        iconRes = R.drawable.icon_devilsadvocate,
        nightPriority = 36,
    )

    val Mastermind = Role(
        type = RoleType.MINION,
        roleName = R.string.role_name_mastermind,
        ability = R.string.role_ability_mastermind,
        dayActionId = R.string.action_day_mastermind,
        iconRes = R.drawable.icon_mastermind,
        nightPriority = 999,
    )

    val EvilTwin = Role(
        type = RoleType.MINION,
        tokens = listOf(EvilTwinTwin),
        roleName = R.string.role_name_evil_twin,
        ability = R.string.role_ability_evil_twin,
        prepareActionId = R.string.action_prepare_evil_twin,
        firstNightActionId = R.string.action_first_night_evil_twin,
        iconRes = R.drawable.icon_eviltwin,
        nightPriority = 37,
    )

    val Witch = Role(
        type = RoleType.MINION,
        tokens = listOf(WitchCursed),
        roleName = R.string.role_name_witch,
        ability = R.string.role_ability_witch,
        firstNightActionId = R.string.action_second_night_witch,
        secondNightActionId = R.string.action_second_night_witch,
        dayActionId = R.string.action_day_witch,
        iconRes = R.drawable.icon_witch,
        nightPriority = 38,
    )

    val Cerenovus = Role(
        type = RoleType.MINION,
        tokens = listOf(CerenovusMad),
        roleName = R.string.role_name_cerenovus,
        ability = R.string.role_ability_cerenovus,
        firstNightActionId = R.string.action_second_night_cerenovus,
        secondNightActionId = R.string.action_second_night_cerenovus,
        iconRes = R.drawable.icon_cerenovus,
        nightPriority = 39,
    )

    val PitHag = Role(
        type = RoleType.MINION,
        roleName = R.string.role_name_pit_hag,
        ability = R.string.role_ability_pit_hag,
        secondNightActionId = R.string.action_second_night_pit_hag,
        iconRes = R.drawable.icon_pithag,
        nightPriority = 40,
    )

    val Widow = Role(
        type = RoleType.MINION,
        roleName = R.string.role_name_widow,
        ability = R.string.role_ability_widow,
        tokens = listOf(WidowPoison, WidowKnow),
        prepareActionId = R.string.action_prepare_widow,
        firstNightActionId = R.string.action_first_night_widow,
        iconRes = R.drawable.icon_widow,
        nightPriority = 27,
    )

    val Goblin = Role(
        type = RoleType.MINION,
        roleName = R.string.role_name_goblin,
        ability = R.string.role_ability_goblin,
        tokens = listOf(GoblinClaimed),
        dayActionId = R.string.action_day_goblin,
        iconRes = R.drawable.icon_goblin,
        nightPriority = 999,
    )

    //Demons
    val Imp = Role(
        type = RoleType.DEMON,
        tokens = listOf(ImpKill),
        roleName = R.string.role_name_imp,
        ability = R.string.role_ability_imp,
        secondNightActionId = R.string.action_second_night_imp,
        iconRes = R.drawable.icon_imp,
        nightPriority = 50,
    )

    val NoDashii = Role(
        type = RoleType.DEMON,
        tokens = listOf(NoDashiiPoison, NoDashiiPoison, NoDashiiKill),
        roleName = R.string.role_name_nodashii,
        ability = R.string.role_ability_nodashii,
        prepareActionId = R.string.action_prepare_nodashii,
        secondNightActionId = R.string.action_second_night_nodashii,
        iconRes = R.drawable.icon_nodashii,
        nightPriority = 56,
    )

    val Pukka = Role(
        type = RoleType.DEMON,
        tokens = listOf(PukkaKill, PukkaPoison),
        roleName = R.string.role_name_pukka,
        ability = R.string.role_ability_pukka,
        firstNightActionId = R.string.action_first_night_pukka,
        secondNightActionId = R.string.action_first_night_pukka,
        iconRes = R.drawable.icon_pukka,
        nightPriority = 52,
    )

    val Zombuul = Role(
        type = RoleType.DEMON,
        tokens = listOf(ZombuulKill, ZombuulDiedToday),
        roleName = R.string.role_name_zombuul,
        ability = R.string.role_ability_zombuul,
        secondNightActionId = R.string.action_second_night_zombuul,
        dayActionId = R.string.action_day_zombuul,
        iconRes = R.drawable.icon_zombuul,
        nightPriority = 51,
    )

    val Shabaloth = Role(
        type = RoleType.DEMON,
        tokens = listOf(ShabalothKill, ShabalothKill, ShabalothAlive),
        roleName = R.string.role_name_shabaloth,
        ability = R.string.role_ability_shabaloth,
        secondNightActionId = R.string.action_second_night_shabaloth,
        iconRes = R.drawable.icon_shabaloth,
        nightPriority = 53,
    )

    val Po = Role(
        type = RoleType.DEMON,
        tokens = listOf(PoKill, PoKill, PoKill, Po3Attacks),
        roleName = R.string.role_name_po,
        ability = R.string.role_ability_po,
        secondNightActionId = R.string.action_second_night_po,
        iconRes = R.drawable.icon_po,
        nightPriority = 54,
    )

    val FangGu = Role(
        type = RoleType.DEMON,
        tokens = listOf(FangGuKill),
        roleName = R.string.role_name_fang_gu,
        ability = R.string.role_ability_fang_gu,
        prepareActionId = R.string.action_prepare_fang_gu,
        secondNightActionId = R.string.action_second_night_fang_gu,
        iconRes = R.drawable.icon_fanggu,
        nightPriority = 55,
    )

    val Vigormortis = Role(
        type = RoleType.DEMON,
        tokens = listOf(
            VigormortisHasAbility,
            VigormortisHasAbility,
            VigormortisHasAbility,
            VigormortisKill,
            VigormortisPoison,
            VigormortisPoison,
            VigormortisPoison,
        ),
        roleName = R.string.role_name_vigormortis,
        ability = R.string.role_ability_vigormortis,
        prepareActionId = R.string.action_prepare_vigormortis,
        secondNightActionId = R.string.action_second_night_vigormortis,
        iconRes = R.drawable.icon_vigormortis,
        nightPriority = 59,
    )

    val Vortox = Role(
        type = RoleType.DEMON,
        tokens = listOf(VortoxKill),
        roleName = R.string.role_name_vortox,
        ability = R.string.role_ability_vortox,
        prepareActionId = R.string.action_prepare_vortox,
        secondNightActionId = R.string.action_second_night_vortox,
        dayActionId = R.string.action_day_vortox,
        iconRes = R.drawable.icon_vortox,
        nightPriority = 57,
    )

    val Lleech = Role(
        type = RoleType.DEMON,
        roleName = R.string.role_name_lleech,
        ability = R.string.role_ability_lleech,
        tokens = listOf(LleechKill, LleechPoison),
        firstNightActionId = R.string.action_first_night_lleech,
        secondNightActionId = R.string.action_second_night_lleech,
        iconRes = R.drawable.icon_lleech,
        nightPriority = 62,
    )

    val Leviathan = Role(
        type = RoleType.DEMON,
        roleName = R.string.role_name_leviathan,
        ability = R.string.role_ability_leviathan,
        tokens = listOf(LeviathanDay1, LeviathanDay2, LeviathanDay3, LeviathanDay4, LeviathanDay5, LeviathanGoodPlayerExecuted),
        dayActionId = R.string.action_day_leviathan,
        iconRes = R.drawable.icon_leviathan,
        nightPriority = 118,
    )

    // Travellers
    val Scapegoat = Role(
        type = RoleType.TRAVELLER,
        roleName = R.string.role_name_scapegoat,
        ability = R.string.role_ability_scapegoat,
        dayActionId = R.string.action_day_scapegoat,
        iconRes = R.drawable.icon_scapegoat,
        nightPriority = 999,
    )

    val Gunslinger = Role(
        type = RoleType.TRAVELLER,
        roleName = R.string.role_name_gunslinger,
        ability = R.string.role_ability_gunslinger,
        dayActionId = R.string.action_day_gunslinger,
        iconRes = R.drawable.icon_gunslinger,
        nightPriority = 999,
    )

    val Beggar = Role(
        type = RoleType.TRAVELLER,
        roleName = R.string.role_name_beggar,
        ability = R.string.role_ability_beggar,
        iconRes = R.drawable.icon_beggar,
        nightPriority = 999,
    )

    val Bureaucrat = Role(
        type = RoleType.TRAVELLER,
        roleName = R.string.role_name_bureaucrat,
        ability = R.string.role_ability_bureaucrat,
        secondNightActionId = R.string.action_second_night_bureaucrat,
        dayActionId = R.string.action_day_bureaucrat,
        iconRes = R.drawable.icon_bureaucrat,
        tokens = listOf(Bureaucrat3Votes),
        nightPriority = 13,
    )

    val Thief = Role(
        type = RoleType.TRAVELLER,
        roleName = R.string.role_name_thief,
        ability = R.string.role_ability_thief,
        secondNightActionId = R.string.action_second_night_thief,
        dayActionId = R.string.action_day_thief,
        iconRes = R.drawable.icon_thief,
        tokens = listOf(ThiefNegativeVote),
        nightPriority = 15,
    )

    val Butcher = Role(
        type = RoleType.TRAVELLER,
        roleName = R.string.role_name_butcher,
        ability = R.string.role_ability_butcher,
        dayActionId = R.string.action_day_butcher,
        iconRes = R.drawable.icon_butcher,
        nightPriority = 999,
    )

    val BoneCollector = Role(
        type = RoleType.TRAVELLER,
        roleName = R.string.role_name_bone_collector,
        ability = R.string.role_ability_bone_collector,
        secondNightActionId = R.string.action_second_night_bone_collector,
        iconRes = R.drawable.icon_bonecollector,
        tokens = listOf(BoneCollectorHasAbility, BoneCollectorNoAbility),
        nightPriority = 12,
    )

    val Harlot = Role(
        type = RoleType.TRAVELLER,
        roleName = R.string.role_name_harlot,
        ability = R.string.role_ability_harlot,
        firstNightActionId = R.string.action_second_night_harlot,
        secondNightActionId = R.string.action_second_night_harlot,
        iconRes = R.drawable.icon_harlot,
        nightPriority = 14,
    )

    val Barista = Role(
        type = RoleType.TRAVELLER,
        roleName = R.string.role_name_barista,
        ability = R.string.role_ability_barista,
        secondNightActionId = R.string.action_second_night_barista,
        iconRes = R.drawable.icon_barista,
        tokens = listOf(BaristaSober, BaristaActsTwice),
        nightPriority = 11,
    )

    val Deviant = Role(
        type = RoleType.TRAVELLER,
        roleName = R.string.role_name_deviant,
        ability = R.string.role_ability_deviant,
        iconRes = R.drawable.icon_deviant,
        nightPriority = 999,
    )

    val Apprentice = Role(
        type = RoleType.TRAVELLER,
        roleName = R.string.role_name_apprentice,
        ability = R.string.role_ability_apprentice,
        iconRes = R.drawable.icon_apprentice,
        nightPriority = 10,
    )

    val Matron = Role(
        type = RoleType.TRAVELLER,
        roleName = R.string.role_name_matron,
        ability = R.string.role_ability_matron,
        iconRes = R.drawable.icon_matron,
        nightPriority = 999,
    )

    val Voudon = Role(
        type = RoleType.TRAVELLER,
        roleName = R.string.role_name_voudon,
        ability = R.string.role_ability_voudon,
        dayActionId = R.string.action_day_voudon,
        iconRes = R.drawable.icon_voudon,
        nightPriority = 999,
    )

    val Judge = Role(
        type = RoleType.TRAVELLER,
        roleName = R.string.role_name_judge,
        ability = R.string.role_ability_judge,
        iconRes = R.drawable.icon_judge,
        tokens = listOf(JudgeNoAbility),
        nightPriority = 999,
    )

    val Bishop = Role(
        type = RoleType.TRAVELLER,
        roleName = R.string.role_name_bishop,
        ability = R.string.role_ability_bishop,
        iconRes = R.drawable.icon_bishop,
        tokens = listOf(BishopNominateEvil, BishopNominateGood),
        nightPriority = 999,
    )

    val Cacklejack = Role(
        type = RoleType.TRAVELLER,
        roleName = R.string.role_name_cacklejack,
        ability = R.string.role_ability_cacklejack,
        secondNightActionId = R.string.action_second_night_cacklejack,
        dayActionId = R.string.action_day_cacklejack,
        iconRes = R.drawable.icon_cacklejack,
        tokens = listOf(CacklejackNotMe),
        nightPriority = 999,
    )

    val Gangster = Role(
        type = RoleType.TRAVELLER,
        roleName = R.string.role_name_gangster,
        ability = R.string.role_ability_gangster,
        iconRes = R.drawable.icon_gangster,
        nightPriority = 999,
    )

    val Gnome = Role(
        type = RoleType.TRAVELLER,
        roleName = R.string.role_name_gnome,
        ability = R.string.role_ability_gnome,
        iconRes = R.drawable.icon_gnome,
        tokens = listOf(GnomeAmigo),
        nightPriority = 999,
    )

    // Fabled
    val Angel = Role(
        type = RoleType.FABLED,
        roleName = R.string.role_name_angel,
        ability = R.string.role_ability_angel,
        iconRes = R.drawable.icon_angel,
        prepareActionId = R.string.action_prepare_angel,
        tokens = listOf(AngelProtected, AngelSomethingBad),
        nightPriority = 4,
    )

    val Buddhist = Role(
        type = RoleType.FABLED,
        roleName = R.string.role_name_buddhist,
        ability = R.string.role_ability_buddhist,
        prepareActionId = R.string.action_prepare_buddhist,
        iconRes = R.drawable.icon_buddhist,
        nightPriority = 6,
    )

    val Doomsayer = Role(
        type = RoleType.FABLED,
        roleName = R.string.role_name_doomsayer,
        ability = R.string.role_ability_doomsayer,
        prepareActionId = R.string.action_prepare_doomsayer,
        iconRes = R.drawable.icon_doomsayer,
        nightPriority = 999,
    )

    val HellsLibrarian = Role(
        type = RoleType.FABLED,
        roleName = R.string.role_name_hells_librarian,
        ability = R.string.role_ability_hells_librarian,
        prepareActionId = R.string.action_prepare_hells_librarian,
        iconRes = R.drawable.icon_hellslibrarian,
        tokens = listOf(HellsLibrarianSomethingBad, HellsLibrarianSomethingBad, HellsLibrarianSomethingBad),
        nightPriority = 999,
    )

    val Fiddler = Role(
        type = RoleType.FABLED,
        roleName = R.string.role_name_fiddler,
        ability = R.string.role_ability_fiddler,
        prepareActionId = R.string.action_prepare_fiddler,
        iconRes = R.drawable.icon_fiddler,
        nightPriority = 999,
    )

    val Revolutionary = Role(
        type = RoleType.FABLED,
        roleName = R.string.role_name_revolutionary,
        ability = R.string.role_ability_revolutionary,
        prepareActionId = R.string.action_prepare_revolutionary,
        iconRes = R.drawable.icon_revolutionary,
        nightPriority = 999,
    )

    val Toymaker = Role(
        type = RoleType.FABLED,
        roleName = R.string.role_name_toymaker,
        ability = R.string.role_ability_toymaker,
        prepareActionId = R.string.action_prepare_toymaker,
        iconRes = R.drawable.icon_toymaker,
        tokens = listOf(ToymakerNoAttack),
        nightPriority = 7,
    )

    val Djinn = Role(
        type = RoleType.FABLED,
        roleName = R.string.role_name_djinn,
        ability = R.string.role_ability_djinn,
        prepareActionId = R.string.action_prepare_djinn,
        iconRes = R.drawable.icon_djinn,
        nightPriority = 999,
    )

    val Duchess = Role(
        type = RoleType.FABLED,
        roleName = R.string.role_name_duchess,
        ability = R.string.role_ability_duchess,
        prepareActionId = R.string.action_prepare_duchess,
        secondNightActionId = R.string.action_second_night_duchess,
        iconRes = R.drawable.icon_duchess,
        tokens = listOf(DuchessVisitor, DuchessVisitor, DuchessVisitor, DuchessFalseInfo),
        nightPriority = 5,
    )

    val Fibbin = Role(
        type = RoleType.FABLED,
        roleName = R.string.role_name_fibbin,
        ability = R.string.role_ability_fibbin,
        prepareActionId = R.string.action_prepare_fibbin,
        iconRes = R.drawable.icon_fibbin,
        tokens = listOf(FibbinNoAbility),
        nightPriority = 999,
    )

    val Sentinel = Role(
        type = RoleType.FABLED,
        roleName = R.string.role_name_sentinel,
        ability = R.string.role_ability_sentinel,
        prepareActionId = R.string.action_prepare_sentinel,
        iconRes = R.drawable.icon_sentinel,
        nightPriority = 999,
    )

    val SpiritOfIvory = Role(
        type = RoleType.FABLED,
        roleName = R.string.role_name_spirit_of_ivory,
        ability = R.string.role_ability_spirit_of_ivory,
        prepareActionId = R.string.action_prepare_spirit_of_ivory,
        iconRes = R.drawable.icon_spiritofivory,
        tokens = listOf(SpiritOfIvoryNoMoreEvil),
        nightPriority = 999,
    )

    val DeusExFiasco = Role(
        type = RoleType.FABLED,
        roleName = R.string.role_name_deus_ex_fiasco,
        ability = R.string.role_ability_deus_ex_fiasco,
        prepareActionId = R.string.action_prepare_deus_ex_fiasco,
        iconRes = R.drawable.icon_deusexfiasco,
        tokens = listOf(DeusExFiascoWhoopsie),
        nightPriority = 999,
    )

    val Ferryman = Role(
        type = RoleType.FABLED,
        roleName = R.string.role_name_ferryman,
        ability = R.string.role_ability_ferryman,
        iconRes = R.drawable.icon_ferryman,
        nightPriority = 999,
    )

    val Zenomancer = Role(
        type = RoleType.FABLED,
        roleName = R.string.role_name_zenomancer,
        ability = R.string.role_ability_zenomancer,
        prepareActionId = R.string.action_prepare_zenomancer,
        firstNightActionId = R.string.action_first_night_zenomancer,
        iconRes = R.drawable.icon_zenomancer,
        tokens = listOf(ZenomancerGoal, ZenomancerGoal, ZenomancerGoal, ZenomancerGoal, ZenomancerGoal),
        nightPriority = 999,
    )

    val Bootlegger = Role(
        type = RoleType.FABLED,
        roleName = R.string.role_name_bootlegger,
        ability = R.string.role_ability_bootlegger,
        prepareActionId = R.string.action_prepare_bootlegger,
        iconRes = R.drawable.icon_bootlegger,
        nightPriority = 999,
    )

    val Tor = Role(
        type = RoleType.FABLED,
        roleName = R.string.role_name_tor,
        ability = R.string.role_ability_tor,
        prepareActionId = R.string.action_prepare_tor,
        iconRes = R.drawable.icon_tor,
        nightPriority = 999,
    )

    val StormCatcher = Role(
        type = RoleType.FABLED,
        roleName = R.string.role_name_storm_catcher,
        ability = R.string.role_ability_storm_catcher,
        prepareActionId = R.string.action_prepare_storm_catcher,
        firstNightActionId = R.string.action_first_night_storm_catcher,
        iconRes = R.drawable.icon_stormcatcher,
        tokens = listOf(StormCatcherSafe),
        nightPriority = 8,
    )

    val Gardener = Role(
        type = RoleType.FABLED,
        roleName = R.string.role_name_gardener,
        ability = R.string.role_ability_gardener,
        prepareActionId = R.string.action_prepare_gardener,
        iconRes = R.drawable.icon_gardener,
        nightPriority = 999,
    )

    val BigWig = Role(
        type = RoleType.FABLED,
        roleName = R.string.role_name_big_wig,
        ability = R.string.role_ability_big_wig,
        prepareActionId = R.string.action_prepare_big_wig,
        iconRes = R.drawable.icon_big_wig,
        nightPriority = 999,
    )


    val allTravelers = listOf(
        Scapegoat,
        Gunslinger,
        Beggar,
        Bureaucrat,
        Thief,
        Butcher,
        BoneCollector,
        Harlot,
        Barista,
        Deviant,
        Apprentice,
        Matron,
        Voudon,
        Judge,
        Bishop,
        Cacklejack,
        Gangster,
        Gnome,
    )

    val allFables = listOf(
        Angel,
        Buddhist,
        Doomsayer,
        HellsLibrarian,
        Fiddler,
        Revolutionary,
        Toymaker,
        Djinn,
        Duchess,
        Fibbin,
        Sentinel,
        SpiritOfIvory,
        DeusExFiasco,
        Ferryman,
        Zenomancer,
        Bootlegger,
        Tor,
        StormCatcher,
        Gardener,
        BigWig,
    )
}
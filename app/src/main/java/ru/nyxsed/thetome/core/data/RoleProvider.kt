package ru.nyxsed.thetome.core.data

import ru.nyxsed.thetome.R
import ru.nyxsed.thetome.core.data.TokenProvider.ArtistNoAbility
import ru.nyxsed.thetome.core.data.TokenProvider.AssassinNoAbility
import ru.nyxsed.thetome.core.data.TokenProvider.BarberHaircut
import ru.nyxsed.thetome.core.data.TokenProvider.ButlerMaster
import ru.nyxsed.thetome.core.data.TokenProvider.CerenovusMad
import ru.nyxsed.thetome.core.data.TokenProvider.CourtierDrunk
import ru.nyxsed.thetome.core.data.TokenProvider.CourtierNoAbility
import ru.nyxsed.thetome.core.data.TokenProvider.DevilsAdvocateSurvivesExecution
import ru.nyxsed.thetome.core.data.TokenProvider.EvilTwinTwin
import ru.nyxsed.thetome.core.data.TokenProvider.ExorcistChosen
import ru.nyxsed.thetome.core.data.TokenProvider.FangGuKill
import ru.nyxsed.thetome.core.data.TokenProvider.FishermanNoAbility
import ru.nyxsed.thetome.core.data.TokenProvider.FlowergirlNotVoted
import ru.nyxsed.thetome.core.data.TokenProvider.FlowergirlVoted
import ru.nyxsed.thetome.core.data.TokenProvider.FoolNoAbility
import ru.nyxsed.thetome.core.data.TokenProvider.FortuneTellerRedHerring
import ru.nyxsed.thetome.core.data.TokenProvider.GamblerKill
import ru.nyxsed.thetome.core.data.TokenProvider.GodfatherDiedToday
import ru.nyxsed.thetome.core.data.TokenProvider.GossipKill
import ru.nyxsed.thetome.core.data.TokenProvider.GrandmotherGrandchild
import ru.nyxsed.thetome.core.data.TokenProvider.ImpKill
import ru.nyxsed.thetome.core.data.TokenProvider.InnkeeperDrunk
import ru.nyxsed.thetome.core.data.TokenProvider.InnkeeperSafe
import ru.nyxsed.thetome.core.data.TokenProvider.InvestigatorMinion
import ru.nyxsed.thetome.core.data.TokenProvider.InvestigatorWrong
import ru.nyxsed.thetome.core.data.TokenProvider.JugglerCorrect
import ru.nyxsed.thetome.core.data.TokenProvider.LibrarianOutsider
import ru.nyxsed.thetome.core.data.TokenProvider.LibrarianWrong
import ru.nyxsed.thetome.core.data.TokenProvider.LleechKill
import ru.nyxsed.thetome.core.data.TokenProvider.LleechPoison
import ru.nyxsed.thetome.core.data.TokenProvider.LunaticChosen
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
import ru.nyxsed.thetome.core.data.TokenProvider.SweetheartDrunk
import ru.nyxsed.thetome.core.data.TokenProvider.TeaLadyCannotDie
import ru.nyxsed.thetome.core.data.TokenProvider.TowncrierMinionNominated
import ru.nyxsed.thetome.core.data.TokenProvider.UndertakerDiedToday
import ru.nyxsed.thetome.core.data.TokenProvider.VigormortisHasAbility
import ru.nyxsed.thetome.core.data.TokenProvider.VigormortisKill
import ru.nyxsed.thetome.core.data.TokenProvider.VigormortisPoison
import ru.nyxsed.thetome.core.data.TokenProvider.VirginNoAbility
import ru.nyxsed.thetome.core.data.TokenProvider.VortoxKill
import ru.nyxsed.thetome.core.data.TokenProvider.WasherwomanTownsfolk
import ru.nyxsed.thetome.core.data.TokenProvider.WasherwomanWrong
import ru.nyxsed.thetome.core.data.TokenProvider.WitchCursed
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
    )

    val Librarian = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(LibrarianOutsider, LibrarianWrong),
        roleName = R.string.role_name_librarian,
        ability = R.string.role_ability_librarian,
        prepareActionId = R.string.action_prepare_librarian,
        firstNightActionId = R.string.action_first_night_librarian,
        iconRes = R.drawable.icon_librarian,
    )

    val Investigator = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(InvestigatorMinion, InvestigatorWrong),
        roleName = R.string.role_name_investigator,
        ability = R.string.role_ability_investigator,
        prepareActionId = R.string.action_prepare_investigator,
        firstNightActionId = R.string.action_first_night_investigator,
        iconRes = R.drawable.icon_investigator,
    )

    val Chef = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_chef,
        ability = R.string.role_ability_chef,
        firstNightActionId = R.string.action_first_night_chef,
        iconRes = R.drawable.icon_chef,
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
    )

    val Empath = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_empath,
        ability = R.string.role_ability_empath,
        firstNightActionId = R.string.action_second_night_empath,
        secondNightActionId = R.string.action_second_night_empath,
        iconRes = R.drawable.icon_empath,
    )

    val Undertaker = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(UndertakerDiedToday),
        roleName = R.string.role_name_undertaker,
        ability = R.string.role_ability_undertaker,
        secondNightActionId = R.string.action_second_night_undertaker,
        dayActionId = R.string.action_day_undertaker,
        iconRes = R.drawable.icon_undertaker,
    )

    val Monk = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(MonkSafe),
        roleName = R.string.role_name_monk,
        ability = R.string.role_ability_monk,
        secondNightActionId = R.string.action_second_night_monk,
        iconRes = R.drawable.icon_monk,
    )

    val Ravenkeeper = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_ravenkeeper,
        ability = R.string.role_ability_ravenkeeper,
        secondNightActionId = R.string.action_second_night_ravenkeeper,
        iconRes = R.drawable.icon_ravenkeeper,
    )

    val Virgin = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(VirginNoAbility),
        roleName = R.string.role_name_virgin,
        ability = R.string.role_ability_virgin,
        iconRes = R.drawable.icon_virgin,
    )

    val Slayer = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(SlayerNoAbility),
        roleName = R.string.role_name_slayer,
        ability = R.string.role_ability_slayer,
        iconRes = R.drawable.icon_slayer,
    )

    val Soldier = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_soldier,
        ability = R.string.role_ability_soldier,
        iconRes = R.drawable.icon_soldier,
    )

    val Mayor = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_mayor,
        ability = R.string.role_ability_mayor,
        iconRes = R.drawable.icon_mayor,
    )

    val Clockmaker = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_clockmaker,
        ability = R.string.role_ability_clockmaker,
        firstNightActionId = R.string.action_first_night_clockmaker,
        iconRes = R.drawable.icon_clockmaker,
    )

    val Grandmother = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_grandmother,
        ability = R.string.role_ability_grandmother,
        iconRes = R.drawable.icon_grandmother,
        prepareActionId = R.string.action_prepare_grandmother,
        firstNightActionId = R.string.action_first_night_grandmother,
        secondNightActionId = R.string.action_second_night_grandmother,
        tokens = listOf(GrandmotherGrandchild)
    )

    val Exorcist = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_exorcist,
        ability = R.string.role_ability_exorcist,
        iconRes = R.drawable.icon_exorcist,
        secondNightActionId = R.string.action_second_night_exorcist,
        tokens = listOf(ExorcistChosen)
    )

    val Flowergirl = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_flowergirl,
        ability = R.string.role_ability_flowergirl,
        iconRes = R.drawable.icon_flowergirl,
        dayActionId = R.string.action_day_flowergirl,
        secondNightActionId = R.string.action_second_night_flowergirl,
        tokens = listOf(FlowergirlNotVoted, FlowergirlVoted)
    )

    val Oracle = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_oracle,
        ability = R.string.role_ability_oracle,
        iconRes = R.drawable.icon_oracle,
        secondNightActionId = R.string.action_second_night_oracle
    )

    val Artist = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_artist,
        ability = R.string.role_ability_artist,
        iconRes = R.drawable.icon_artist,
        tokens = listOf(ArtistNoAbility)
    )

    val Seamstress = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_seamstress,
        ability = R.string.role_ability_seamstress,
        iconRes = R.drawable.icon_seamstress,
        firstNightActionId = R.string.action_second_night_seamstress,
        secondNightActionId = R.string.action_second_night_seamstress,
        tokens = listOf(SeamstressNoAbility)
    )

    val Sailor = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(SailorDrunk),
        roleName = R.string.role_name_sailor,
        ability = R.string.role_ability_sailor,
        firstNightActionId = R.string.action_second_night_sailor,
        secondNightActionId = R.string.action_second_night_sailor,
        dayActionId = R.string.action_day_sailor,
        iconRes = R.drawable.icon_sailor
    )

    val Chambermaid = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_chambermaid,
        ability = R.string.role_ability_chambermaid,
        firstNightActionId = R.string.action_second_night_chambermaid,
        secondNightActionId = R.string.action_second_night_chambermaid,
        iconRes = R.drawable.icon_chambermaid
    )

    val Innkeeper = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(InnkeeperSafe, InnkeeperSafe, InnkeeperDrunk),
        roleName = R.string.role_name_innkeeper,
        ability = R.string.role_ability_innkeeper,
        secondNightActionId = R.string.action_second_night_innkeeper,
        dayActionId = R.string.action_day_innkeeper,
        iconRes = R.drawable.icon_innkeeper
    )

    val Gambler = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(GamblerKill),
        roleName = R.string.role_name_gambler,
        ability = R.string.role_ability_gambler,
        secondNightActionId = R.string.action_second_night_gambler,
        iconRes = R.drawable.icon_gambler
    )

    val Gossip = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(GossipKill),
        roleName = R.string.role_name_gossip,
        ability = R.string.role_ability_gossip,
        secondNightActionId = R.string.action_second_night_gossip,
        dayActionId = R.string.action_day_gossip,
        iconRes = R.drawable.icon_gossip
    )

    val Courtier = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(CourtierDrunk, CourtierDrunk, CourtierDrunk, CourtierNoAbility),
        roleName = R.string.role_name_courtier,
        ability = R.string.role_ability_courtier,
        firstNightActionId = R.string.action_second_night_courtier,
        secondNightActionId = R.string.action_second_night_courtier,
        iconRes = R.drawable.icon_courtier
    )

    val Professor = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(ProfessorNoAbility),
        roleName = R.string.role_name_professor,
        ability = R.string.role_ability_professor,
        secondNightActionId = R.string.action_second_night_professor,
        iconRes = R.drawable.icon_professor
    )

    val Minstrel = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(MinstrelEveryoneIsDrunk),
        roleName = R.string.role_name_minstrel,
        ability = R.string.role_ability_minstrel,
        dayActionId = R.string.action_day_minstrel,
        iconRes = R.drawable.icon_minstrel
    )

    val TeaLady = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(TeaLadyCannotDie, TeaLadyCannotDie),
        roleName = R.string.role_name_tea_lady,
        ability = R.string.role_ability_tea_lady,
        prepareActionId = R.string.action_prepare_tea_lady,
        dayActionId = R.string.action_day_tea_lady,
        iconRes = R.drawable.icon_tealady
    )

    val Pacifist = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_pacifist,
        ability = R.string.role_ability_pacifist,
        dayActionId = R.string.action_day_pacifist,
        iconRes = R.drawable.icon_pacifist
    )

    val Fool = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(FoolNoAbility),
        roleName = R.string.role_name_fool,
        ability = R.string.role_ability_fool,
        firstNightActionId = R.string.action_first_night_fool,
        secondNightActionId = R.string.action_second_night_fool,
        dayActionId = R.string.action_day_fool,
        iconRes = R.drawable.icon_fool
    )

    val Dreamer = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_dreamer,
        ability = R.string.role_ability_dreamer,
        firstNightActionId = R.string.action_second_night_dreamer,
        secondNightActionId = R.string.action_second_night_dreamer,
        iconRes = R.drawable.icon_dreamer
    )

    val SnakeCharmer = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(SnakeCharmerPoison),
        roleName = R.string.role_name_snake_charmer,
        ability = R.string.role_ability_snake_charmer,
        firstNightActionId = R.string.action_second_night_snake_charmer,
        secondNightActionId = R.string.action_second_night_snake_charmer,
        iconRes = R.drawable.icon_snakecharmer
    )

    val Mathematician = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(MathematicianAbnormal),
        roleName = R.string.role_name_mathematician,
        ability = R.string.role_ability_mathematician,
        firstNightActionId = R.string.action_second_night_mathematician,
        secondNightActionId = R.string.action_second_night_mathematician,
        iconRes = R.drawable.icon_mathematician
    )

    val TownCrier = Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(TowncrierMinionNominated),
        roleName = R.string.role_name_town_crier,
        ability = R.string.role_ability_town_crier,
        secondNightActionId = R.string.action_second_night_town_crier,
        dayActionId = R.string.action_day_town_crier,
        iconRes = R.drawable.icon_towncrier
    )

    val Savant = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_savant,
        ability = R.string.role_ability_savant,
        iconRes = R.drawable.icon_savant
    )

    val Philosopher = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_philosopher,
        ability = R.string.role_ability_philosopher,
        tokens = listOf(PhilosopherDrunk, PhilosopherIs),
        firstNightActionId = R.string.action_second_night_philosopher,
        secondNightActionId = R.string.action_second_night_philosopher,
        iconRes = R.drawable.icon_philosopher,
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
        iconRes = R.drawable.icon_juggler
    )

    val Sage = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_sage,
        ability = R.string.role_ability_sage,
        secondNightActionId = R.string.action_second_night_sage,
        iconRes = R.drawable.icon_sage
    )

    val Pixie = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_pixie,
        ability = R.string.role_ability_pixie,
        tokens = listOf(PixieMad),
        prepareActionId = R.string.action_prepare_pixie,
        firstNightActionId = R.string.action_first_night_pixie,
        iconRes = R.drawable.icon_pixie
    )

    val Fisherman = Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_fisherman,
        ability = R.string.role_ability_fisherman,
        tokens = listOf(FishermanNoAbility),
        iconRes = R.drawable.icon_fisherman
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
    )

    val Saint = Role(
        type = RoleType.OUTSIDER,
        roleName = R.string.role_name_saint,
        ability = R.string.role_ability_saint,
        iconRes = R.drawable.icon_saint,
    )

    val Recluse = Role(
        type = RoleType.OUTSIDER,
        roleName = R.string.role_name_recluse,
        ability = R.string.role_ability_recluse,
        prepareActionId = R.string.action_prepare_recluse,
        iconRes = R.drawable.icon_recluse,
    )

    val Drunk = Role(
        type = RoleType.OUTSIDER,
        roleName = R.string.role_name_drunk,
        ability = R.string.role_ability_Drunk,
        prepareActionId = R.string.action_prepare_Drunk,
        iconRes = R.drawable.icon_drunk,
    )

    val Lunatic = Role(
        type = RoleType.OUTSIDER,
        roleName = R.string.role_name_lunatic,
        ability = R.string.role_ability_lunatic,
        iconRes = R.drawable.icon_lunatic,
        prepareActionId = R.string.action_prepare_lunatic,
        firstNightActionId = R.string.action_first_night_lunatic,
        secondNightActionId = R.string.action_second_night_lunatic,
        tokens = listOf(LunaticChosen, LunaticChosen, LunaticChosen, LunaticChosen)
    )

    val Mutant = Role(
        type = RoleType.OUTSIDER,
        roleName = R.string.role_name_mutant,
        ability = R.string.role_ability_mutant,
        iconRes = R.drawable.icon_mutant,
    )

    val Sweetheart = Role(
        type = RoleType.OUTSIDER,
        roleName = R.string.role_name_sweetheart,
        ability = R.string.role_ability_sweetheart,
        iconRes = R.drawable.icon_sweetheart,
        dayActionId = R.string.action_day_sweethearth,
        secondNightActionId = R.string.action_day_sweethearth,
        tokens = listOf(SweetheartDrunk),
    )

    val Goon = Role(
        type = RoleType.OUTSIDER,
        roleName = R.string.role_name_goon,
        ability = R.string.role_ability_goon,
        firstNightActionId = R.string.action_second_night_goon,
        secondNightActionId = R.string.action_second_night_goon,
        iconRes = R.drawable.icon_goon
    )

    val Tinker = Role(
        type = RoleType.OUTSIDER,
        roleName = R.string.role_name_tinker,
        ability = R.string.role_ability_tinker,
        iconRes = R.drawable.icon_tinker
    )

    val Moonchild = Role(
        type = RoleType.OUTSIDER,
        tokens = listOf(MoonchildKill),
        roleName = R.string.role_name_moonchild,
        ability = R.string.role_ability_moonchild,
        secondNightActionId = R.string.action_second_night_moonchild,
        dayActionId = R.string.action_day_moonchild,
        iconRes = R.drawable.icon_moonchild
    )

    val Barber = Role(
        type = RoleType.OUTSIDER,
        tokens = listOf(BarberHaircut),
        roleName = R.string.role_name_barber,
        ability = R.string.role_ability_barber,
        secondNightActionId = R.string.action_second_night_barber,
        dayActionId = R.string.action_day_barber,
        iconRes = R.drawable.icon_barber
    )

    val Klutz = Role(
        type = RoleType.OUTSIDER,
        roleName = R.string.role_name_klutz,
        ability = R.string.role_ability_klutz,
        iconRes = R.drawable.icon_klutz
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
    )

    val Spy = Role(
        type = RoleType.MINION,
        roleName = R.string.role_name_spy,
        ability = R.string.role_ability_spy,
        firstNightActionId = R.string.action_second_spy,
        secondNightActionId = R.string.action_second_spy,
        iconRes = R.drawable.icon_spy,
    )

    val Baron = Role(
        type = RoleType.MINION,
        roleName = R.string.role_name_baron,
        ability = R.string.role_ability_baron,
        prepareActionId = R.string.action_prepare_baron,
        iconRes = R.drawable.icon_baron,
    )

    val ScarletWoman = Role(
        type = RoleType.MINION,
        roleName = R.string.role_name_scarlet_woman,
        ability = R.string.role_ability_scarlet_woman,
        secondNightActionId = R.string.action_second_night_scarlet_woman,
        iconRes = R.drawable.icon_scarletwoman,
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
        tokens = listOf(GodfatherDiedToday)
    )

    val Assassin = Role(
        type = RoleType.MINION,
        roleName = R.string.role_name_assassin,
        ability = R.string.role_ability_assassin,
        iconRes = R.drawable.icon_assassin,
        secondNightActionId = R.string.action_second_night_assassin,
        tokens = listOf(AssassinNoAbility)
    )

    val Marionette = Role(
        type = RoleType.MINION,
        roleName = R.string.role_name_marionette,
        ability = R.string.role_ability_marionette,
        prepareActionId = R.string.action_prepare_marionette,
        iconRes = R.drawable.icon_marionette,
    )

    val DevilsAdvocate = Role(
        type = RoleType.MINION,
        tokens = listOf(DevilsAdvocateSurvivesExecution),
        roleName = R.string.role_name_devils_advocate,
        ability = R.string.role_ability_devils_advocate,
        firstNightActionId = R.string.action_second_night_devils_advocate,
        secondNightActionId = R.string.action_second_night_devils_advocate,
        dayActionId = R.string.action_day_devils_advocate,
        iconRes = R.drawable.icon_devilsadvocate
    )

    val Mastermind = Role(
        type = RoleType.MINION,
        roleName = R.string.role_name_mastermind,
        ability = R.string.role_ability_mastermind,
        dayActionId = R.string.action_day_mastermind,
        iconRes = R.drawable.icon_mastermind
    )

    val EvilTwin = Role(
        type = RoleType.MINION,
        tokens = listOf(EvilTwinTwin),
        roleName = R.string.role_name_evil_twin,
        ability = R.string.role_ability_evil_twin,
        prepareActionId = R.string.action_prepare_evil_twin,
        firstNightActionId = R.string.action_first_night_evil_twin,
        iconRes = R.drawable.icon_eviltwin
    )

    val Witch = Role(
        type = RoleType.MINION,
        tokens = listOf(WitchCursed),
        roleName = R.string.role_name_witch,
        ability = R.string.role_ability_witch,
        firstNightActionId = R.string.action_second_night_witch,
        secondNightActionId = R.string.action_second_night_witch,
        iconRes = R.drawable.icon_witch
    )

    val Cerenovus = Role(
        type = RoleType.MINION,
        tokens = listOf(CerenovusMad),
        roleName = R.string.role_name_cerenovus,
        ability = R.string.role_ability_cerenovus,
        firstNightActionId = R.string.action_second_night_cerenovus,
        secondNightActionId = R.string.action_second_night_cerenovus,
        iconRes = R.drawable.icon_cerenovus
    )

    val PitHag = Role(
        type = RoleType.MINION,
        roleName = R.string.role_name_pit_hag,
        ability = R.string.role_ability_pit_hag,
        secondNightActionId = R.string.action_second_night_pit_hag,
        iconRes = R.drawable.icon_pithag
    )

    //Demons
    val Imp = Role(
        type = RoleType.DEMON,
        tokens = listOf(ImpKill),
        roleName = R.string.role_name_imp,
        ability = R.string.role_ability_imp,
        secondNightActionId = R.string.action_second_night_imp,
        iconRes = R.drawable.icon_imp,
    )

    val NoDashii = Role(
        type = RoleType.DEMON,
        tokens = listOf(NoDashiiPoison, NoDashiiPoison, NoDashiiKill),
        roleName = R.string.role_name_nodashii,
        ability = R.string.role_ability_nodashii,
        prepareActionId = R.string.action_prepare_nodashii,
        secondNightActionId = R.string.action_second_night_nodashii,
        iconRes = R.drawable.icon_nodashii,
    )

    val Pukka = Role(
        type = RoleType.DEMON,
        tokens = listOf(PukkaKill, PukkaPoison),
        roleName = R.string.role_name_pukka,
        ability = R.string.role_ability_pukka,
        firstNightActionId = R.string.action_first_night_pukka,
        secondNightActionId = R.string.action_first_night_pukka,
        iconRes = R.drawable.icon_pukka,
    )

    val Zombuul = Role(
        type = RoleType.DEMON,
        tokens = listOf(ZombuulKill, ZombuulDiedToday),
        roleName = R.string.role_name_zombuul,
        ability = R.string.role_ability_zombuul,
        secondNightActionId = R.string.action_second_night_zombuul,
        dayActionId = R.string.action_day_zombuul,
        iconRes = R.drawable.icon_zombuul
    )

    val Shabaloth = Role(
        type = RoleType.DEMON,
        tokens = listOf(ShabalothKill, ShabalothKill, ShabalothAlive),
        roleName = R.string.role_name_shabaloth,
        ability = R.string.role_ability_shabaloth,
        secondNightActionId = R.string.action_second_night_shabaloth,
        iconRes = R.drawable.icon_shabaloth
    )

    val Po = Role(
        type = RoleType.DEMON,
        tokens = listOf(PoKill, PoKill, PoKill, Po3Attacks),
        roleName = R.string.role_name_po,
        ability = R.string.role_ability_po,
        secondNightActionId = R.string.action_second_night_po,
        iconRes = R.drawable.icon_po
    )

    val FangGu = Role(
        type = RoleType.DEMON,
        tokens = listOf(FangGuKill),
        roleName = R.string.role_name_fang_gu,
        ability = R.string.role_ability_fang_gu,
        prepareActionId = R.string.action_prepare_fang_gu,
        secondNightActionId = R.string.action_second_night_fang_gu,
        iconRes = R.drawable.icon_fanggu
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
        iconRes = R.drawable.icon_vigormortis
    )

    val Vortox = Role(
        type = RoleType.DEMON,
        tokens = listOf(VortoxKill),
        roleName = R.string.role_name_vortox,
        ability = R.string.role_ability_vortox,
        prepareActionId = R.string.action_prepare_vortox,
        secondNightActionId = R.string.action_second_night_vortox,
        dayActionId = R.string.action_day_vortox,
        iconRes = R.drawable.icon_vortox
    )

    val Lleech = Role(
        type = RoleType.DEMON,
        roleName = R.string.role_name_lleech,
        ability = R.string.role_ability_lleech,
        tokens = listOf(LleechKill, LleechPoison),
        firstNightActionId = R.string.action_first_night_lleech,
        secondNightActionId = R.string.action_second_night_lleech,
        iconRes = R.drawable.icon_lleech
    )
}
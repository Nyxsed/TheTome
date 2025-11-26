package ru.nyxsed.thetome.core.domain.models


import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.serialization.Serializable
import ru.nyxsed.thetome.R

@Serializable
sealed class Role(
    val type: RoleType,
    val tokens: List<Token> = emptyList(),
    @StringRes val roleName: Int,
    @StringRes val ability: Int,
    @StringRes val prepareActionId: Int = 0,
    @StringRes val firstNightActionId: Int = 0,
    @StringRes val secondNightActionId: Int = 0,
    @StringRes val dayActionId: Int = 0,
    @DrawableRes val iconRes: Int,
) {
    //Townsfolks
    @Serializable
    data object Washerwoman : Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(Token.WasherwomanTownsfolk, Token.WasherwomanWrong),
        roleName = R.string.role_name_washerwoman,
        ability = R.string.role_ability_washerwoman,
        prepareActionId = R.string.action_prepare_washerwoman,
        firstNightActionId = R.string.action_first_night_washerwoman,
        iconRes = R.drawable.icon_washerwoman,
    )

    @Serializable
    data object Librarian : Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(Token.LibrarianOutsider, Token.LibrarianWrong),
        roleName = R.string.role_name_librarian,
        ability = R.string.role_ability_librarian,
        prepareActionId = R.string.action_prepare_librarian,
        firstNightActionId = R.string.action_first_night_librarian,
        iconRes = R.drawable.icon_librarian,
    )

    @Serializable
    data object Investigator : Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(Token.InvestigatorMinion, Token.InvestigatorWrong),
        roleName = R.string.role_name_investigator,
        ability = R.string.role_ability_investigator,
        prepareActionId = R.string.action_prepare_investigator,
        firstNightActionId = R.string.action_first_night_investigator,
        iconRes = R.drawable.icon_investigator,
    )

    @Serializable
    data object Chef : Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_chef,
        ability = R.string.role_ability_chef,
        firstNightActionId = R.string.action_first_night_chef,
        iconRes = R.drawable.icon_chef,
    )

    @Serializable
    data object FortuneTeller : Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(Token.FortuneTellerRedHerring),
        roleName = R.string.role_name_fortune_teller,
        ability = R.string.role_ability_fortune_teller,
        prepareActionId = R.string.action_prepare_fortune_teller,
        firstNightActionId = R.string.action_second_night_fortune_teller,
        secondNightActionId = R.string.action_second_night_fortune_teller,
        iconRes = R.drawable.icon_fortuneteller,
    )

    @Serializable
    data object Empath : Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_empath,
        ability = R.string.role_ability_empath,
        firstNightActionId = R.string.action_second_night_empath,
        secondNightActionId = R.string.action_second_night_empath,
        iconRes = R.drawable.icon_empath,
    )

    @Serializable
    data object Undertaker : Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(Token.UndertakerDiedToday),
        roleName = R.string.role_name_undertaker,
        ability = R.string.role_ability_undertaker,
        secondNightActionId = R.string.action_second_night_undertaker,
        dayActionId = R.string.action_day_undertaker,
        iconRes = R.drawable.icon_undertaker,
    )

    @Serializable
    data object Monk : Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(Token.MonkSafe),
        roleName = R.string.role_name_monk,
        ability = R.string.role_ability_monk,
        secondNightActionId = R.string.action_second_night_monk,
        iconRes = R.drawable.icon_monk,
    )

    @Serializable
    data object Ravenkeeper : Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_ravenkeeper,
        ability = R.string.role_ability_ravenkeeper,
        secondNightActionId = R.string.action_second_night_ravenkeeper,
        iconRes = R.drawable.icon_ravenkeeper,
    )

    @Serializable
    data object Virgin : Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(Token.VirginNoAbility),
        roleName = R.string.role_name_virgin,
        ability = R.string.role_ability_virgin,
        iconRes = R.drawable.icon_virgin,
    )

    @Serializable
    data object Slayer : Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(Token.SlayerNoAbility),
        roleName = R.string.role_name_slayer,
        ability = R.string.role_ability_slayer,
        iconRes = R.drawable.icon_slayer,
    )

    @Serializable
    data object Soldier : Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_soldier,
        ability = R.string.role_ability_soldier,
        iconRes = R.drawable.icon_soldier,
    )

    @Serializable
    data object Mayor : Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_mayor,
        ability = R.string.role_ability_mayor,
        iconRes = R.drawable.icon_mayor,
    )

    @Serializable
    data object Clockmaker : Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_clockmaker,
        ability = R.string.role_ability_clockmaker,
        firstNightActionId = R.string.action_first_night_clockmaker,
        iconRes = R.drawable.icon_clockmaker,
    )

    @Serializable
    data object Grandmother : Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_grandmother,
        ability = R.string.role_ability_grandmother,
        iconRes = R.drawable.icon_grandmother,
        prepareActionId = R.string.action_prepare_grandmother,
        firstNightActionId = R.string.action_first_night_grandmother,
        secondNightActionId = R.string.action_second_night_grandmother,
        tokens = listOf(Token.GrandmotherGrandchild)
    )

    @Serializable
    data object Exorcist : Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_exorcist,
        ability = R.string.role_ability_exorcist,
        iconRes = R.drawable.icon_exorcist,
        secondNightActionId = R.string.action_second_night_exorcist,
        tokens = listOf(Token.ExorcistChosen)
    )

    @Serializable
    data object Flowergirl : Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_flowergirl,
        ability = R.string.role_ability_flowergirl,
        iconRes = R.drawable.icon_flowergirl,
        dayActionId = R.string.action_day_flowergirl,
        secondNightActionId = R.string.action_second_night_flowergirl,
        tokens = listOf(Token.FlowergirlNotVoted, Token.FlowergirlVoted)
    )

    @Serializable
    data object Oracle : Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_oracle,
        ability = R.string.role_ability_oracle,
        iconRes = R.drawable.icon_oracle,
        secondNightActionId = R.string.action_second_night_oracle
    )

    @Serializable
    data object Artist : Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_artist,
        ability = R.string.role_ability_artist,
        iconRes = R.drawable.icon_artist,
        tokens = listOf(Token.ArtistNoAbility)
    )

    @Serializable
    data object Seamstress : Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_seamstress,
        ability = R.string.role_ability_seamstress,
        iconRes = R.drawable.icon_seamstress,
        firstNightActionId = R.string.action_second_night_seamstress,
        secondNightActionId = R.string.action_second_night_seamstress,
        tokens = listOf(Token.SeamstressNoAbility)
    )

    @Serializable
    data object Sailor : Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(Token.SailorDrunk),
        roleName = R.string.role_name_sailor,
        ability = R.string.role_ability_sailor,
        firstNightActionId = R.string.action_second_night_sailor,
        secondNightActionId = R.string.action_second_night_sailor,
        dayActionId = R.string.action_day_sailor,
        iconRes = R.drawable.icon_sailor
    )

    @Serializable
    data object Chambermaid : Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_chambermaid,
        ability = R.string.role_ability_chambermaid,
        firstNightActionId = R.string.action_second_night_chambermaid,
        secondNightActionId = R.string.action_second_night_chambermaid,
        iconRes = R.drawable.icon_chambermaid
    )

    @Serializable
    data object Innkeeper : Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(Token.InnkeeperSafe, Token.InnkeeperSafe, Token.InnkeeperDrunk),
        roleName = R.string.role_name_innkeeper,
        ability = R.string.role_ability_innkeeper,
        secondNightActionId = R.string.action_second_night_innkeeper,
        dayActionId = R.string.action_day_innkeeper,
        iconRes = R.drawable.icon_innkeeper
    )

    @Serializable
    data object Gambler : Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(Token.GamblerKill),
        roleName = R.string.role_name_gambler,
        ability = R.string.role_ability_gambler,
        secondNightActionId = R.string.action_second_night_gambler,
        iconRes = R.drawable.icon_gambler
    )

    @Serializable
    data object Gossip : Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(Token.GossipKill),
        roleName = R.string.role_name_gossip,
        ability = R.string.role_ability_gossip,
        secondNightActionId = R.string.action_second_night_gossip,
        dayActionId = R.string.action_day_gossip,
        iconRes = R.drawable.icon_gossip
    )

    @Serializable
    data object Courtier : Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(Token.CourtierDrunk, Token.CourtierDrunk, Token.CourtierDrunk, Token.CourtierNoAbility),
        roleName = R.string.role_name_courtier,
        ability = R.string.role_ability_courtier,
        firstNightActionId = R.string.action_second_night_courtier,
        secondNightActionId = R.string.action_second_night_courtier,
        iconRes = R.drawable.icon_courtier
    )

    @Serializable
    data object Professor : Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(Token.ProfessorNoAbility),
        roleName = R.string.role_name_professor,
        ability = R.string.role_ability_professor,
        secondNightActionId = R.string.action_second_night_professor,
        iconRes = R.drawable.icon_professor
    )

    @Serializable
    data object Minstrel : Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(Token.MinstrelEveryoneIsDrunk),
        roleName = R.string.role_name_minstrel,
        ability = R.string.role_ability_minstrel,
        dayActionId = R.string.action_day_minstrel,
        iconRes = R.drawable.icon_minstrel
    )

    @Serializable
    data object TeaLady : Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(Token.TeaLadyCannotDie,Token.TeaLadyCannotDie),
        roleName = R.string.role_name_tea_lady,
        ability = R.string.role_ability_tea_lady,
        prepareActionId = R.string.action_prepare_tea_lady,
        dayActionId = R.string.action_day_tea_lady,
        iconRes = R.drawable.icon_tealady
    )

    @Serializable
    data object Pacifist : Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_pacifist,
        ability = R.string.role_ability_pacifist,
        dayActionId = R.string.action_day_pacifist,
        iconRes = R.drawable.icon_pacifist
    )

    @Serializable
    data object Fool : Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(Token.FoolNoAbility),
        roleName = R.string.role_name_fool,
        ability = R.string.role_ability_fool,
        firstNightActionId = R.string.action_first_night_fool,
        secondNightActionId = R.string.action_second_night_fool,
        dayActionId = R.string.action_day_fool,
        iconRes = R.drawable.icon_fool
    )

    @Serializable
    data object Dreamer : Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_dreamer,
        ability = R.string.role_ability_dreamer,
        firstNightActionId = R.string.action_second_night_dreamer,
        secondNightActionId = R.string.action_second_night_dreamer,
        iconRes = R.drawable.icon_dreamer
    )

    @Serializable
    data object SnakeCharmer : Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(Token.SnakeCharmerPoison),
        roleName = R.string.role_name_snake_charmer,
        ability = R.string.role_ability_snake_charmer,
        firstNightActionId = R.string.action_second_night_snake_charmer,
        secondNightActionId = R.string.action_second_night_snake_charmer,
        iconRes = R.drawable.icon_snakecharmer
    )

    @Serializable
    data object Mathematician : Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(Token.MathematicianAbnormal),
        roleName = R.string.role_name_mathematician,
        ability = R.string.role_ability_mathematician,
        firstNightActionId = R.string.action_second_night_mathematician,
        secondNightActionId = R.string.action_second_night_mathematician,
        iconRes = R.drawable.icon_mathematician
    )

    @Serializable
    data object TownCrier : Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(Token.TowncrierMinionNominated),
        roleName = R.string.role_name_town_crier,
        ability = R.string.role_ability_town_crier,
        secondNightActionId = R.string.action_second_night_town_crier,
        dayActionId = R.string.action_day_town_crier,
        iconRes = R.drawable.icon_towncrier
    )

    @Serializable
    data object Savant : Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_savant,
        ability = R.string.role_ability_savant,
        iconRes = R.drawable.icon_savant
    )

    @Serializable
    data object Philosopher : Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_philosopher,
        ability = R.string.role_ability_philosopher,
        tokens = listOf(Token.PhilosopherDrunk, Token.PhilosopherIs),
        firstNightActionId = R.string.action_second_night_philosopher,
        secondNightActionId = R.string.action_second_night_philosopher,
        iconRes = R.drawable.icon_philosopher,
    )


    @Serializable
    data object Juggler : Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(Token.JugglerCorrect,Token.JugglerCorrect,Token.JugglerCorrect,Token.JugglerCorrect,Token.JugglerCorrect,),
        roleName = R.string.role_name_juggler,
        ability = R.string.role_ability_juggler,
        secondNightActionId = R.string.action_second_night_juggler,
        dayActionId = R.string.action_day_juggler,
        iconRes = R.drawable.icon_juggler
    )

    @Serializable
    data object Sage : Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_sage,
        ability = R.string.role_ability_sage,
        secondNightActionId = R.string.action_second_night_sage,
        iconRes = R.drawable.icon_sage
    )


    //Outsiders
    @Serializable
    data object Butler : Role(
        type = RoleType.OUTSIDER,
        tokens = listOf(Token.ButlerMaster),
        roleName = R.string.role_name_butler,
        ability = R.string.role_ability_butler,
        firstNightActionId = R.string.action_second_night_butler,
        secondNightActionId = R.string.action_second_night_butler,
        dayActionId = R.string.action_day_butler,
        iconRes = R.drawable.icon_butler,
    )

    @Serializable
    data object Saint : Role(
        type = RoleType.OUTSIDER,
        roleName = R.string.role_name_saint,
        ability = R.string.role_ability_saint,
        iconRes = R.drawable.icon_saint,
    )

    @Serializable
    data object Recluse : Role(
        type = RoleType.OUTSIDER,
        roleName = R.string.role_name_recluse,
        ability = R.string.role_ability_recluse,
        prepareActionId = R.string.action_prepare_recluse,
        iconRes = R.drawable.icon_recluse,
    )

    @Serializable
    data object Drunk : Role(
        type = RoleType.OUTSIDER,
        roleName = R.string.role_name_drunk,
        ability = R.string.role_ability_Drunk,
        prepareActionId = R.string.action_prepare_Drunk,
        iconRes = R.drawable.icon_drunk,
    )

    @Serializable
    data object Lunatic : Role(
        type = RoleType.OUTSIDER,
        roleName = R.string.role_name_lunatic,
        ability = R.string.role_ability_lunatic,
        iconRes = R.drawable.icon_lunatic,
        prepareActionId = R.string.action_prepare_lunatic,
        firstNightActionId = R.string.action_first_night_lunatic,
        secondNightActionId = R.string.action_second_night_lunatic,
        tokens = listOf(Token.LunaticChosen, Token.LunaticChosen, Token.LunaticChosen, Token.LunaticChosen)
    )

    @Serializable
    data object Mutant : Role(
        type = RoleType.OUTSIDER,
        roleName = R.string.role_name_mutant,
        ability = R.string.role_ability_mutant,
        iconRes = R.drawable.icon_mutant,
    )

    @Serializable
    data object Sweetheart : Role(
        type = RoleType.OUTSIDER,
        roleName = R.string.role_name_sweetheart,
        ability = R.string.role_ability_sweetheart,
        iconRes = R.drawable.icon_sweetheart,
        dayActionId = R.string.action_day_sweethearth,
        secondNightActionId = R.string.action_day_sweethearth,
        tokens = listOf(Token.SweetheartDrunk),
    )

    @Serializable
    data object Goon : Role(
        type = RoleType.OUTSIDER,
        roleName = R.string.role_name_goon,
        ability = R.string.role_ability_goon,
        firstNightActionId = R.string.action_second_night_goon,
        secondNightActionId = R.string.action_second_night_goon,
        iconRes = R.drawable.icon_goon
    )

    @Serializable
    data object Tinker : Role(
        type = RoleType.OUTSIDER,
        roleName = R.string.role_name_tinker,
        ability = R.string.role_ability_tinker,
        iconRes = R.drawable.icon_tinker
    )

    @Serializable
    data object Moonchild : Role(
        type = RoleType.OUTSIDER,
        tokens = listOf(Token.MoonchildKill),
        roleName = R.string.role_name_moonchild,
        ability = R.string.role_ability_moonchild,
        secondNightActionId = R.string.action_second_night_moonchild,
        dayActionId = R.string.action_day_moonchild,
        iconRes = R.drawable.icon_moonchild
    )

    @Serializable
    data object Barber : Role(
        type = RoleType.OUTSIDER,
        tokens = listOf(Token.BarberHaircut),
        roleName = R.string.role_name_barber,
        ability = R.string.role_ability_barber,
        secondNightActionId = R.string.action_second_night_barber,
        dayActionId = R.string.action_day_barber,
        iconRes = R.drawable.icon_barber
    )

    @Serializable
    data object Klutz : Role(
        type = RoleType.OUTSIDER,
        roleName = R.string.role_name_klutz,
        ability = R.string.role_ability_klutz,
        iconRes = R.drawable.icon_klutz
    )

    //Minions
    @Serializable
    data object Poisoner : Role(
        type = RoleType.MINION,
        tokens = listOf(Token.PoisonerPoison),
        roleName = R.string.role_name_poisoner,
        ability = R.string.role_ability_poisoner,
        firstNightActionId = R.string.action_second_night_poisoner,
        secondNightActionId = R.string.action_second_night_poisoner,
        iconRes = R.drawable.icon_poisoner,
    )

    @Serializable
    data object Spy : Role(
        type = RoleType.MINION,
        roleName = R.string.role_name_spy,
        ability = R.string.role_ability_spy,
        firstNightActionId = R.string.action_second_spy,
        secondNightActionId = R.string.action_second_spy,
        iconRes = R.drawable.icon_spy,
    )

    @Serializable
    data object Baron : Role(
        type = RoleType.MINION,
        roleName = R.string.role_name_baron,
        ability = R.string.role_ability_baron,
        prepareActionId = R.string.action_prepare_baron,
        iconRes = R.drawable.icon_baron,
    )

    @Serializable
    data object ScarletWoman : Role(
        type = RoleType.MINION,
        roleName = R.string.role_name_scarlet_woman,
        ability = R.string.role_ability_scarlet_woman,
        secondNightActionId = R.string.action_second_night_scarlet_woman,
        iconRes = R.drawable.icon_scarletwoman,
    )

    @Serializable
    data object Godfather : Role(
        type = RoleType.MINION,
        roleName = R.string.role_name_godfather,
        ability = R.string.role_ability_godfather,
        iconRes = R.drawable.icon_godfather,
        prepareActionId = R.string.action_prepare_godfather,
        firstNightActionId = R.string.action_first_night_godfather,
        secondNightActionId = R.string.action_second_night_godfather,
        dayActionId = R.string.action_day_godfather,
        tokens = listOf(Token.GodfatherDiedToday)
    )

    @Serializable
    data object Assassin : Role(
        type = RoleType.MINION,
        roleName = R.string.role_name_assassin,
        ability = R.string.role_ability_assassin,
        iconRes = R.drawable.icon_assassin,
        secondNightActionId = R.string.action_second_night_assassin,
        tokens = listOf(Token.AssassinNoAbility)
    )

    @Serializable
    data object  Marionette: Role(
        type = RoleType.MINION,
        roleName = R.string.role_name_marionette,
        ability = R.string.role_ability_marionette,
        prepareActionId = R.string.action_prepare_marionette,
        iconRes = R.drawable.icon_marionette,
    )

    @Serializable
    data object DevilsAdvocate : Role(
        type = RoleType.MINION,
        tokens = listOf(Token.DevilsAdvocateSurvivesExecution),
        roleName = R.string.role_name_devils_advocate,
        ability = R.string.role_ability_devils_advocate,
        firstNightActionId = R.string.action_second_night_devils_advocate,
        secondNightActionId = R.string.action_second_night_devils_advocate,
        dayActionId = R.string.action_day_devils_advocate,
        iconRes = R.drawable.icon_devilsadvocate
    )

    @Serializable
    data object Mastermind : Role(
        type = RoleType.MINION,
        roleName = R.string.role_name_mastermind,
        ability = R.string.role_ability_mastermind,
        dayActionId = R.string.action_day_mastermind,
        iconRes = R.drawable.icon_mastermind
    )

    @Serializable
    data object EvilTwin : Role(
        type = RoleType.MINION,
        tokens = listOf(Token.EvilTwinTwin),
        roleName = R.string.role_name_evil_twin,
        ability = R.string.role_ability_evil_twin,
        prepareActionId = R.string.action_prepare_evil_twin,
        firstNightActionId = R.string.action_first_night_evil_twin,
        iconRes = R.drawable.icon_eviltwin
    )

    @Serializable
    data object Witch : Role(
        type = RoleType.MINION,
        tokens = listOf(Token.WitchCursed),
        roleName = R.string.role_name_witch,
        ability = R.string.role_ability_witch,
        firstNightActionId = R.string.action_second_night_witch,
        secondNightActionId = R.string.action_second_night_witch,
        iconRes = R.drawable.icon_witch
    )

    @Serializable
    data object Cerenovus : Role(
        type = RoleType.MINION,
        tokens = listOf(Token.CerenovusMad),
        roleName = R.string.role_name_cerenovus,
        ability = R.string.role_ability_cerenovus,
        firstNightActionId = R.string.action_second_night_cerenovus,
        secondNightActionId = R.string.action_second_night_cerenovus,
        iconRes = R.drawable.icon_cerenovus
    )

    @Serializable
    data object PitHag : Role(
        type = RoleType.MINION,
        roleName = R.string.role_name_pit_hag,
        ability = R.string.role_ability_pit_hag,
        secondNightActionId = R.string.action_second_night_pit_hag,
        iconRes = R.drawable.icon_pithag
    )

    //Demons
    @Serializable
    data object Imp : Role(
        type = RoleType.DEMON,
        tokens = listOf(Token.ImpKill),
        roleName = R.string.role_name_imp,
        ability = R.string.role_ability_imp,
        secondNightActionId = R.string.action_second_night_imp,
        iconRes = R.drawable.icon_imp,
    )

    @Serializable
    data object NoDashii : Role(
        type = RoleType.DEMON,
        tokens = listOf(Token.NoDashiiPoison, Token.NoDashiiPoison, Token.NoDashiiKill),
        roleName = R.string.role_name_nodashii,
        ability = R.string.role_ability_nodashii,
        prepareActionId = R.string.action_prepare_nodashii,
        secondNightActionId = R.string.action_second_night_nodashii,
        iconRes = R.drawable.icon_nodashii,
    )

    @Serializable
    data object Pukka : Role(
        type = RoleType.DEMON,
        tokens = listOf(Token.PukkaKill, Token.PukkaPoison),
        roleName = R.string.role_name_pukka,
        ability = R.string.role_ability_pukka,
        firstNightActionId = R.string.action_first_night_pukka,
        secondNightActionId = R.string.action_first_night_pukka,
        iconRes = R.drawable.icon_pukka,
    )

    @Serializable
    data object Zombuul : Role(
        type = RoleType.DEMON,
        tokens = listOf(Token.ZombuulKill, Token.ZombuulDiedToday),
        roleName = R.string.role_name_zombuul,
        ability = R.string.role_ability_zombuul,
        secondNightActionId = R.string.action_second_night_zombuul,
        dayActionId = R.string.action_day_zombuul,
        iconRes = R.drawable.icon_zombuul
    )

    @Serializable
    data object Shabaloth : Role(
        type = RoleType.DEMON,
        tokens = listOf(Token.ShabalothKill, Token.ShabalothKill, Token.ShabalothAlive),
        roleName = R.string.role_name_shabaloth,
        ability = R.string.role_ability_shabaloth,
        secondNightActionId = R.string.action_second_night_shabaloth,
        iconRes = R.drawable.icon_shabaloth
    )

    @Serializable
    data object Po : Role(
        type = RoleType.DEMON,
        tokens = listOf(Token.PoKill,Token.PoKill,Token.PoKill,Token.Po3Attacks),
        roleName = R.string.role_name_po,
        ability = R.string.role_ability_po,
        secondNightActionId = R.string.action_second_night_po,
        iconRes = R.drawable.icon_po
    )

    @Serializable
    data object FangGu : Role(
        type = RoleType.DEMON,
        tokens = listOf(Token.FangGuKill),
        roleName = R.string.role_name_fang_gu,
        ability = R.string.role_ability_fang_gu,
        prepareActionId = R.string.action_prepare_fang_gu,
        secondNightActionId = R.string.action_second_night_fang_gu,
        iconRes = R.drawable.icon_fanggu
    )

    @Serializable
    data object Vigormortis : Role(
        type = RoleType.DEMON,
        tokens = listOf(Token.VigormortisHasAbility,Token.VigormortisHasAbility,Token.VigormortisHasAbility,Token.VigormortisKill, Token.VigormortisPoison,Token.VigormortisPoison,Token.VigormortisPoison,),
        roleName = R.string.role_name_vigormortis,
        ability = R.string.role_ability_vigormortis,
        prepareActionId = R.string.action_prepare_vigormortis,
        secondNightActionId = R.string.action_second_night_vigormortis,
        iconRes = R.drawable.icon_vigormortis
    )


    @Serializable
    data object Vortox : Role(
        type = RoleType.DEMON,
        tokens = listOf(Token.VortoxKill),
        roleName = R.string.role_name_vortox,
        ability = R.string.role_ability_vortox,
        prepareActionId = R.string.action_prepare_vortox,
        secondNightActionId = R.string.action_second_night_vortox,
        dayActionId = R.string.action_day_vortox,
        iconRes = R.drawable.icon_vortox
    )
}
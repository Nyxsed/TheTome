package ru.nyxsed.thetome.core.domain.models


import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.serialization.Serializable
import ru.nyxsed.thetome.R

@Serializable
sealed class Role(
    val type: RoleType,
    val tokens: List<Token> = emptyList(),
    @StringRes val roleName: Int = 0,
    @StringRes val ability: Int = 0,
    @StringRes val prepareActionId: Int = 0,
    @StringRes val firstNightAction: Int = 0,
    @StringRes val secondNightAction: Int = 0,
    @StringRes val dayAction: Int = 0,
    @DrawableRes val iconRes: Int = 0,
) {
    //Townsfolks
    @Serializable
    data object Washerwoman : Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(Token.WasherwomanTownsfolk, Token.WasherwomanWrong),
        roleName = R.string.role_name_washerwoman,
        ability = R.string.role_ability_washerwoman,
        prepareActionId = R.string.action_prepare_washerwoman,
        firstNightAction = R.string.action_first_night_washerwoman,
        iconRes = R.drawable.icon_washerwoman,
    )

    @Serializable
    data object Librarian : Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(Token.LibrarianOutsider, Token.LibrarianWrong),
        roleName = R.string.role_name_librarian,
        ability = R.string.role_ability_librarian,
        prepareActionId = R.string.action_prepare_librarian,
        firstNightAction = R.string.action_first_night_librarian,
        iconRes = R.drawable.icon_librarian,
    )

    @Serializable
    data object Investigator : Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(Token.InvestigatorMinion, Token.InvestigatorWrong),
        roleName = R.string.role_name_investigator,
        ability = R.string.role_ability_investigator,
        prepareActionId = R.string.action_prepare_investigator,
        firstNightAction = R.string.action_first_night_investigator,
        iconRes = R.drawable.icon_investigator,
    )

    @Serializable
    data object Chef : Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_chef,
        ability = R.string.role_ability_chef,
        firstNightAction = R.string.action_first_night_chef,
        iconRes = R.drawable.icon_chef,
    )

    @Serializable
    data object FortuneTeller : Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(Token.FortuneTellerRedHerring),
        roleName = R.string.role_name_fortune_teller,
        ability = R.string.role_ability_fortune_teller,
        firstNightAction = R.string.action_second_night_fortune_teller,
        secondNightAction = R.string.action_second_night_fortune_teller,
        iconRes = R.drawable.icon_fortuneteller,
    )

    @Serializable
    data object Empath : Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_empath,
        ability = R.string.role_ability_empath,
        firstNightAction = R.string.action_second_night_empath,
        secondNightAction = R.string.action_second_night_empath,
        iconRes = R.drawable.icon_empath,
    )

    @Serializable
    data object Undertaker : Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(Token.UndertakerDiedToday),
        roleName = R.string.role_name_undertaker,
        ability = R.string.role_ability_undertaker,
        secondNightAction = R.string.action_second_night_undertaker,
        dayAction = R.string.action_day_undertaker,
        iconRes = R.drawable.icon_undertaker,
    )

    @Serializable
    data object Monk : Role(
        type = RoleType.TOWNSFOLK,
        tokens = listOf(Token.MonkSafe),
        roleName = R.string.role_name_monk,
        ability = R.string.role_ability_monk,
        secondNightAction = R.string.action_second_night_monk,
        iconRes = R.drawable.icon_monk,
    )

    @Serializable
    data object Ravenkeeper : Role(
        type = RoleType.TOWNSFOLK,
        roleName = R.string.role_name_ravenkeeper,
        ability = R.string.role_ability_ravenkeeper,
        secondNightAction = R.string.action_second_night_ravenkeeper,
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

    //Outsiders
    @Serializable
    data object Butler : Role(
        type = RoleType.OUTSIDER,
        tokens = listOf(Token.ButlerMaster),
        roleName = R.string.role_name_butler,
        ability = R.string.role_ability_butler,
        firstNightAction = R.string.action_second_night_butler,
        secondNightAction = R.string.action_second_night_butler,
        dayAction = R.string.action_day_butler,
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
        roleName = R.string.role_name_Drunk,
        ability = R.string.role_ability_Drunk,
        prepareActionId = R.string.action_prepare_Drunk,
        iconRes = R.drawable.icon_drunk,
    )

    //Minions
    @Serializable
    data object Poisoner : Role(
        type = RoleType.MINION,
        tokens = listOf(Token.PoisonerPoison),
        roleName = R.string.role_name_poisoner,
        ability = R.string.role_ability_poisoner,
        firstNightAction = R.string.action_second_night_poisoner,
        secondNightAction = R.string.action_second_night_poisoner,
        iconRes = R.drawable.icon_poisoner,
    )

    @Serializable
    data object Spy : Role(
        type = RoleType.MINION,
        roleName = R.string.role_name_spy,
        ability = R.string.role_ability_spy,
        firstNightAction = R.string.action_second_spy,
        secondNightAction = R.string.action_second_spy,
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
        secondNightAction = R.string.action_second_night_scarlet_woman,
        iconRes = R.drawable.icon_scarletwoman,
    )

    //Demons
    @Serializable
    data object Imp : Role(
        type = RoleType.DEMON,
        tokens = listOf(Token.ImpKill),
        roleName = R.string.role_name_imp,
        ability = R.string.role_ability_imp,
        secondNightAction = R.string.action_second_night_imp,
        iconRes = R.drawable.icon_imp,
    )
}
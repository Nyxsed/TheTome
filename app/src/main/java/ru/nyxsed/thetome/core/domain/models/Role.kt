package ru.nyxsed.thetome.core.domain.models


import androidx.annotation.StringRes
import kotlinx.serialization.Serializable
import ru.nyxsed.thetome.R

@Serializable
sealed class Role(
    val roleId: RoleId,
    val type: RoleType,
    val tokens: List<Token> = emptyList(),
    @StringRes val roleName: Int = 0,
    @StringRes val ability: Int = 0,
    @StringRes val prepareActionId: Int = 0,
    @StringRes val secondNightAction: Int = 0,
) {
    //Townsfolks
    @Serializable
    data object Washerwoman : Role(
        roleId = RoleId.WASHERWOMAN,
        type = RoleType.TOWNSFOLK,
        tokens = listOf(Token.WasherwomanTownsfolk, Token.WasherwomanWrong),
        roleName = R.string.role_name_washerwoman,
        ability = R.string.role_ability_washerwoman,
        prepareActionId = R.string.action_prepare_washerwoman,
    )

    @Serializable
    data object Librarian : Role(
        roleId = RoleId.LIBRARIAN,
        type = RoleType.TOWNSFOLK,
        tokens = listOf(Token.LibrarianOutsider, Token.LibrarianWrong)
    )

    @Serializable
    data object Investigator : Role(
        roleId = RoleId.INVESTIGATOR,
        type = RoleType.TOWNSFOLK
    )

    @Serializable
    data object Chef : Role(
        roleId = RoleId.CHEF,
        type = RoleType.TOWNSFOLK
    )

    @Serializable
    data object FortuneTeller : Role(
        roleId = RoleId.FORTUNE_TELLER,
        type = RoleType.TOWNSFOLK
    )

    @Serializable
    data object Empath : Role(
        roleId = RoleId.EMPATH,
        type = RoleType.TOWNSFOLK
    )

    @Serializable
    data object Undertaker : Role(
        roleId = RoleId.UNDERTAKER,
        type = RoleType.TOWNSFOLK
    )

    @Serializable
    data object Monk : Role(
        roleId = RoleId.MONK,
        type = RoleType.TOWNSFOLK
    )

    @Serializable
    data object Ravenkeeper : Role(
        roleId = RoleId.RAVENKEEPER,
        type = RoleType.TOWNSFOLK
    )

    @Serializable
    data object Virgin : Role(
        roleId = RoleId.VIRGIN,
        type = RoleType.TOWNSFOLK
    )

    @Serializable
    data object Slayer : Role(
        roleId = RoleId.SLAYER,
        type = RoleType.TOWNSFOLK
    )

    @Serializable
    data object Soldier : Role(
        roleId = RoleId.SOLDIER,
        type = RoleType.TOWNSFOLK
    )

    @Serializable
    data object Mayor : Role(
        roleId = RoleId.MAYOR,
        type = RoleType.TOWNSFOLK
    )

    //Outsiders
    @Serializable
    data object Butler : Role(
        roleId = RoleId.BUTLER,
        type = RoleType.OUTSIDER
    )

    @Serializable
    data object Saint : Role(
        roleId = RoleId.SAINT,
        type = RoleType.OUTSIDER
    )

    @Serializable
    data object Recluse : Role(
        roleId = RoleId.RECLUSE,
        type = RoleType.OUTSIDER
    )

    @Serializable
    data object Drunk : Role(
        roleId = RoleId.DRUNK,
        type = RoleType.OUTSIDER
    )

    //Minions
    @Serializable
    data object Poisoner : Role(
        roleId = RoleId.POISONER,
        type = RoleType.MINION,
        roleName = R.string.role_name_poisoner,
        ability = R.string.role_ability_poisoner,
        secondNightAction = R.string.action_second_night_poisoner,
    )

    @Serializable
    data object Spy : Role(
        roleId = RoleId.SPY,
        type = RoleType.MINION
    )

    @Serializable
    data object Baron : Role(
        roleId = RoleId.BARON,
        type = RoleType.MINION
    )

    @Serializable
    data object ScarletWoman : Role(
        roleId = RoleId.SCARLET_WOMAN,
        type = RoleType.MINION
    )

    //Demons
    @Serializable
    data object Imp : Role(
        roleId = RoleId.IMP,
        type = RoleType.DEMON,
        roleName = R.string.role_name_imp,
        ability = R.string.role_ability_imp,
        secondNightAction = R.string.action_second_night_imp,
    )
}
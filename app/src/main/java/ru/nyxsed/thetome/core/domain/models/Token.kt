package ru.nyxsed.thetome.core.domain.models

import kotlinx.serialization.Serializable
import ru.nyxsed.thetome.R

@Serializable
sealed class Token(
    val nameResId: Int,
) {
    @Serializable
    data object Good : Token(R.string.token_good)
    @Serializable
    data object Evil : Token(R.string.token_evil)

    @Serializable
    data object WasherwomanTownsfolk : Token(R.string.token_townsfolk)
    @Serializable
    data object WasherwomanWrong : Token(R.string.token_wrong)

    @Serializable
    data object LibrarianOutsider : Token(R.string.token_outsider)
    @Serializable
    data object LibrarianWrong : Token(R.string.token_wrong)

    @Serializable
    data object PoisonerPoison : Token(R.string.token_poison)

    @Serializable
    data object ImpKill : Token(R.string.token_kill)

    @Serializable
    data object InvestigatorWrong : Token(R.string.token_wrong)
    @Serializable
    data object InvestigatorMinion : Token(R.string.token_minion)

    @Serializable
    data object FortuneTellerRedHerring : Token(R.string.token_red_herring)

    @Serializable
    data object ButlerMaster : Token(R.string.token_master)

    @Serializable
    data object MonkSafe : Token(R.string.token_safe)

    @Serializable
    data object UndertakerDiedToday : Token(R.string.token_died_today)

    @Serializable
    data object SlayerNoAbility : Token(R.string.token_no_ability)

    @Serializable
    data object VirginNoAbility : Token(R.string.token_no_ability)

    @Serializable
    data object DrunkDrunk : Token(R.string.token_drunk)

}
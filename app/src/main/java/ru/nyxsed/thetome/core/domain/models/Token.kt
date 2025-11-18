package ru.nyxsed.thetome.core.domain.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.serialization.Serializable
import ru.nyxsed.thetome.R

@Serializable
sealed class Token(
    @StringRes val nameResId: Int,
    @DrawableRes val iconRes: Int = 0,
) {
    @Serializable
    data object Good : Token(nameResId = R.string.token_good, iconRes = R.drawable.icon_good)
    @Serializable
    data object Evil : Token(nameResId = R.string.token_evil, iconRes = R.drawable.icon_evil)

    @Serializable
    data object WasherwomanTownsfolk : Token(nameResId = R.string.token_townsfolk, iconRes = R.drawable.icon_washerwoman)
    @Serializable
    data object WasherwomanWrong : Token(nameResId = R.string.token_wrong, iconRes = R.drawable.icon_washerwoman)

    @Serializable
    data object LibrarianOutsider : Token(nameResId = R.string.token_outsider, iconRes = R.drawable.icon_librarian)
    @Serializable
    data object LibrarianWrong : Token(nameResId = R.string.token_wrong, iconRes = R.drawable.icon_librarian)

    @Serializable
    data object PoisonerPoison : Token(nameResId = R.string.token_poison, iconRes = R.drawable.icon_poisoner)

    @Serializable
    data object ImpKill : Token(nameResId = R.string.token_kill, iconRes = R.drawable.icon_imp)

    @Serializable
    data object InvestigatorWrong : Token(nameResId = R.string.token_wrong, iconRes = R.drawable.icon_investigator)
    @Serializable
    data object InvestigatorMinion : Token(nameResId = R.string.token_minion, iconRes = R.drawable.icon_investigator)

    @Serializable
    data object FortuneTellerRedHerring : Token(nameResId = R.string.token_red_herring, iconRes = R.drawable.icon_fortuneteller)

    @Serializable
    data object ButlerMaster : Token(nameResId = R.string.token_master, iconRes = R.drawable.icon_butler)

    @Serializable
    data object MonkSafe : Token(nameResId = R.string.token_safe, iconRes = R.drawable.icon_monk)

    @Serializable
    data object UndertakerDiedToday : Token(nameResId = R.string.token_died_today, iconRes = R.drawable.icon_undertaker)

    @Serializable
    data object SlayerNoAbility : Token(nameResId = R.string.token_no_ability, iconRes = R.drawable.icon_slayer)

    @Serializable
    data object VirginNoAbility : Token(nameResId = R.string.token_no_ability, iconRes = R.drawable.icon_virgin)

    @Serializable
    data object DrunkDrunk : Token(nameResId = R.string.token_drunk, iconRes = R.drawable.icon_drunk)

}
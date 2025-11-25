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

    @Serializable
    data object GrandmotherGrandchild : Token(nameResId = R.string.token_grandchild, iconRes = R.drawable.icon_grandmother)

    @Serializable
    data object ExorcistChosen : Token(nameResId = R.string.token_chosen, iconRes = R.drawable.icon_exorcist)

    @Serializable
    data object FlowergirlNotVoted : Token(nameResId = R.string.token_not_voted, iconRes = R.drawable.icon_flowergirl)

    @Serializable
    data object FlowergirlVoted : Token(nameResId = R.string.token_voted, iconRes = R.drawable.icon_flowergirl)

    @Serializable
    data object ArtistNoAbility : Token(nameResId = R.string.token_no_ability, iconRes = R.drawable.icon_artist)

    @Serializable
    data object SeamstressNoAbility : Token(nameResId = R.string.token_no_ability, iconRes = R.drawable.icon_seamstress)

    @Serializable
    data object LunaticChosen : Token(nameResId = R.string.token_chosen, iconRes = R.drawable.icon_lunatic)

    @Serializable
    data object SweetheartDrunk : Token(nameResId = R.string.token_drunk, iconRes = R.drawable.icon_sweetheart)

    @Serializable
    data object GodfatherDiedToday : Token(nameResId = R.string.token_died_today, iconRes = R.drawable.icon_godfather)

    @Serializable
    data object AssassinNoAbility : Token(nameResId = R.string.token_no_ability, iconRes = R.drawable.icon_assassin)

    @Serializable
    data object MarionetteMarionette : Token(nameResId = R.string.token_marionette, iconRes = R.drawable.icon_marionette) // TODO unique token

    @Serializable
    data object NoDashiiPoison : Token(nameResId = R.string.token_poison, iconRes = R.drawable.icon_nodashii)

    @Serializable
    data object NoDashiiKill : Token(nameResId = R.string.token_kill, iconRes = R.drawable.icon_nodashii)

    @Serializable
    data object PukkaPoison : Token(nameResId = R.string.token_poison, iconRes = R.drawable.icon_pukka)

    @Serializable
    data object PukkaKill : Token(nameResId = R.string.token_kill, iconRes = R.drawable.icon_pukka)

    @Serializable
    data object SailorDrunk : Token(nameResId = R.string.token_drunk, iconRes = R.drawable.icon_sailor)

    @Serializable
    data object InnkeeperDrunk : Token(nameResId = R.string.token_drunk, iconRes = R.drawable.icon_innkeeper)

    @Serializable
    data object InnkeeperSafe : Token(nameResId = R.string.token_safe, iconRes = R.drawable.icon_innkeeper)

    @Serializable
    data object GamblerKill : Token(nameResId = R.string.token_kill, iconRes = R.drawable.icon_gambler)

    @Serializable
    data object GossipKill : Token(nameResId = R.string.token_kill, iconRes = R.drawable.icon_gossip)

    @Serializable
    data object CourtierDrunk : Token(nameResId = R.string.token_drunk, iconRes = R.drawable.icon_courtier)

    @Serializable
    data object CourtierNoAbility : Token(nameResId = R.string.token_no_ability, iconRes = R.drawable.icon_gossip)

    @Serializable
    data object ProfessorNoAbility : Token(nameResId = R.string.token_no_ability, iconRes = R.drawable.icon_professor)

    @Serializable
    data object MinstrelEveryoneIsDrunk : Token(nameResId = R.string.token_everyone_is_drunk, iconRes = R.drawable.icon_minstrel)

    @Serializable
    data object TeaLadyCannotDie : Token(nameResId = R.string.token_cannot_die, iconRes = R.drawable.icon_tealady)

    @Serializable
    data object FoolNoAbility : Token(nameResId = R.string.token_no_ability, iconRes = R.drawable.icon_fool)

    @Serializable
    data object MoonchildKill : Token(nameResId = R.string.token_kill, iconRes = R.drawable.icon_moonchild)

    @Serializable
    data object DevilsAdvocateSurvivesExecution : Token(nameResId = R.string.token_survives_execution, iconRes = R.drawable.icon_devilsadvocate)

    @Serializable
    data object ZombuulKill : Token(nameResId = R.string.token_kill, iconRes = R.drawable.icon_zombuul)

    @Serializable
    data object ZombuulDiedToday : Token(nameResId = R.string.token_died_today, iconRes = R.drawable.icon_zombuul)

    @Serializable
    data object ShabalothKill : Token(nameResId = R.string.token_kill, iconRes = R.drawable.icon_shabaloth)

    @Serializable
    data object ShabalothAlive : Token(nameResId = R.string.token_alive, iconRes = R.drawable.icon_shabaloth)

    @Serializable
    data object PoKill : Token(nameResId = R.string.token_kill, iconRes = R.drawable.icon_po)

    @Serializable
    data object Po3Attacks : Token(nameResId = R.string.token_kill, iconRes = R.drawable.icon_po)
}
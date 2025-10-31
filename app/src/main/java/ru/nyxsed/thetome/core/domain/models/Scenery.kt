package ru.nyxsed.thetome.core.domain.models

import kotlinx.serialization.Serializable

@Serializable
sealed class Scenery(
    val sceneryId: SceneryId,
    val roles: List<Role>,
    val firstNightOrder: List<Role>,
    val secondNightOrder: List<Role>,
) {
    @Serializable
    data object TroubleBrewing : Scenery(
        sceneryId = SceneryId.TROUBLE_BREWING,
        roles = listOf(
            Role.Washerwoman,
            Role.Librarian,
            Role.Investigator,
            Role.Chef,
            Role.Empath,
            Role.FortuneTeller,
            Role.Undertaker,
            Role.Monk,
            Role.Ravenkeeper,
            Role.Virgin,
            Role.Slayer,
            Role.Soldier,
            Role.Mayor,
            Role.Butler,
            Role.Saint,
            Role.Recluse,
            Role.Drunk,
            Role.Poisoner,
            Role.Spy,
            Role.Baron,
            Role.ScarletWoman,
            Role.Imp,
        ),
        firstNightOrder = listOf(
            Role.Poisoner,
            Role.Spy,
            Role.Washerwoman,
            Role.Librarian,
            Role.Investigator,
            Role.Chef,
            Role.Empath,
            Role.FortuneTeller,
            Role.Butler,
        ),
        secondNightOrder = listOf(
            Role.Poisoner,
            Role.Monk,
            Role.Spy,
            Role.ScarletWoman,
            Role.Imp,
            Role.Ravenkeeper,
            Role.Undertaker,
            Role.Empath,
            Role.FortuneTeller,
            Role.Butler,
        )
    )

    companion object {
        val all: List<Scenery> = listOf(
            TroubleBrewing,
        )
    }
}
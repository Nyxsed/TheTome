package ru.nyxsed.thetome.core.domain.models

import androidx.annotation.DrawableRes
import kotlinx.serialization.Serializable

@Serializable
data class Scenery(
    val sceneryNameRes: Int,
    @DrawableRes val iconRes: Int = 0,
    val maxPlayers: Int = 15,
    val roles: List<Role>
)
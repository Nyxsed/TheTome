package ru.nyxsed.thetome.core.domain.models

import androidx.annotation.DrawableRes
import kotlinx.serialization.Serializable

@Serializable
data class Scenery(
    val sceneryNameRes: Int,
    @DrawableRes val iconRes: Int = 0,
    val roles: List<Role>,
    val prepareActions: List<Action>,
    val firstNightActions: List<Action>,
    val secondNightActions: List<Action>,
    val dayActions: List<Action>,
    val sceneryTokens: List<Token>,
)
package ru.nyxsed.thetome.core.domain.models

import androidx.annotation.StringRes
import kotlinx.serialization.Serializable

@Serializable
data class Action(
    val type: ActionType,
    val role: Role?,
    @StringRes val res: Int
)

enum class ActionType {
    PLAYER, PLAYERS_7, DAY, NIGHT
}
package ru.nyxsed.thetome.core.domain.models

import androidx.annotation.StringRes
import kotlinx.serialization.Serializable

@Serializable
data class Action(
    val actionType:ActionType,
    val role: Role?,
    @StringRes val actionResId: Int
)

enum class ActionType {
    PLAYER, PLAYERS_7, DAY, NIGHT
}
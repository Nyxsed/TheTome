package ru.nyxsed.thetome.core.domain.models


import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.serialization.Serializable

@Serializable
data class Role(
    val type: RoleType,
    val tokens: List<Token> = emptyList(),
    @StringRes val roleName: Int,
    @StringRes val ability: Int,
    @StringRes val prepareActionId: Int = 0,
    @StringRes val firstNightActionId: Int = 0,
    @StringRes val secondNightActionId: Int = 0,
    @StringRes val dayActionId: Int = 0,
    @DrawableRes val iconRes: Int,
)

enum class RoleType {
    TOWNSFOLK,
    OUTSIDER,
    MINION,
    DEMON
}
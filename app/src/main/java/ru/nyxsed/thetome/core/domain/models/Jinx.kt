package ru.nyxsed.thetome.core.domain.models

import androidx.annotation.StringRes
import kotlinx.serialization.Serializable

@Serializable
data class Jinx(
    val roles: List<Role>,
    @StringRes val descriptionResId: Int
)

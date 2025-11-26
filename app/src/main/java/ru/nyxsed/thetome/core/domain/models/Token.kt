package ru.nyxsed.thetome.core.domain.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.serialization.Serializable
import ru.nyxsed.thetome.R

@Serializable
data class Token(
    @StringRes val nameResId: Int,
    @DrawableRes val iconRes: Int = 0,
)
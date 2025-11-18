package ru.nyxsed.thetome.core.domain.models

import androidx.annotation.StringRes
import ru.nyxsed.thetome.R

enum class RoleType(@StringRes val resId: Int) {
    TOWNSFOLK(R.string.text_townsfolk),
    OUTSIDER(R.string.text_outsider),
    MINION(R.string.text_minion),
    DEMON(R.string.text_demon)
}
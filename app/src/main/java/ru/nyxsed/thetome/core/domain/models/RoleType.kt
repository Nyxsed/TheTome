package ru.nyxsed.thetome.core.domain.models

import ru.nyxsed.thetome.R

enum class RoleType(val resId: Int) {
    TOWNSFOLK(R.string.text_townsfolk),
    OUTSIDER(R.string.text_outsider),
    MINION(R.string.text_minion),
    DEMON(R.string.text_demon)
}
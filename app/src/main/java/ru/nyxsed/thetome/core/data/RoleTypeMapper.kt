package ru.nyxsed.thetome.core.data

import ru.nyxsed.thetome.R
import ru.nyxsed.thetome.core.domain.models.RoleType

object RoleTypeMapper {

    fun RoleType.getDisplayNameId(): Int = when(this){
        RoleType.TOWNSFOLK -> R.string.text_townsfolk
        RoleType.OUTSIDER -> R.string.text_outsider
        RoleType.MINION -> R.string.text_minion
        RoleType.DEMON -> R.string.text_demon
        RoleType.TRAVELLER -> R.string.text_demon
        RoleType.FABLED -> R.string.text_fabled
    }
}
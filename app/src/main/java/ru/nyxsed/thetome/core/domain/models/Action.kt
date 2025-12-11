package ru.nyxsed.thetome.core.domain.models

import androidx.annotation.StringRes
import kotlinx.serialization.Serializable
import ru.nyxsed.thetome.R

@Serializable
data class Action(
    val role: Role?,
    @StringRes val res: Int
) {
    companion object {
        val showDemonId: Int = R.string.action_show_demon
        val showMinionsId: Int = R.string.action_show_minions_and_bluffs
        val prepareBluffsId: Int = R.string.action_prepare_3_demon_bluffs
        val prepareBagId: Int = R.string.action_prepare_bag
        val startDayId: Int = R.string.action_start_day_phase
        val startNightId: Int = R.string.action_start_night_phase
        val startFirstNightId: Int = R.string.action_start_first_night_phase
    }
}
package ru.nyxsed.thetome.core.domain.usecase

import ru.nyxsed.thetome.core.domain.models.RoleType
import javax.inject.Inject

class RoleDistributionUseCase @Inject constructor() {
    private val distribution: Map<Int, Map<RoleType, Int>> = mapOf(
        5 to mapOf(RoleType.TOWNSFOLK to 3, RoleType.OUTSIDER to 0, RoleType.MINION to 1, RoleType.DEMON to 1),
        6 to mapOf(RoleType.TOWNSFOLK to 3, RoleType.OUTSIDER to 1, RoleType.MINION to 1, RoleType.DEMON to 1),
        7 to mapOf(RoleType.TOWNSFOLK to 5, RoleType.OUTSIDER to 0, RoleType.MINION to 1, RoleType.DEMON to 1),
        8 to mapOf(RoleType.TOWNSFOLK to 5, RoleType.OUTSIDER to 1, RoleType.MINION to 1, RoleType.DEMON to 1),
        9 to mapOf(RoleType.TOWNSFOLK to 5, RoleType.OUTSIDER to 2, RoleType.MINION to 1, RoleType.DEMON to 1),
        10 to mapOf(RoleType.TOWNSFOLK to 7, RoleType.OUTSIDER to 0, RoleType.MINION to 2, RoleType.DEMON to 1),
        11 to mapOf(RoleType.TOWNSFOLK to 7, RoleType.OUTSIDER to 1, RoleType.MINION to 2, RoleType.DEMON to 1),
        12 to mapOf(RoleType.TOWNSFOLK to 7, RoleType.OUTSIDER to 2, RoleType.MINION to 2, RoleType.DEMON to 1),
        13 to mapOf(RoleType.TOWNSFOLK to 9, RoleType.OUTSIDER to 0, RoleType.MINION to 3, RoleType.DEMON to 1),
        14 to mapOf(RoleType.TOWNSFOLK to 9, RoleType.OUTSIDER to 1, RoleType.MINION to 3, RoleType.DEMON to 1),
        15 to mapOf(RoleType.TOWNSFOLK to 9, RoleType.OUTSIDER to 2, RoleType.MINION to 3, RoleType.DEMON to 1),
    )

    operator fun invoke(playerCount: Int): Map<RoleType, Int> {
        if (playerCount > 15) {
            return mapOf(
                RoleType.TOWNSFOLK to 9,
                RoleType.OUTSIDER to 2,
                RoleType.MINION to 3,
                RoleType.DEMON to 1
            )
        }

        if (playerCount < 5) {
            return mapOf(
                RoleType.TOWNSFOLK to 3,
                RoleType.OUTSIDER to 0,
                RoleType.MINION to 1,
                RoleType.DEMON to 1
            )
        }

        return distribution[playerCount] ?: throw IllegalArgumentException("Have no distribution for $playerCount players")
    }
}
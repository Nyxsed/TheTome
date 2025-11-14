package ru.nyxsed.thetome.features.settings.presentation

import ru.nyxsed.thetome.core.domain.models.Role
import ru.nyxsed.thetome.core.domain.models.RoleType
import ru.nyxsed.thetome.core.domain.models.Scenery

data class SettingsState(
    val selectedScenery: Scenery? = null,
    val playerCount: Int = 5,
    val roleDistribution: Map<RoleType, Int> = emptyMap(),
    val chosenRoles: List<Role> = emptyList()
)

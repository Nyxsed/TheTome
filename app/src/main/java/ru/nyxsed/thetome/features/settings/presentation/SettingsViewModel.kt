package ru.nyxsed.thetome.features.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.nyxsed.thetome.core.domain.models.GameState
import ru.nyxsed.thetome.core.domain.models.Player
import ru.nyxsed.thetome.core.domain.models.Role
import ru.nyxsed.thetome.core.domain.models.Scenery
import ru.nyxsed.thetome.core.domain.usecase.RoleDistributionUseCase
import ru.nyxsed.thetome.core.domain.usecase.SaveGameStateUseCase
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val roleDistributionUseCase: RoleDistributionUseCase,
    private val saveGameStateUseCase: SaveGameStateUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(SettingsState())
    val state = _state.asStateFlow()

    init {
        changePlayerCount(5)
    }

    fun changePlayerCount(playerCount: Int) {
        val distribution = roleDistributionUseCase(playerCount)
        _state.update { it.copy(playerCount = playerCount, roleDistribution = distribution) }
    }

    fun changeScenery(selectedScenery: Scenery) {
        _state.update { it.copy(selectedScenery = selectedScenery) }
    }

    fun toggleRoleSelection(role: Role) {
        _state.update { state ->
            val selected = state.chosenRoles.toMutableList()
            if (selected.contains(role)) {
                selected.remove(role)
            } else if (selected.size < state.playerCount) {
                selected.add(role)
            }
            state.copy(chosenRoles = selected)
        }
    }

    fun saveGameState() {
        val players = _state.value.chosenRoles.mapIndexed { index, role ->
            Player(
                id = index,
                name = null,
                role = null
            )
        }
        val gameState = GameState(
            scenery = _state.value.selectedScenery,
            chosenRoles = _state.value.chosenRoles,
            players = players
        )
        viewModelScope.launch {
            saveGameStateUseCase(gameState)
        }
    }
}
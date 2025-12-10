package ru.nyxsed.thetome.features.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.nyxsed.thetome.core.domain.models.GameState
import ru.nyxsed.thetome.core.domain.models.Player
import ru.nyxsed.thetome.core.domain.models.Role
import ru.nyxsed.thetome.core.domain.models.Scenery
import ru.nyxsed.thetome.core.domain.usecase.GetListSceneryUseCase
import ru.nyxsed.thetome.core.domain.usecase.LoadGameStateUseCase
import ru.nyxsed.thetome.core.domain.usecase.RoleDistributionUseCase
import ru.nyxsed.thetome.core.domain.usecase.SaveGameStateUseCase
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val roleDistributionUseCase: RoleDistributionUseCase,
    private val saveGameStateUseCase: SaveGameStateUseCase,
    private val loadGameStateUseCase: LoadGameStateUseCase,
    private val getListSceneryUseCase: GetListSceneryUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(SettingsState())
    val state = _state.asStateFlow()
    val listScenery = getListSceneryUseCase()

    init {
        viewModelScope.launch {
            loadGameStateUseCase()
                .onStart {
                    changePlayerCount(5)
                }
                .filterNotNull()
                .collect { loadedState ->
                    _state.update {
                        val playerCount = loadedState.players?.size ?: 5
                        it.copy(
                            selectedScenery = loadedState.scenery,
                            playerCount = playerCount,
                            players = loadedState.players,
                            roleDistribution = roleDistributionUseCase(playerCount),
                            chosenRoles = loadedState.chosenRoles!!
                        )
                    }
                }
        }
    }

    fun changePlayerCount(playerCount: Int) {
        val distribution = roleDistributionUseCase(playerCount)
        _state.update { it.copy(playerCount = playerCount, roleDistribution = distribution) }
    }

    fun changeScenery(selectedScenery: Scenery) {
        _state.update {
            it.copy(
                selectedScenery = selectedScenery,
                chosenRoles = emptyList(),
            )
        }
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
        val roles = _state.value.chosenRoles
        val oldPlayers = _state.value.players

        val players = List(roles.size) { index ->
            val oldName = oldPlayers?.getOrNull(index)?.name
            Player(
                id = index,
                name = oldName,
                role = null
            )
        }
        val gameState = GameState(
            scenery = _state.value.selectedScenery,
            chosenRoles = roles,
            players = players,
            roleDistribution = _state.value.roleDistribution
        )
        viewModelScope.launch {
            saveGameStateUseCase(gameState)
        }
    }

    fun randomRoleSelection() {

        if (_state.value.selectedScenery == null) return

        val distribution = _state.value.roleDistribution
        val sceneryRoles = _state.value.selectedScenery

        val result = mutableListOf<Role>()

        distribution.forEach { (roleType, count) ->
            if (count > 0) {
                val rolesOfType = sceneryRoles!!.roles
                    .filter { it.type == roleType }
                    .shuffled()
                    .take(count)
                result.addAll(rolesOfType)
            }
        }
        _state.update { it.copy(chosenRoles = result) }
    }
}
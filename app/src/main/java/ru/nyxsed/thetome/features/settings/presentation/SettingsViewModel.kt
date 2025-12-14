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
                            chosenRoles = loadedState.chosenRoles!!,
                            allowMultipleRoles = loadedState.allowMultipleRoles
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
            roleDistribution = _state.value.roleDistribution,
            allowMultipleRoles = _state.value.allowMultipleRoles
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

    fun toggleRoleSelection(roleInfo: Pair<Pair<Role, Int>, Boolean>) {
        val (roleData, wasSelected) = roleInfo
        val (role, copyNum) = roleData

        val currentState = _state.value
        val newRoles = currentState.chosenRoles.toMutableList()

        if (currentState.allowMultipleRoles) {
            if (wasSelected) {
                // Удаляем эту и все следующие копии
                val currentSelectedCount = newRoles.count { it == role }

                // Удаляем копии
                val copiesToRemove = currentSelectedCount - copyNum + 1
                if (copiesToRemove > 0) {
                    // Удаляем copiesToRemove последних копий этой роли
                    var removed = 0
                    val iterator = newRoles.listIterator(newRoles.size)
                    while (iterator.hasPrevious() && removed < copiesToRemove) {
                        if (iterator.previous() == role) {
                            iterator.remove()
                            removed++
                        }
                    }
                }
            } else {
                // Добавляем новую копию
                if (newRoles.size < currentState.playerCount) {
                    newRoles.add(role)
                }
            }
        } else {

            if (newRoles.contains(role)) {
                newRoles.removeAll { it == role }
            } else if (newRoles.size < currentState.playerCount) {
                newRoles.add(role)
            }
        }

        _state.update { it.copy(chosenRoles = newRoles) }
    }

    fun setAllowMultipleRoles(allow: Boolean) {
        val currentState = _state.value

        if (!allow && currentState.allowMultipleRoles) {
            // Оставляем только первую копию каждой роли
            val uniqueRoles = mutableListOf<Role>()
            val seenRoles = mutableSetOf<Role>()

            currentState.chosenRoles.forEach { role ->
                if (!seenRoles.contains(role)) {
                    uniqueRoles.add(role)
                    seenRoles.add(role)
                }
            }

            _state.update {
                it.copy(
                    allowMultipleRoles = allow,
                    chosenRoles = uniqueRoles
                )
            }

        } else {
            _state.update { it.copy(allowMultipleRoles = allow) }
        }
    }
}
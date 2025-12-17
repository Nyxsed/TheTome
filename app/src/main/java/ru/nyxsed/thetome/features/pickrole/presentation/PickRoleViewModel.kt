package ru.nyxsed.thetome.features.pickrole.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.nyxsed.thetome.core.domain.models.GameState
import ru.nyxsed.thetome.core.domain.models.Role
import ru.nyxsed.thetome.core.domain.usecase.LoadGameStateUseCase
import ru.nyxsed.thetome.core.domain.usecase.SaveGameStateUseCase
import javax.inject.Inject

@HiltViewModel
class PickRoleViewModel @Inject constructor(
    private val loadGameUseCase: LoadGameStateUseCase,
    private val saveGameStateUseCase: SaveGameStateUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(GameState(null))
    val state = _state.asStateFlow()

    private val _currentPlayerIndex = MutableStateFlow(0)
    val currentPlayerIndex = _currentPlayerIndex.asStateFlow()

    private val _shuffledRoles = MutableStateFlow<List<Role>?>(null)
    val shuffledRoles = _shuffledRoles.asStateFlow()

    private val _currentPlayerName = MutableStateFlow("")
    val currentPlayerName = _currentPlayerName.asStateFlow()

    init {
        viewModelScope.launch {
            loadGameUseCase().collect { loadedGame ->
                loadedGame?.let { game ->
                    _state.value = game
                    if (shuffledRoles.value == null || shuffledRoles.value?.size != game.chosenRoles?.size) {
                        _shuffledRoles.value = game.chosenRoles?.shuffled()
                        _currentPlayerName.value = getName(_currentPlayerIndex.value)
                    }
                }
            }
        }
    }

    fun pickRole(role: Role, playerIndex: Int) {
        _state.update { currentState ->
            currentState.copy(
                players = currentState.players?.mapIndexed { index, player ->
                    if (index == playerIndex) player.copy(role = role) else player
                }
            )
        }
        _currentPlayerIndex.value += 1
        _currentPlayerName.value = getName(_currentPlayerIndex.value)
        saveGameState()
    }

    fun getName(playerIndex: Int): String = _state.value.players?.getOrNull(playerIndex)?.name.orEmpty()

    fun changeName(name: String, playerIndex: Int) {
        _state.update { currentState ->
            val players = currentState.players ?: return@update currentState

            currentState.copy(
                players = players.mapIndexed { index, player ->
                    if (index == playerIndex) player.copy(name = name) else player
                }
            )
        }
        _currentPlayerName.value = name

        saveGameState()
    }

    fun saveGameState() {
        viewModelScope.launch {
            saveGameStateUseCase(_state.value)
        }
    }
}
package ru.nyxsed.thetome.features.game.presentation

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
import ru.nyxsed.thetome.core.domain.models.Token
import ru.nyxsed.thetome.core.domain.usecase.LoadGameStateUseCase
import ru.nyxsed.thetome.core.domain.usecase.SaveGameStateUseCase
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val loadGameUseCase: LoadGameStateUseCase,
    private val saveGameStateUseCase: SaveGameStateUseCase,
) : ViewModel() {

    val _state = MutableStateFlow(GameState(null))
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            loadGameUseCase().collect { loadedGame ->
                _state.update {
                    it.copy(
                        scenery = loadedGame?.scenery,
                        players = loadedGame?.players,
                        chosenRoles = loadedGame?.chosenRoles,
                    )
                }
            }
        }
    }


    fun saveGameState() {
        viewModelScope.launch {
            saveGameStateUseCase(_state.value)
        }
    }

    fun updateTokens(player: Player, tokens: List<Token>) {
        _state.update { currentState ->
            val updatedPlayers = currentState.players?.map { currentPlayer ->
                if (currentPlayer == player) currentPlayer.copy(tokens = tokens) else currentPlayer
            }
            currentState.copy(players = updatedPlayers)
        }
        saveGameState()
    }

    fun changeAliveStatus(player: Player) {
        _state.update { currentState ->
            val updatedPlayers = currentState.players?.map { currentPlayer ->
                if (currentPlayer == player) currentPlayer.copy(isAlive = !currentPlayer.isAlive) else currentPlayer
            }
            currentState.copy(players = updatedPlayers)
        }
        saveGameState()
    }

    fun renamePlayer(player: Player, name: String) {
        _state.update { currentState ->
            val updatedPlayers = currentState.players?.map { currentPlayer ->
                if (currentPlayer == player) currentPlayer.copy(name = name) else currentPlayer
            }
            currentState.copy(players = updatedPlayers)
        }
        saveGameState()
    }

    fun changeRole(player: Player, role: Role?) {
        _state.update { currentState ->
            val updatedPlayers = currentState.players?.map { currentPlayer ->
                if (currentPlayer == player) currentPlayer.copy(role = role) else currentPlayer
            }
            currentState.copy(players = updatedPlayers)
        }
        saveGameState()
    }
}
package ru.nyxsed.thetome.features.game.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.nyxsed.thetome.core.domain.models.*
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
                loadedGame?.let { game ->
                    _state.value = game
                    val actions = getActionList(game)
                    val current = actions.getOrNull(game.actionIndex)
                    _state.value = _state.value.copy(currentAction = current)
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

    fun onChangeGhostVoteStatus(player: Player) {
        _state.update { currentState ->
            val updatedPlayers = currentState.players?.map { currentPlayer ->
                if (currentPlayer == player) currentPlayer.copy(haveGhostVote = !currentPlayer.haveGhostVote) else currentPlayer
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

    fun changeDemonBluffs(bluffs: List<Role?>) {
        _state.update {
            it.copy(demonBluffs = bluffs)
        }
        saveGameState()
    }

    private fun getActionList(state: GameState): List<Action> {
        val chosenRoles = state.chosenRoles
        val aliveRoles = state.players?.filter { it.isAlive }?.map { it.role }

        val list = when (state.currentPhase) {
            GamePhase.PREPARE ->
                state.scenery?.prepareActions
                    ?.filterNot { action ->
                        action.actionType == ActionType.PLAYERS_7 && state.players?.size!! < 7
                    }
                    ?.filterNot { action ->
                        action.actionType == ActionType.PLAYER && chosenRoles?.contains(action.role) != true
                    }
                    ?: emptyList()

            GamePhase.FIRST_NIGHT ->
                state.scenery?.firstNightActions
                    ?.filterNot { action ->
                        action.actionType == ActionType.PLAYERS_7 && state.players?.size!! < 7
                    }
                    ?.filterNot { action ->
                        action.actionType == ActionType.PLAYER && chosenRoles?.contains(action.role) != true
                    }
                    ?: emptyList()

            GamePhase.SECOND_NIGHT ->
                state.scenery?.secondNightActions
                    ?.filterNot { action ->
                        action.actionType == ActionType.PLAYER && chosenRoles?.contains(action.role) != true
                    }
                    ?.filter { action ->
                        aliveRoles?.contains(action.role) == true
                    } ?: emptyList()

            GamePhase.DAY ->
                state.scenery?.dayActions
                    ?.filterNot { action ->
                        action.actionType == ActionType.PLAYER && chosenRoles?.contains(action.role) != true
                    }
                    ?.filter { action ->
                        aliveRoles?.contains(action.role) == true
                    } ?: emptyList()
        }
        return list
    }

    private fun updateCurrentAction() {
        val actions = getActionList(_state.value)
        _state.update { it.copy(currentAction = actions[_state.value.actionIndex]) }
        saveGameState()
    }

    fun moveToNextAction() {
        val state = _state.value
        val actions = getActionList(state)

        val isLast = state.actionIndex == actions.lastIndex

        if (!isLast) {
            _state.update { it.copy(actionIndex = state.actionIndex + 1) }
        } else {
            when (state.currentPhase) {
                GamePhase.PREPARE -> {
                    _state.update {
                        it.copy(currentPhase = GamePhase.FIRST_NIGHT, actionIndex = 0)
                    }
                }

                GamePhase.FIRST_NIGHT -> {
                    _state.update {
                        it.copy(currentPhase = GamePhase.DAY, actionIndex = 0)
                    }
                }

                GamePhase.DAY -> {
                    _state.update {
                        it.copy(currentPhase = GamePhase.SECOND_NIGHT, actionIndex = 0)
                    }
                }

                GamePhase.SECOND_NIGHT -> {
                    _state.update {
                        it.copy(currentPhase = GamePhase.DAY, actionIndex = 0)
                    }
                }
            }
        }
        updateCurrentAction()
    }

    fun moveToPreviousAction() {
        val state = _state.value

        val isFirst = state.actionIndex == 0

        if (!isFirst) {
            _state.update { it.copy(actionIndex = state.actionIndex - 1) }
        } else {
            when (state.currentPhase) {
                GamePhase.PREPARE -> {
                    _state.update { it.copy(actionIndex = 0) }
                }

                GamePhase.FIRST_NIGHT -> {
                    val prepare = state.scenery?.prepareActions ?: emptyList()
                    _state.update {
                        it.copy(
                            currentPhase = GamePhase.PREPARE,
                            actionIndex = (prepare.lastIndex).coerceAtLeast(0)
                        )
                    }
                }

                GamePhase.SECOND_NIGHT -> {
                    val day = state.scenery?.dayActions ?: emptyList()
                    _state.update {
                        it.copy(
                            currentPhase = GamePhase.DAY,
                            actionIndex = (day.lastIndex).coerceAtLeast(0)
                        )
                    }
                }

                GamePhase.DAY -> {
                    val secondNight = state.scenery?.secondNightActions ?: emptyList()
                    _state.update {
                        it.copy(
                            currentPhase = GamePhase.SECOND_NIGHT,
                            actionIndex = (secondNight.lastIndex).coerceAtLeast(0)
                        )
                    }
                }
            }
        }
        updateCurrentAction()
    }
}
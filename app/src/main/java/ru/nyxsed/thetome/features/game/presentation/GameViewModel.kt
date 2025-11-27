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


    fun deleteToken(player: Player, tokeIndex: Int) {
        val newTokens = player.tokens.toMutableList().also { it.removeAt(tokeIndex) }
        _state.update { currentState ->
            val updatedPlayers = currentState.players?.map { currentPlayer ->
                if (currentPlayer == player) currentPlayer.copy(tokens = newTokens) else currentPlayer
            }
            currentState.copy(players = updatedPlayers)
        }
        saveGameState()
    }


    fun addToken(player: Player, token: Token) {
        val newTokens = player.tokens.toMutableList().also { it.add(token) }
        _state.update { currentState ->
            val updatedPlayers = currentState.players?.map { currentPlayer ->
                if (currentPlayer == player) currentPlayer.copy(tokens = newTokens) else currentPlayer
            }
            currentState.copy(players = updatedPlayers)
        }
        saveGameState()
    }


    fun changeAliveStatus(player: Player) {
        _state.update { currentState ->
            val updatedPlayers = currentState.players?.map { currentPlayer ->
                if (currentPlayer.id == player.id) currentPlayer.copy(isAlive = !currentPlayer.isAlive)
                else currentPlayer
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

    fun editNotes(notes: String) {
        _state.update {
            it.copy(notes = notes)
        }
        saveGameState()
    }


    private fun getActionList(state: GameState): List<Action> {
        val chosenRoles = state.chosenRoles
        val aliveRoles = state.players?.filter { it.isAlive }?.map { it.role }
        val killedRoles = state.players?.filter { !it.isAlive }?.map { it.role }
        val currentRoles = (chosenRoles.orEmpty() + aliveRoles.orEmpty()).distinctBy { it?.roleName }

        val phaseActions = when (state.currentPhase) {
            GamePhase.PREPARE -> state.scenery?.prepareActions
            GamePhase.FIRST_NIGHT -> state.scenery?.firstNightActions
            GamePhase.SECOND_NIGHT -> state.scenery?.secondNightActions
            GamePhase.DAY -> state.scenery?.dayActions
        }?.filterNot { action ->
            action.type == ActionType.PLAYERS_7 && state.players?.size!! < 7
        }?.filter { action ->
            (action.type != ActionType.PLAYER) || currentRoles.contains(action.role)
        }?.filterNot { action ->
            action.type == ActionType.PLAYER && killedRoles?.contains(action.role) == true
        } ?: emptyList()

        return phaseActions
    }


    private fun updateCurrentAction() {
        val state = _state.value
        val actions = getActionList(state)
        val currentAction = actions.getOrElse(state.actionIndex) { actions[0] }
        _state.update {
            it.copy(
                currentAction = currentAction
            )
        }

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
                        it.copy(currentPhase = GamePhase.DAY, actionIndex = 0, currentDay = it.currentDay + 1)
                    }
                }

                GamePhase.DAY -> {
                    _state.update {
                        it.copy(currentPhase = GamePhase.SECOND_NIGHT, actionIndex = 0)
                    }
                }

                GamePhase.SECOND_NIGHT -> {
                    _state.update {
                        it.copy(currentPhase = GamePhase.DAY, actionIndex = 0, currentDay = it.currentDay + 1)
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
                    val prevActions = getActionList(state.copy(currentPhase = GamePhase.PREPARE))
                    _state.update {
                        it.copy(
                            currentPhase = GamePhase.PREPARE,
                            actionIndex = prevActions.lastIndex
                        )
                    }
                }

                GamePhase.DAY -> {
                    val prevActions = getActionList(state.copy(currentPhase = GamePhase.SECOND_NIGHT))
                    _state.update {
                        it.copy(
                            currentPhase = GamePhase.SECOND_NIGHT,
                            actionIndex = prevActions.lastIndex
                        )
                    }
                }

                GamePhase.SECOND_NIGHT -> {
                    val prevActions = getActionList(state.copy(currentPhase = GamePhase.DAY))
                    _state.update {
                        it.copy(
                            currentPhase = GamePhase.DAY,
                            actionIndex = prevActions.lastIndex,
                            currentDay = it.currentDay - 1,
                        )
                    }
                }
            }
        }
        updateCurrentAction()
    }
}
package ru.nyxsed.thetome.features.game.presentation

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.nyxsed.thetome.core.domain.models.*
import ru.nyxsed.thetome.core.domain.usecase.GenerateQrUseCase
import ru.nyxsed.thetome.core.domain.usecase.LoadGameStateUseCase
import ru.nyxsed.thetome.core.domain.usecase.RoleDistributionUseCase
import ru.nyxsed.thetome.core.domain.usecase.SaveGameStateUseCase
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val loadGameUseCase: LoadGameStateUseCase,
    private val saveGameStateUseCase: SaveGameStateUseCase,
    private val generateQrUseCase: GenerateQrUseCase,
    private val roleDistributionUseCase: RoleDistributionUseCase
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
                    _state.update { it.copy(currentAction = current, actions = actions) }

                    val fabled = if (_state.value.fabled == null) Player(id = 666, name = null, role = null) else _state.value.fabled
                    _state.update { it.copy(fabled = fabled) }
                }
            }
        }
    }


    fun saveGameState() {
        viewModelScope.launch {
            saveGameStateUseCase(_state.value)
        }
    }


    fun deleteToken(player: Player, tokenIndex: Int) {
        val newTokens = player.tokens.toMutableList().also { it.removeAt(tokenIndex) }

        _state.update { currentState ->

            val updatedPlayers = currentState.players?.map { currentPlayer ->
                if (currentPlayer == player)
                    currentPlayer.copy(tokens = newTokens)
                else
                    currentPlayer
            }

            val updatedFabled =
                if (player == currentState.fabled)
                    currentState.fabled.copy(tokens = newTokens)
                else
                    currentState.fabled

            currentState.copy(
                players = updatedPlayers,
                fabled = updatedFabled
            )
        }

        saveGameState()
    }


    fun addToken(player: Player, token: Token) {
        val newTokens = player.tokens.toMutableList().also { it.add(token) }

        _state.update { currentState ->

            val updatedPlayers = currentState.players?.map { currentPlayer ->
                if (currentPlayer == player)
                    currentPlayer.copy(tokens = newTokens)
                else
                    currentPlayer
            }

            val updatedFabled =
                if (player == currentState.fabled)
                    currentState.fabled.copy(tokens = newTokens)
                else
                    currentState.fabled

            currentState.copy(
                players = updatedPlayers,
                fabled = updatedFabled
            )
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

            val updatedFabled =
                if (player == currentState.fabled)
                    currentState.fabled.copy(role = role)
                else
                    currentState.fabled

            val distribution = roleDistributionUseCase(
                updatedPlayers?.filter { player -> player.role?.type != RoleType.TRAVELLER }?.size ?: 0
            )

            currentState.copy(players = updatedPlayers, fabled = updatedFabled, roleDistribution = distribution)
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


    private fun getRoleActionsForPhase(role: Role, phase: GamePhase): Action? {
        val actionId = when (phase) {
            GamePhase.PREPARE -> role.prepareActionId
            GamePhase.FIRST_NIGHT -> role.firstNightActionId
            GamePhase.SECOND_NIGHT -> role.secondNightActionId
            GamePhase.DAY -> role.dayActionId
        }

        return if (actionId != 0) {
            Action(
                role = role,
                res = actionId
            )
        } else {
            null
        }
    }

    private fun getActionList(state: GameState): List<Action> {
        val chosenRoles = state.chosenRoles
        val alivePlayers = state.players?.filter { it.isAlive } ?: emptyList()
        val aliveRoles = alivePlayers.map { it.role }
        val killedRoles = state.players?.filter { !it.isAlive }?.map { it.role } ?: emptyList()

        val fabledRole = state.fabled?.role

        // Получаем все уникальные роли, которые есть в игре (выбранные + живые)
        val currentRoles = (chosenRoles.orEmpty() + aliveRoles.orEmpty() + fabledRole)
            .distinctBy { it?.roleName }
            .filterNotNull()

        // Разделяем на группы для сортировки
        val players7ActionPrepare = mutableListOf<Action>()
        val playersActionPrepare = mutableListOf<Action>()
        val players7ActionFirstNight = mutableListOf<Action>()
        val roleActions = mutableListOf<Action>()
        val endAction = mutableListOf<Action>()


        if (state.currentPhase == GamePhase.PREPARE) {
            playersActionPrepare.add(Action(null, Action.prepareBagId))
        }
        // отдельная группа (всегда первое)
        if (state.currentPhase == GamePhase.PREPARE && (state.players?.size ?: 0) >= 7) {
            players7ActionPrepare.add(Action(null, Action.prepareBluffsId))
        }

        if (state.currentPhase == GamePhase.FIRST_NIGHT && (state.players?.size ?: 0) >= 7) {
            players7ActionFirstNight.add(Action(null, Action.showMinionsId))
            players7ActionFirstNight.add(Action(null, Action.showDemonId))
        }

        // Собираем действия
        currentRoles.forEach { role ->
            if (!killedRoles.contains(role)) {
                getRoleActionsForPhase(role, state.currentPhase)?.let { action ->
                    roleActions.add(action)
                }
            }
        }

        // отдельная группа (всегда последнее)
        when (state.currentPhase) {
            GamePhase.FIRST_NIGHT, GamePhase.SECOND_NIGHT -> {
                endAction.add(Action(null, Action.startDayId))
            }

            GamePhase.DAY -> {
                endAction.add(Action(null, Action.startNightId))
            }

            GamePhase.PREPARE -> {
                endAction.add(Action(null, Action.startFirstNightId))
            }
        }

        val sortedRoleActions = roleActions.sortedBy { it.role?.nightPriority ?: Int.MAX_VALUE }

        return playersActionPrepare + players7ActionPrepare + players7ActionFirstNight + sortedRoleActions + endAction
    }


    private fun updateCurrentAction() {
        val state = _state.value
        val actions = getActionList(state)
        val currentAction = actions.getOrElse(state.actionIndex) { actions[0] }
        _state.update {
            it.copy(
                currentAction = currentAction,
                actions = actions,
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
                            actionIndex = prevActions.lastIndex,
                        )
                    }
                }

                GamePhase.DAY -> {
                    val prevActions = getActionList(state.copy(currentPhase = GamePhase.SECOND_NIGHT))
                    _state.update {
                        it.copy(
                            currentPhase = GamePhase.SECOND_NIGHT,
                            actionIndex = prevActions.lastIndex,
                            currentDay = it.currentDay - 1,
                        )
                    }
                }

                GamePhase.SECOND_NIGHT -> {
                    val prevActions = getActionList(state.copy(currentPhase = GamePhase.DAY))
                    _state.update {
                        it.copy(
                            currentPhase = GamePhase.DAY,
                            actionIndex = prevActions.lastIndex,
                        )
                    }
                }
            }
        }
        updateCurrentAction()
    }


    fun generateQr(link: String): Bitmap? {
        return generateQrUseCase(link)
    }


    fun addPlayer() {
        _state.update { state ->
            val current = state.players
            val newId = current?.size ?: 0

            val newPlayer = Player(
                id = newId,
                name = null,
                role = null
            )

            val newPlayers = current?.plus(newPlayer)

            val distribution = roleDistributionUseCase(
                newPlayers?.filter { player -> player.role?.type != RoleType.TRAVELLER }?.size ?: 0
            )

            state.copy(players = newPlayers, roleDistribution = distribution)
        }
        saveGameState()
    }

    fun deletePlayer(player: Player) {
        _state.update { state ->
            val current = state.players

            if (state.players?.contains(player) != true) return@update state

            val updated = current
                .filter { it != player }
                .mapIndexed { newIndex, player ->
                    player.copy(id = newIndex)
                }

            val distribution = roleDistributionUseCase(
                updated.filterNot { player -> player.role?.type == RoleType.TRAVELLER }.size
            )

            state.copy(players = updated, roleDistribution = distribution)
        }

        saveGameState()
    }


    fun updatePlayers(players: List<Player>) {
        _state.update { it.copy(players = players) }
        saveGameState()
    }


    fun playerPositionChange(player: Player, x: Float, y: Float) {
        _state.update { currentState ->
            val updatedPlayers = currentState.players?.map { currentPlayer ->
                if (currentPlayer.id == player.id) {
                    currentPlayer.copy(x = x, y = y)
                } else {
                    currentPlayer
                }
            }
            currentState.copy(players = updatedPlayers)
        }
        saveGameState()
    }

    fun changePositionMode() {
        _state.update { it.copy(freePosition = !it.freePosition) }
        saveGameState()
    }
}
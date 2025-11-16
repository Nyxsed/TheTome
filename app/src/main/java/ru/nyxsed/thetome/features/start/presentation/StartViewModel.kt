package ru.nyxsed.thetome.features.start.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.nyxsed.thetome.core.domain.models.GameState
import ru.nyxsed.thetome.core.domain.models.Player
import ru.nyxsed.thetome.core.domain.usecase.LoadGameStateUseCase
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val loadGameUseCase: LoadGameStateUseCase
): ViewModel() {
    private val _state = MutableStateFlow(GameState(null))
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            loadGameUseCase().collect { loadedGame ->
                _state.update { it.copy(
                    scenery = loadedGame?.scenery,
                    players = emptyList(),
                    chosenRoles = loadedGame?.chosenRoles,
                    roleDistribution = loadedGame?.roleDistribution
                ) }
            }

        }
    }
}
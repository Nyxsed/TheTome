package ru.nyxsed.thetome.core.domain.usecase

import ru.nyxsed.thetome.core.domain.models.GameState
import ru.nyxsed.thetome.core.domain.repository.GameStateRepository
import javax.inject.Inject

class SaveGameStateUseCase @Inject constructor(private val repository: GameStateRepository) {
    suspend operator fun invoke(gameState: GameState) {
        repository.saveGameState(gameState)
    }
}
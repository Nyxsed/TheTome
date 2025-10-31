package ru.nyxsed.thetome.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.nyxsed.thetome.core.domain.models.GameState
import ru.nyxsed.thetome.core.domain.repository.GameStateRepository
import javax.inject.Inject

class LoadGameStateUseCase @Inject constructor(private val repository: GameStateRepository) {
    suspend operator fun invoke(): Flow<GameState?> {
        return repository.loadGameState()
    }
}
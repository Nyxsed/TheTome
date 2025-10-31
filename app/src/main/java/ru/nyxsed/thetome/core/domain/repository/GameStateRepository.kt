package ru.nyxsed.thetome.core.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.nyxsed.thetome.core.domain.models.GameState

interface GameStateRepository {
    suspend fun saveGameState(gameState: GameState)
    suspend fun loadGameState(): Flow<GameState?>
}
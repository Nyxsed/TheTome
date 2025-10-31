package ru.nyxsed.thetome.core.data.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import ru.nyxsed.thetome.core.domain.models.GameState
import ru.nyxsed.thetome.core.domain.repository.GameStateRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameStateRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : GameStateRepository {

    private val json = Json { encodeDefaults = true; ignoreUnknownKeys = true }

    override suspend fun saveGameState(state: GameState) {
        val jsonString = json.encodeToString(GameState.serializer(), state)
        context.gameStateDataStore.updateData { preferences ->
            preferences.toMutablePreferences().apply {
                this[KEY_GAME_STATE] = jsonString
            }
        }
    }

    override suspend fun loadGameState(): Flow<GameState?> {
        return context.gameStateDataStore.data
            .map { prefs ->
                prefs[KEY_GAME_STATE]?.let {
                    json.decodeFromString(GameState.serializer(), it)
                }
            }
    }
}
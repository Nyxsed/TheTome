package ru.nyxsed.thetome.core.data.repository

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.gameStateDataStore by preferencesDataStore(name = "game_state")
val KEY_GAME_STATE = stringPreferencesKey("game_state")
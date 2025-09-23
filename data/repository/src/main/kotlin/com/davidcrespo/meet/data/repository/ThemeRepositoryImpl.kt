package com.davidcrespo.meet.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.davidcrespo.domain.models.theme.ThemeMode
import com.davidcrespo.meet.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow
import com.davidcrespo.meet.core.common.Constants
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map

private val THEME_KEY = stringPreferencesKey(Constants.DataStore.THEME_KEY)

class ThemeRepositoryImpl(
    private val dataStore: DataStore<Preferences>
): ThemeRepository {

    override val themeModeFlow: Flow<ThemeMode> = dataStore.data
        .map { preferences ->
            when (preferences[THEME_KEY]) {
                ThemeMode.DARK.name -> ThemeMode.DARK
                ThemeMode.LIGHT.name -> ThemeMode.LIGHT
                else -> ThemeMode.SYSTEM
            }
        }

    override suspend fun setThemeMode(theme: ThemeMode) {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = theme.name
        }
    }
}
package com.davidcrespo.meet.domain.repository

import com.davidcrespo.domain.models.theme.ThemeMode
import kotlinx.coroutines.flow.Flow

interface ThemeRepository {
    val themeModeFlow: Flow<ThemeMode>
    suspend fun setThemeMode(mode: ThemeMode)
}

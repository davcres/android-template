package com.davcres.template.domain.repository

import com.davcres.domain.models.theme.ThemeMode
import kotlinx.coroutines.flow.Flow

interface ThemeRepository {
    val themeModeFlow: Flow<ThemeMode>
    suspend fun setThemeMode(mode: ThemeMode)
}

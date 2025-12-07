package com.davcres.template.domain.repository

import com.davcres.template.core.common.models.ThemeMode
import kotlinx.coroutines.flow.Flow

interface ThemeRepository {
    val themeModeFlow: Flow<ThemeMode>
    suspend fun setThemeMode(mode: ThemeMode)
}

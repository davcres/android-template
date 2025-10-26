package com.davcres.domain.usecases

import com.davcres.domain.models.theme.ThemeMode
import com.davcres.template.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow

class GetThemeUseCase(
    private val repo: ThemeRepository
) {
    operator fun invoke(): Flow<ThemeMode> = repo.themeModeFlow
}

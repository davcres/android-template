package com.davcres.domain.usecases

import com.davcres.template.core.common.models.ThemeMode
import com.davcres.template.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow

class GetThemeUseCase(
    private val repo: ThemeRepository
) {
    operator fun invoke(): Flow<ThemeMode> = repo.themeModeFlow
}

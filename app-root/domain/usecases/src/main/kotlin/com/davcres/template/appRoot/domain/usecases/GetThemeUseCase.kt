package com.davcres.template.appRoot.domain.usecases

import com.davcres.template.appRoot.domain.repository.ThemeRepository
import com.davcres.template.core.common.models.ThemeMode
import kotlinx.coroutines.flow.Flow

class GetThemeUseCase(
    private val repo: ThemeRepository
) {
    operator fun invoke(): Flow<ThemeMode> = repo.themeModeFlow
}

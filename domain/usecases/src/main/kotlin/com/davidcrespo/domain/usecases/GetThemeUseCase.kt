package com.davidcrespo.domain.usecases

import com.davidcrespo.domain.models.theme.ThemeMode
import com.davidcrespo.template.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow

class GetThemeUseCase(
    private val repo: ThemeRepository
) {
    operator fun invoke(): Flow<ThemeMode> = repo.themeModeFlow
}

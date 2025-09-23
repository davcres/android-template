package com.davidcrespo.domain.usecases

import com.davidcrespo.domain.models.theme.ThemeMode
import com.davidcrespo.meet.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.lang.Thread.sleep

class GetThemeUseCase(
    private val repo: ThemeRepository
) {
    operator fun invoke(): Flow<ThemeMode> = repo.themeModeFlow
}
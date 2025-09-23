package com.davidcrespo.domain.usecases

import com.davidcrespo.domain.models.theme.ThemeMode
import com.davidcrespo.meet.domain.repository.ThemeRepository

class SetThemeUseCase(
    private val repo: ThemeRepository
) {
    suspend operator fun invoke(themeMode: ThemeMode) {
        repo.setThemeMode(themeMode)
    }
}
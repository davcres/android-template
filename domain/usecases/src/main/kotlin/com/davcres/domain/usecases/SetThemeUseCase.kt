package com.davcres.domain.usecases

import com.davcres.domain.models.theme.ThemeMode
import com.davcres.template.domain.repository.ThemeRepository

class SetThemeUseCase(
    private val repo: ThemeRepository
) {
    suspend operator fun invoke(themeMode: ThemeMode) {
        repo.setThemeMode(themeMode)
    }
}

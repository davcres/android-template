package com.davcres.domain.usecases

import com.davcres.template.core.common.models.ThemeMode
import com.davcres.template.domain.repository.ThemeRepository

class SetThemeUseCase(
    private val repo: ThemeRepository
) {
    suspend operator fun invoke(themeMode: ThemeMode) {
        repo.setThemeMode(themeMode)
    }
}

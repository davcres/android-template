package com.davcres.template.appRoot.domain.usecases

import com.davcres.template.appRoot.domain.repository.ThemeRepository
import com.davcres.template.core.common.models.ThemeMode

class SetThemeUseCase(
    private val repo: ThemeRepository
) {
    suspend operator fun invoke(themeMode: ThemeMode) {
        repo.setThemeMode(themeMode)
    }
}

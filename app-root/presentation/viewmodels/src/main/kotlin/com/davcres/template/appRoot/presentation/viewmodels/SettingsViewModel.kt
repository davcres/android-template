package com.davcres.template.appRoot.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.davcres.template.appRoot.domain.usecases.GetThemeUseCase
import com.davcres.template.appRoot.domain.usecases.SetThemeUseCase
import com.davcres.template.core.common.Constants.Flow.SUBSCRIPTION_DURATION
import com.davcres.template.core.common.models.ThemeMode
import com.davcres.template.core.viewmodels.BaseViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val getThemeUseCase: GetThemeUseCase,
    private val setThemeUseCase: SetThemeUseCase
) : BaseViewModel() {

    val themeMode: StateFlow<ThemeMode> =
        getThemeUseCase()
            .distinctUntilChanged()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(SUBSCRIPTION_DURATION),
                initialValue = ThemeMode.SYSTEM
            )

    fun setTheme(mode: ThemeMode) {
        viewModelScope.launch {
            setThemeUseCase(mode)
        }
    }
}

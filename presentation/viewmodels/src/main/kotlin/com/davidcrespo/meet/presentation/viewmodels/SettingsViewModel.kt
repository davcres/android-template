package com.davidcrespo.meet.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.davidcrespo.domain.models.theme.ThemeMode
import com.davidcrespo.domain.usecases.GetThemeUseCase
import com.davidcrespo.domain.usecases.SetThemeUseCase
import com.davidcrespo.meet.core.viewmodels.BaseViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val getThemeUseCase: GetThemeUseCase,
    private val setThemeUseCase: SetThemeUseCase
): BaseViewModel() {

    val themeMode: StateFlow<ThemeMode> =
        getThemeUseCase()
            .distinctUntilChanged()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ThemeMode.SYSTEM
            )

    fun setTheme(mode: ThemeMode) {
        viewModelScope.launch {
            setThemeUseCase(mode)
        }
    }
}
package com.davidcrespo.meet.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.davidcrespo.domain.models.theme.ThemeMode
import com.davidcrespo.domain.usecases.GetThemeUseCase
import com.davidcrespo.domain.usecases.SetThemeUseCase
import com.davidcrespo.meet.core.common.Constants.Flow.SUBSCRIPTION_DURATION
import com.davidcrespo.meet.core.viewmodels.BaseViewModel
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

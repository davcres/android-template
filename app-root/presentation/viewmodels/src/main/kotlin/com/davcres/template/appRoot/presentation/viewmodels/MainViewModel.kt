package com.davcres.template.appRoot.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davcres.template.appRoot.domain.usecases.GetThemeUseCase
import com.davcres.template.core.common.Constants.Flow.SUBSCRIPTION_DURATION
import com.davcres.template.core.common.models.ThemeMode
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn

class MainViewModel(
    private val getThemeUseCase: GetThemeUseCase
) : ViewModel() {

    val themeMode: StateFlow<ThemeMode> =
        getThemeUseCase()
            .distinctUntilChanged()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(SUBSCRIPTION_DURATION),
                initialValue = ThemeMode.SYSTEM
            )
}

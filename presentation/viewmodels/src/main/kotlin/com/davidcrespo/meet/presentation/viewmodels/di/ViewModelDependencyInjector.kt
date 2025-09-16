package com.davidcrespo.meet.presentation.viewmodels.di

import com.davidcrespo.meet.core.common.di.KoinModuleLoader
import com.davidcrespo.meet.presentation.viewmodels.SettingsViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

object ViewModelDependencyInjector : KoinModuleLoader {
    override val modules: List<Module>
        get() = listOf(
            module {
                viewModelOf(::SettingsViewModel)

            }
        )
}

package com.davcres.template.presentation.viewmodels.di

import com.davcres.template.core.common.di.KoinModuleLoader
import com.davcres.template.presentation.viewmodels.MainViewModel
import com.davcres.template.presentation.viewmodels.SettingsViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

object ViewModelDependencyInjector : KoinModuleLoader {
    override val modules: List<Module>
        get() = listOf(
            module {
                // viewModel { SettingsViewModel(get(), get()) }
                viewModelOf(::MainViewModel)
                viewModelOf(::SettingsViewModel)
            }
        )
}

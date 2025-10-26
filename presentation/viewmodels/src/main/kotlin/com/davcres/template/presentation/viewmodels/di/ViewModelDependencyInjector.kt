package com.davcres.template.presentation.viewmodels.di

import com.davcres.template.core.common.di.KoinModuleLoader
import com.davcres.template.presentation.viewmodels.SettingsViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

object ViewModelDependencyInjector : KoinModuleLoader {
    override val modules: List<Module>
        get() = listOf(
            module {
                // viewModelOf(::SettingsViewModel)
                viewModel { SettingsViewModel(get(), get()) }
            }
        )
}

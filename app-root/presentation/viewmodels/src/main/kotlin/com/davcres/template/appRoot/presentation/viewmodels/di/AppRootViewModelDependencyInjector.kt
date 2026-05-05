package com.davcres.template.appRoot.presentation.viewmodels.di

import com.davcres.template.appRoot.presentation.viewmodels.MainViewModel
import com.davcres.template.appRoot.presentation.viewmodels.SettingsViewModel
import com.davcres.template.core.common.di.KoinModuleLoader
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

object AppRootViewModelDependencyInjector : KoinModuleLoader {
    override val modules: List<Module>
        get() = listOf(
            module {
                viewModelOf(::MainViewModel)
                viewModelOf(::SettingsViewModel)
            }
        )
}

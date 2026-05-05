package com.davcres.template.appRoot.domain.usecases.di

import com.davcres.template.appRoot.domain.usecases.GetThemeUseCase
import com.davcres.template.appRoot.domain.usecases.SetThemeUseCase
import com.davcres.template.core.common.di.KoinModuleLoader
import org.koin.core.module.Module
import org.koin.dsl.module

object AppRootUseCaseDependencyInjector : KoinModuleLoader {
    override val modules: List<Module>
        get() = listOf(
            module {
                factory { GetThemeUseCase(get()) }
                factory { SetThemeUseCase(get()) }
            }
        )
}

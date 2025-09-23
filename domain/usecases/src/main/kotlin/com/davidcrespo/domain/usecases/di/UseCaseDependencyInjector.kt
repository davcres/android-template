package com.davidcrespo.domain.usecases.di

import com.davidcrespo.domain.usecases.GetThemeUseCase
import com.davidcrespo.domain.usecases.SetThemeUseCase
import com.davidcrespo.meet.core.common.di.KoinModuleLoader
import org.koin.core.module.Module
import org.koin.dsl.module

object UseCaseDependencyInjector : KoinModuleLoader {
    override val modules: List<Module>
        get() = listOf(
            module {
                factory { GetThemeUseCase(get()) }
                factory { SetThemeUseCase(get()) }
            }
        )
}

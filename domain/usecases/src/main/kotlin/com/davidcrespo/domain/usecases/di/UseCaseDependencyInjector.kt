package com.davidcrespo.domain.usecases.di

import com.davidcrespo.meet.core.common.di.KoinModuleLoader
import org.koin.core.module.Module
import org.koin.dsl.module

object UseCaseDependencyInjector : KoinModuleLoader {
    override val modules: List<Module>
        get() = listOf(
            module {

            }
        )
}

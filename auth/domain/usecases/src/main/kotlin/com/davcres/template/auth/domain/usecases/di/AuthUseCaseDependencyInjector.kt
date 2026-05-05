package com.davcres.template.auth.domain.usecases.di

import com.davcres.template.core.common.di.KoinModuleLoader
import org.koin.core.module.Module
import org.koin.dsl.module

object AuthUseCaseDependencyInjector : KoinModuleLoader {
    override val modules: List<Module>
        get() = listOf(
            module {
            }
        )
}

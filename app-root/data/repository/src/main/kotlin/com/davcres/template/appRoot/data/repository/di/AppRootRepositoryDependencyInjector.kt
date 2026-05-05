package com.davcres.template.appRoot.data.repository.di

import com.davcres.template.appRoot.data.repository.ThemeRepositoryImpl
import com.davcres.template.appRoot.domain.repository.ThemeRepository
import com.davcres.template.core.common.di.KoinModuleLoader
import org.koin.core.module.Module
import org.koin.dsl.module

object AppRootRepositoryDependencyInjector : KoinModuleLoader {
    override val modules: List<Module>
        get() = listOf(
            module {
                single<ThemeRepository> { ThemeRepositoryImpl(get()) }
            }
        )
}

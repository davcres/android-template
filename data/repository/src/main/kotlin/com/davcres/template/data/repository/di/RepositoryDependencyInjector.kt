package com.davcres.template.data.repository.di

import com.davcres.template.core.common.di.KoinModuleLoader
import com.davcres.template.data.repository.ThemeRepositoryImpl
import com.davcres.template.domain.repository.ThemeRepository
import org.koin.core.module.Module
import org.koin.dsl.module

object RepositoryDependencyInjector : KoinModuleLoader {
    override val modules: List<Module>
        get() = listOf(
            module {
                single<ThemeRepository> { ThemeRepositoryImpl(get()) }
            }
        )
}

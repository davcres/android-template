package com.davidcrespo.template.data.repository.di

import com.davidcrespo.template.core.common.di.KoinModuleLoader
import com.davidcrespo.template.data.repository.ThemeRepositoryImpl
import com.davidcrespo.template.domain.repository.ThemeRepository
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

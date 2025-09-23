package com.davidcrespo.meet.data.repository.di

import com.davidcrespo.meet.core.common.di.KoinModuleLoader
import com.davidcrespo.meet.data.repository.ThemeRepositoryImpl
import com.davidcrespo.meet.domain.repository.ThemeRepository
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

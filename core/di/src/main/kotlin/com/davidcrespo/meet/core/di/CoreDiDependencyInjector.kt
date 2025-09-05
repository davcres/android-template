package com.davidcrespo.meet.core.di

import com.davidcrespo.domain.usecases.di.UseCaseDependencyInjector
import com.davidcrespo.meet.core.common.di.KoinModuleLoader
import com.davidcrespo.meet.data.datasource.di.DataSourceDependencyInjector
import com.davidcrespo.meet.data.repository.di.RepositoryDependencyInjector
import com.davidcrespo.meet.presentation.viewmodels.di.ViewModelDependencyInjector
import org.koin.core.module.Module

object CoreDiDependencyInjector : KoinModuleLoader {
    override val modules: List<Module>
        get() = listOf(
            DataSourceDependencyInjector.modules,
            RepositoryDependencyInjector.modules,
            UseCaseDependencyInjector.modules,
            ViewModelDependencyInjector.modules,
        ).flatten()
}

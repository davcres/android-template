package com.davidcrespo.template.core.di

import com.davidcrespo.domain.usecases.di.UseCaseDependencyInjector
import com.davidcrespo.template.core.common.di.KoinModuleLoader
import com.davidcrespo.template.data.datasource.di.DataSourceDependencyInjector
import com.davidcrespo.template.data.repository.di.RepositoryDependencyInjector
import com.davidcrespo.template.presentation.ui.di.UiDependencyInjector
import com.davidcrespo.template.presentation.viewmodels.di.ViewModelDependencyInjector
import org.koin.core.module.Module

object CoreDiDependencyInjector : KoinModuleLoader {
    override val modules: List<Module>
        get() = listOf(
            DataSourceDependencyInjector.modules,
            RepositoryDependencyInjector.modules,
            UseCaseDependencyInjector.modules,
            ViewModelDependencyInjector.modules,
            UiDependencyInjector.modules
        ).flatten()
}

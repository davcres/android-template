package com.davcres.template.core.di

import com.davcres.domain.usecases.di.UseCaseDependencyInjector
import com.davcres.template.core.common.di.KoinModuleLoader
import com.davcres.template.data.datasource.di.DataSourceDependencyInjector
import com.davcres.template.data.repository.di.RepositoryDependencyInjector
import com.davcres.template.presentation.ui.di.UiDependencyInjector
import com.davcres.template.presentation.viewmodels.di.ViewModelDependencyInjector
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

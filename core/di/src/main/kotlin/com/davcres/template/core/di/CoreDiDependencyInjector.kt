package com.davcres.template.core.di

import com.davcres.template.appRoot.data.datasource.di.AppRootDataSourceDependencyInjector
import com.davcres.template.appRoot.data.repository.di.AppRootRepositoryDependencyInjector
import com.davcres.template.appRoot.domain.usecases.di.AppRootUseCaseDependencyInjector
import com.davcres.template.appRoot.presentation.ui.di.AppRootUiDependencyInjector
import com.davcres.template.appRoot.presentation.viewmodels.di.AppRootViewModelDependencyInjector
import com.davcres.template.auth.data.datasource.di.AuthDataSourceDependencyInjector
import com.davcres.template.auth.data.repository.di.AuthRepositoryDependencyInjector
import com.davcres.template.auth.domain.usecases.di.AuthUseCaseDependencyInjector
import com.davcres.template.auth.presentation.ui.di.AuthUiDependencyInjector
import com.davcres.template.auth.presentation.viewmodels.di.AuthViewModelDependencyInjector
import com.davcres.template.core.common.di.KoinModuleLoader
import org.koin.core.module.Module

object CoreDiDependencyInjector : KoinModuleLoader {
    override val modules: List<Module>
        get() = listOf(
            AppRootDataSourceDependencyInjector.modules,
            AppRootRepositoryDependencyInjector.modules,
            AppRootUseCaseDependencyInjector.modules,
            AppRootViewModelDependencyInjector.modules,
            AppRootUiDependencyInjector.modules,

            AuthDataSourceDependencyInjector.modules,
            AuthRepositoryDependencyInjector.modules,
            AuthUseCaseDependencyInjector.modules,
            AuthViewModelDependencyInjector.modules,
            AuthUiDependencyInjector.modules,
        ).flatten()
}

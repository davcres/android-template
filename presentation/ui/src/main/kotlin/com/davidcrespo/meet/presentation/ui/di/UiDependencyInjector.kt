package com.davidcrespo.meet.presentation.ui.di

import com.davidcrespo.meet.core.common.di.KoinModuleLoader
import org.koin.core.module.Module
import org.koin.dsl.module

object UiDependencyInjector : KoinModuleLoader {

    override val modules: List<Module>
        get() = listOf(
            module {

            }
        )
}
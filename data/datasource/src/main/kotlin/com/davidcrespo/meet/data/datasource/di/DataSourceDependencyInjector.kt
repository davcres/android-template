package com.davidcrespo.meet.data.datasource.di

import com.davidcrespo.meet.core.common.di.KoinModuleLoader
import org.koin.core.module.Module
import org.koin.dsl.module

object DataSourceDependencyInjector : KoinModuleLoader {
    override val modules: List<Module>
        get() = listOf(
            module {
                /*single<ApiService> {
                    Ktorfit
                        .Builder()
                        .baseUrl(ApiService.API_URL)
                        .build()
                        .create()
                }
                singleOf(::Database)
                single<AppPreferences> { AppPreferencesImpl(get()) }*/
            }
        )
}

package com.davcres.template.appRoot.presentation.ui.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.davcres.template.core.common.Constants.DataStore.PREFERENCES_NAME
import com.davcres.template.core.common.di.KoinModuleLoader
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

object AppRootUiDependencyInjector : KoinModuleLoader {

    override val modules: List<Module>
        get() = listOf(
            module {
                single<DataStore<Preferences>> {
                    PreferenceDataStoreFactory.create(
                        produceFile = { androidContext().preferencesDataStoreFile(PREFERENCES_NAME) }
                    )
                }
            }
        )
}

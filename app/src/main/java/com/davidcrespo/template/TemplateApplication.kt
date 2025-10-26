package com.davidcrespo.template

import android.app.Application
import com.davidcrespo.template.core.di.CoreDiDependencyInjector
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TemplateApplication : Application() {

    override fun onCreate() {
        startKoin {
            androidContext(this@TemplateApplication)
            modules(CoreDiDependencyInjector.modules)
        }

        super.onCreate()
    }
}

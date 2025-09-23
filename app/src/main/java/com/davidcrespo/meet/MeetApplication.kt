package com.davidcrespo.meet

import android.app.Application
import com.davidcrespo.meet.core.di.CoreDiDependencyInjector
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MeetApplication : Application() {

    override fun onCreate() {
        startKoin {
            androidContext(this@MeetApplication)
            modules(CoreDiDependencyInjector.modules)
        }

        super.onCreate()
    }
}

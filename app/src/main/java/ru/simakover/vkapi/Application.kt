package ru.simakover.vkapi

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.simakover.vkapi.di.dataModule
import ru.simakover.vkapi.di.viewModelsModule

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@Application)
            modules(dataModule, viewModelsModule)
        }
    }
}
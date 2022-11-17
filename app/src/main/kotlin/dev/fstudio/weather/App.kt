package dev.fstudio.weather

import android.app.Application
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.logger.slf4jLogger

@ExperimentalSerializationApi
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            slf4jLogger(Level.ERROR)
            androidLogger(Level.ERROR)
            androidContext(this@App)
        }
    }
}
package com.joshua.spacexlaunch

import android.app.Application
import com.joshua.spacexlaunch.di.ApiModule
import com.joshua.spacexlaunch.di.AppModule
import com.joshua.spacexlaunch.di.DBModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class App: Application() {

    companion object {
        lateinit var self: Application
        fun applicationContext(): Application {
            return self
        }
    }

    init {
        self = this
    }

    override fun onCreate() {
        super.onCreate()

        val module = listOf(
            AppModule, ApiModule
        )

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(module)
        }

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree()) else
            Timber.plant(Timber.DebugTree())

    }


}
package dev.unero.bukutabungan

import android.app.Application
import dev.unero.bukutabungan.di.databaseModule
import dev.unero.bukutabungan.di.repositoryModule
import dev.unero.bukutabungan.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                repositoryModule,
                databaseModule,
                viewModelModule
            )
        }
    }
}
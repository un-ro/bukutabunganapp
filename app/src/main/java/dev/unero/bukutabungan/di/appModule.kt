package dev.unero.bukutabungan.di

import android.app.Application
import androidx.room.Room
import dev.unero.bukutabungan.data.local.RecordDao
import dev.unero.bukutabungan.data.local.RecordDatabase
import dev.unero.bukutabungan.data.repository.AccountRepositoryImpl
import dev.unero.bukutabungan.data.repository.RecordRepositoryImpl
import dev.unero.bukutabungan.domain.repository.AccountRepository
import dev.unero.bukutabungan.domain.repository.RecordRepository
import dev.unero.bukutabungan.ui.dashboard.DashboardViewModel
import dev.unero.bukutabungan.ui.history.HistoryViewModel
import dev.unero.bukutabungan.ui.insert.InsertViewModel
import dev.unero.bukutabungan.ui.login.LoginViewModel
import dev.unero.bukutabungan.ui.settings.SettingsViewModel
import dev.unero.bukutabungan.utils.DB_NAME
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single<RecordRepository> { RecordRepositoryImpl(get()) }
    single<AccountRepository> { AccountRepositoryImpl(get()) }
}

val databaseModule = module {
    fun provideDB(application: Application): RecordDatabase =
        Room.databaseBuilder(application, RecordDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    fun provideDao(database: RecordDatabase): RecordDao = database.getDao()

    single { provideDB(androidApplication()) }
    single { provideDao(get())}
}

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { DashboardViewModel(get(), get()) }
    viewModel { HistoryViewModel(get()) }
    viewModel { InsertViewModel(get()) }
    viewModel { SettingsViewModel(get()) }
}
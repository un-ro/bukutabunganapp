package dev.unero.bukutabungan.di

import androidx.room.Room
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
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single<RecordRepository> { RecordRepositoryImpl() }
    single<AccountRepository> { AccountRepositoryImpl() }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(androidApplication(), RecordDatabase::class.java, "RECORD_DB")
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<RecordDatabase>().getDao() }
}

val viewModelModule = module {
    viewModel { LoginViewModel() }
    viewModel { DashboardViewModel() }
    viewModel { HistoryViewModel() }
    viewModel { InsertViewModel() }
    viewModel { SettingsViewModel() }
}
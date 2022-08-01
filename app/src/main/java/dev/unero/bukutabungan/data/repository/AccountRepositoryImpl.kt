package dev.unero.bukutabungan.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dev.unero.bukutabungan.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AccountRepositoryImpl(
    private val context: Context
): AccountRepository {

    private val Context.datastore: DataStore<Preferences> by preferencesDataStore("account")

    override suspend fun getUsername(): Flow<String> = context.datastore.data.map { prefs ->
        prefs[PREF_USERNAME] ?: DEFAULT
    }

    override suspend fun getPassword(): Flow<String> = context.datastore.data.map { prefs ->
        prefs[PREF_PASSWORD] ?: DEFAULT
    }

    override suspend fun setPassword(newPassword: String) {
        context.datastore.edit { account ->
            account[PREF_PASSWORD] = newPassword
        }
    }

    companion object {
        private val PREF_USERNAME = stringPreferencesKey("USERNAME")
        private val PREF_PASSWORD = stringPreferencesKey("PASSWORD")
        private const val DEFAULT = "user"
    }
}
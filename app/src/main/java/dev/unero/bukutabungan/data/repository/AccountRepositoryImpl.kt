package dev.unero.bukutabungan.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dev.unero.bukutabungan.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.datastore: DataStore<Preferences> by preferencesDataStore("account")
class AccountRepositoryImpl(
    private val context: Context
): AccountRepository {
    override suspend fun getUsername(): Flow<String> = context.datastore.data.map { prefs ->
        prefs[PREF_USERNAME] ?: "user"
    }

    override suspend fun getPassword(): Flow<String> = context.datastore.data.map { prefs ->
        prefs[PREF_PASSWORD] ?: "user"
    }

    override suspend fun setPassword(newPassword: String) {
        context.datastore.edit { account ->
            account[PREF_PASSWORD] = newPassword
        }
    }

    companion object {
        private val PREF_USERNAME = stringPreferencesKey("USERNAME")
        private val PREF_PASSWORD = stringPreferencesKey("PASSWORD")
    }
}
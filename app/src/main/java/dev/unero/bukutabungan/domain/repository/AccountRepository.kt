package dev.unero.bukutabungan.domain.repository

import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    suspend fun getUsername(): Flow<String>
    suspend fun getPassword(): Flow<String>
    suspend fun setPassword(newPassword: String)
}
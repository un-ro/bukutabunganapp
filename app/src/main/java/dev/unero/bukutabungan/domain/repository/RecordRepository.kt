package dev.unero.bukutabungan.domain.repository

import androidx.lifecycle.LiveData
import dev.unero.bukutabungan.data.model.Record

interface RecordRepository {
    fun getAllRecord(): LiveData<List<Record>>
    suspend fun insertRecord(record: Record)
}
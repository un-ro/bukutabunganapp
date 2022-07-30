package dev.unero.bukutabungan.domain.repository

import dev.unero.bukutabungan.data.model.Record

interface RecordRepository {
    fun getAllRecord(): List<Record>

    suspend fun insertRecord(record: Record)
}
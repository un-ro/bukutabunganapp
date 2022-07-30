package dev.unero.bukutabungan.data.repository

import dev.unero.bukutabungan.data.model.Record
import dev.unero.bukutabungan.domain.repository.RecordRepository

class RecordRepositoryImpl: RecordRepository {
    override fun getAllRecord(): List<Record> {
        TODO("Not yet implemented")
    }

    override suspend fun insertRecord(record: Record) {
        TODO("Not yet implemented")
    }
}
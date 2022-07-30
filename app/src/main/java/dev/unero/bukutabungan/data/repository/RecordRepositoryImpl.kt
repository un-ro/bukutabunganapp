package dev.unero.bukutabungan.data.repository

import androidx.lifecycle.LiveData
import dev.unero.bukutabungan.data.local.RecordDao
import dev.unero.bukutabungan.data.model.Record
import dev.unero.bukutabungan.domain.repository.RecordRepository

class RecordRepositoryImpl(
    private val dao: RecordDao
): RecordRepository {
    override fun getAllRecord(): LiveData<List<Record>> = dao.getAll()
    override suspend fun insertRecord(record: Record) { dao.insert(record) }
}